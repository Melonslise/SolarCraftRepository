package com.finderfeed.solarforge.magic_items.items;


import com.finderfeed.solarforge.magic_items.items.solar_lexicon.unlockables.AncientFragment;
import com.finderfeed.solarforge.magic_items.items.solar_lexicon.unlockables.AncientFragmentISTER;
import com.finderfeed.solarforge.magic_items.items.solar_lexicon.unlockables.ProgressionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.TooltipFlag;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;

import javax.annotation.Nullable;
import java.nio.charset.MalformedInputException;
import java.util.List;
import java.util.function.Consumer;

public class AncientFragmentItem extends Item {
    public AncientFragmentItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }






    @Override
    public void fillItemCategory(CreativeModeTab p_150895_1_, NonNullList<ItemStack> list) {
        super.fillItemCategory(p_150895_1_, list);
        if (this.allowdedIn(p_150895_1_)) {
            for (AncientFragment frag : AncientFragment.getAllFragments()) {
                ItemStack stack = new ItemStack(this, 1);
                stack.getOrCreateTagElement(ProgressionHelper.TAG_ELEMENT).putString(ProgressionHelper.FRAG_ID, frag.getId());
                list.add(stack);
            }
        }

    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> texts, TooltipFlag p_77624_4_) {
        CompoundTag nbt = stack.getTagElement(ProgressionHelper.TAG_ELEMENT);
        if (nbt == null){
            texts.add(new TranslatableComponent("ancient_frag.no_tag").withStyle(ChatFormatting.GOLD));
        }else{
            AncientFragment frag = AncientFragment.getFragmentByID(nbt.getString(ProgressionHelper.FRAG_ID));
            if (frag != null){
                texts.add(new TranslatableComponent("ancient_frag.fragment_active").withStyle(ChatFormatting.GOLD).append(frag.getTranslation()));
            }
            texts.add(new TranslatableComponent("ancient_frag.has_tag").withStyle(ChatFormatting.GOLD));
        }

        super.appendHoverText(stack, level, texts, p_77624_4_);
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(FragmentRenderProperties.INSTANCE);
    }
}

class FragmentRenderProperties implements IItemRenderProperties{

    public static FragmentRenderProperties INSTANCE = new FragmentRenderProperties();

    @Override
    public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
        return new AncientFragmentISTER(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }
}
