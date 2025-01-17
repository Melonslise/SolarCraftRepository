package com.finderfeed.solarforge.magic_items.blocks.infusing_table_things;

import com.finderfeed.solarforge.ClientHelpers;
import com.finderfeed.solarforge.Helpers;
import com.finderfeed.solarforge.RenderingTools;
import com.finderfeed.solarforge.SolarForge;
import com.finderfeed.solarforge.events.RenderEventsHandler;
import com.finderfeed.solarforge.magic_items.blocks.blockentities.RuneEnergyPylonTile;
import com.finderfeed.solarforge.magic_items.items.solar_lexicon.achievements.Achievement;
import com.finderfeed.solarforge.misc_things.*;
import com.finderfeed.solarforge.packet_handler.SolarForgePacketHandler;
import com.finderfeed.solarforge.packet_handler.packets.UpdateEnergyOnClientPacket;
import com.finderfeed.solarforge.recipe_types.InfusingRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.world.item.TooltipFlag;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import net.minecraft.world.item.Item.Properties;

public class SolarWandItem extends Item implements ManaConsumer {


    public SolarWandItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

        player.startUsingItem(hand);

        return super.use(world, player, hand);
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        if (player instanceof  Player) {
            handleEnergyConsumption(player.level, (Player) player);
        }
        super.onUsingTick(stack, player, count);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_77661_1_) {
        return UseAnim.BOW;
    }

    public InteractionResult useOn(UseOnContext ctx) {
        BlockPos pos = ctx.getClickedPos();
        Level world = ctx.getLevel();
        if (!world.isClientSide && world.getBlockEntity(pos) != null
                && world.getBlockEntity(pos) instanceof InfusingTableTileEntity) {
            InfusingTableTileEntity tile = (InfusingTableTileEntity) world.getBlockEntity(pos);
            tile.triggerCrafting(ctx.getPlayer());
            return InteractionResult.SUCCESS;
        }


        return InteractionResult.FAIL;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable Level p_77624_2_, List<Component> p_77624_3_, TooltipFlag p_77624_4_) {
        p_77624_3_.add(new TranslatableComponent("solarcraft.solar_wand").withStyle(ChatFormatting.GOLD));
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
    }

    @Override
    public double getManacost() {
        return 0;
    }


    public void handleEnergyConsumption(Level world, Player player){

        Vec3 from = player.position().add(0,1.4,0);
        Vec3 look = player.getLookAngle().multiply(30,30,30);
        Vec3 to = from.add(look);
        ClipContext ctx = new ClipContext(from,to, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,null);
        BlockHitResult res = world.clip(ctx);

        if (world.getBlockEntity(res.getBlockPos()) instanceof RuneEnergyPylonTile){
            if (!world.isClientSide){
                RuneEnergyPylonTile tile = (RuneEnergyPylonTile) world.getBlockEntity(res.getBlockPos());
                tile.givePlayerEnergy(player,5);
                player.displayClientMessage(new TextComponent(tile.getEnergyType().id.toUpperCase()+" "+RunicEnergy.getEnergy(player,tile.getEnergyType())).withStyle(ChatFormatting.GOLD),true);
                Helpers.updateRunicEnergyOnClient(tile.getEnergyType(),RunicEnergy.getEnergy(player,tile.getEnergyType()),player);
                Helpers.fireProgressionEvent(player, Achievement.RUNE_ENERGY_CLAIM);
            }else{
                Vec3 pos = new Vec3(res.getBlockPos().getX()+0.5,res.getBlockPos().getY()+0.5,res.getBlockPos().getZ()+0.5);
                Vec3 vel = new Vec3(from.x-pos.x,from.y-pos.y,from.z-pos.z);
                ClientHelpers.handleSolarWandParticles(pos,vel);

            }
        }
    }

}

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE,modid = "solarforge",value = Dist.CLIENT)
class WandEvents{

    public static final ResourceLocation LOC = new ResourceLocation("solarforge", "textures/misc/wand_crafting_progress.png");
    @SubscribeEvent
    public static void renderWandOverlays(final RenderGameOverlayEvent event){

            if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
                Minecraft mc = Minecraft.getInstance();
                Player player = mc.player;
                if (player.getMainHandItem().getItem() instanceof SolarWandItem) {
                    ClipContext ctx = new ClipContext(player.position().add(0, 1.5, 0),
                            player.position().add(0, 1.5, 0).add(player.getLookAngle().normalize().multiply(4.5, 4.5, 4.5)),
                            ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player);
                    BlockHitResult result = player.level.clip(ctx);

                    if (result.getType() == HitResult.Type.BLOCK &&
                            player.level.getBlockState(result.getBlockPos()).getBlock() instanceof InfusingTableBlock) {
                        BlockEntity tile = player.level.getBlockEntity(result.getBlockPos());
                        if (tile instanceof InfusingTableTileEntity) {
                            InfusingTableTileEntity tileInfusing = (InfusingTableTileEntity) tile;
                            ClientHelpers.bindText(LOC);
                            if (tileInfusing.RECIPE_IN_PROGRESS) {
                                double percent = (float) tileInfusing.CURRENT_PROGRESS / tileInfusing.INFUSING_TIME;
                                int height = event.getWindow().getGuiScaledHeight();
                                int width = event.getWindow().getGuiScaledWidth();

                                GuiComponent.blit(event.getMatrixStack(), width / 2 - 20, height / 2 + 11, 0, 9, (int) (40 * percent), 3, 40, 20);
                                GuiComponent.blit(event.getMatrixStack(), width / 2 - 20, height / 2 + 8, 0, 0, 40, 9, 40, 20);
                            }else{
                                Optional<InfusingRecipe> recipe = mc.level.getRecipeManager().getRecipeFor(SolarForge.INFUSING_RECIPE_TYPE,tileInfusing,mc.level);
                                if (recipe.isPresent()) {
                                    int height = event.getWindow().getGuiScaledHeight();
                                    int width = event.getWindow().getGuiScaledWidth();
                                    GuiComponent.blit(event.getMatrixStack(), width / 2 - 20, height / 2 + 8, 0, 0, 40, 9, 40, 20);
                                    GuiComponent.blit(event.getMatrixStack(), width / 2 -7, height / 2 + 7, 14, 24, 14, 14, 80, 40);
                                }else{
                                    int height = event.getWindow().getGuiScaledHeight();
                                    int width = event.getWindow().getGuiScaledWidth();
                                    GuiComponent.blit(event.getMatrixStack(), width / 2 - 20, height / 2 + 8, 0, 0, 40, 9, 40, 20);
                                    GuiComponent.blit(event.getMatrixStack(), width / 2 -7, height / 2 + 7, 0, 24, 14, 14, 80, 40);

                                }
                            }

                        }
                    }

                }
            }

    }
}
