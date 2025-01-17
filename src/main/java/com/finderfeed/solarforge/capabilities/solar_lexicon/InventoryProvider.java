package com.finderfeed.solarforge.capabilities.solar_lexicon;

import com.finderfeed.solarforge.magic_items.items.solar_lexicon.unlockables.AncientFragment;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InventoryProvider implements ICapabilitySerializable<CompoundTag> {


    private final ItemStackHandler lexicon_inv = new ItemStackHandler(AncientFragment.getAllFragments().length);
    private final LazyOptional<ItemStackHandler> lexicon_inv_optional = LazyOptional.of(()->lexicon_inv);
    public void invalidate(){
        lexicon_inv_optional.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap != CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return LazyOptional.empty();
        }
        return lexicon_inv_optional.cast();
    }

    @Override
    public CompoundTag serializeNBT() {
        return lexicon_inv.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        lexicon_inv.deserializeNBT(nbt);
    }
}
