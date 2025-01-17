package com.finderfeed.solarforge.magic_items.items;

import com.finderfeed.solarforge.Helpers;
import com.finderfeed.solarforge.SolarForge;
import com.finderfeed.solarforge.packet_handler.SolarForgePacketHandler;
import com.finderfeed.solarforge.packet_handler.TriggerToastPacket;
import com.finderfeed.solarforge.registries.blocks.BlocksRegistry;
import com.finderfeed.solarforge.magic_items.items.solar_lexicon.achievements.Achievement;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class SolarEnergyGeneratorBlockItem extends BlockItem {

    public SolarEnergyGeneratorBlockItem() {
        super(BlocksRegistry.SOLAR_ENERGY_GENERATOR.get(),new Item.Properties().tab(SolarForge.SOLAR_GROUP_BLOCKS));
    }

    @Override
    public void inventoryTick(ItemStack p_77663_1_, Level p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if ((!p_77663_2_.isClientSide) && (p_77663_3_ instanceof Player) ){
            Helpers.fireProgressionEvent((Player) p_77663_3_,Achievement.CRAFT_SOLAR_ENERGY_GENERATOR);
        }
        super.inventoryTick(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
    }
}
