package com.finderfeed.solarforge.magic_items.items;

import com.finderfeed.solarforge.magic_items.items.solar_lexicon.unlockables.AncientFragment;
import com.finderfeed.solarforge.magic_items.items.solar_lexicon.unlockables.ProgressionHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;

public class AncientFragmentItem extends Item {
    public AncientFragmentItem(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }


    @Override
    public boolean verifyTagAfterLoad(CompoundNBT p_179215_1_) {
        return true;
    }

    @Override
    public void fillItemCategory(ItemGroup p_150895_1_, NonNullList<ItemStack> list) {
        super.fillItemCategory(p_150895_1_, list);
        if (this.allowdedIn(p_150895_1_)) {
            for (AncientFragment frag : AncientFragment.getAllFragments()) {
                ItemStack stack = new ItemStack(this, 1);
                stack.getOrCreateTagElement(ProgressionHelper.TAG_ELEMENT).putString(ProgressionHelper.FRAG_ID, frag.getId());
                list.add(stack);
            }
        }

    }
}
