package com.finderfeed.solarforge.magic_items.blocks.infusing_table_things.infusing_pool;

import com.finderfeed.solarforge.packet_handler.SolarForgePacketHandler;
import com.finderfeed.solarforge.registries.tile_entities.TileEntitiesRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.fmllegacy.network.PacketDistributor;


public class InfusingPoolTileEntity extends RandomizableContainerBlockEntity  {

    public NonNullList<ItemStack> items = NonNullList.withSize(1,ItemStack.EMPTY);

    public InfusingPoolTileEntity(BlockPos p_155630_, BlockState p_155631_) {
        super(TileEntitiesRegistry.INFUSING_POOL_BLOCKENTITY.get(), p_155630_, p_155631_);
    }


    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("container.solarforge.infusing_pool");
    }

    @Override
    protected AbstractContainerMenu createMenu(int x, Inventory inv) {
        return new InfusingPoolContainer(x,inv,this);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }



    @Override
    public int getContainerSize() {
        return this.items.size();
    }



    @Override
    public CompoundTag save(CompoundTag cmp){
        super.save(cmp);

        if (!this.trySaveLootTable(cmp)) {
            ContainerHelper.saveAllItems(cmp, this.items);
        }
        return cmp;
    }

    @Override
    public void load( CompoundTag cmp) {
        super.load(cmp);

        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(cmp)) {
            ContainerHelper.loadAllItems(cmp, this.items);
        }
    }


    public static void tick(Level world,BlockPos pos,BlockState state,InfusingPoolTileEntity tile) {
        if (!tile.level.isClientSide) {
            SolarForgePacketHandler.INSTANCE.send(PacketDistributor.NEAR.with(PacketDistributor.TargetPoint.p(tile.worldPosition.getX(),tile.worldPosition.getY(),tile.worldPosition.getZ(),20,tile.level.dimension())),
                    new UpdateStacksOnClientPacketPool(tile.getItem(0),tile.worldPosition));

        }
    }


}
