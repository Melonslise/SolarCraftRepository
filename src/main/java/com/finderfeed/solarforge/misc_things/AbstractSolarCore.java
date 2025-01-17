package com.finderfeed.solarforge.misc_things;

import com.finderfeed.solarforge.Helpers;

import com.finderfeed.solarforge.packet_handler.SolarForgePacketHandler;
import com.finderfeed.solarforge.packet_handler.packets.UpdateCoreOnClient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;


import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.BlockPos;

import net.minecraftforge.fmllegacy.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSolarCore extends BlockEntity implements IBindable,ISolarEnergyContainer {
    public int energy = 0;
    public int count = 0;
    public int update_tick=0;
    public List<BlockPos> poslist = new ArrayList<>();

    public AbstractSolarCore(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
        super(p_155228_, p_155229_, p_155230_);
    }


    public static void tick(Level world, BlockPos pos, BlockState blockState, AbstractSolarCore tile) {
        if (tile.level.isClientSide){
            //System.out.println(poslist);
        }
        if (!tile.level.isClientSide && tile.getConditionToFunction()){


            tile.count = tile.poslist.size();
            List<BlockPos> toRemove = new ArrayList<>();

            for (BlockPos a :tile.poslist){
                boolean b = false;
                BlockEntity tileAtPos = tile.level.getBlockEntity(a);
                if (tileAtPos instanceof AbstractSolarNetworkRepeater){
                    List<BlockPos > visited = new ArrayList<>();
                    ((AbstractSolarNetworkRepeater) tileAtPos).tryTransmitEnergyCore(tile,tile.getMaxEnergyFlowPerSec(), visited);
                }else if (tileAtPos instanceof IEnergyUser){
                    if (tile.energy >= tile.getMaxEnergyFlowPerSec() && ((IEnergyUser) tileAtPos).requriesEnergy()  ) {
                        ((IEnergyUser) tileAtPos).giveEnergy(tile.getMaxEnergyFlowPerSec());
                        tile.energy -= tile.getMaxEnergyFlowPerSec();
                    }
                }else {

                    toRemove.add(a);
                    b = true;
                }


                    SolarForgePacketHandler.INSTANCE.send(PacketDistributor.NEAR.with(PacketDistributor.TargetPoint.p(tile.worldPosition.getX(), tile.worldPosition.getY(), tile.worldPosition.getZ(), 40, tile.level.dimension())),
                            new UpdateCoreOnClient(tile.worldPosition, a, tile.poslist.indexOf(a), b));


            }
            tile.poslist.removeAll(toRemove);
        }
    }

    public abstract int getMaxEnergy();
    public abstract int getMaxEnergyFlowPerSec();
    public abstract int getRadius();
    public abstract boolean getConditionToFunction();

    @Override
    public void load( CompoundTag p_230337_2_) {
        count = p_230337_2_.getInt("sizepos");
        for (int i = 0;i<count;i++) {
            poslist.add(new BlockPos(p_230337_2_.getInt("xpos"+i), p_230337_2_.getInt("ypos"+i), p_230337_2_.getInt("zpos"+i)));
        }
        energy = p_230337_2_.getInt("energy_level");
        super.load( p_230337_2_);
    }

    @Override
    public CompoundTag save(CompoundTag p_189515_1_) {
        p_189515_1_.putInt("sizepos",count);
        for (int i = 0;i<poslist.size();i++) {
            p_189515_1_.putInt("xpos"+i, poslist.get(i).getX());
            p_189515_1_.putInt("ypos"+i, poslist.get(i).getY());
            p_189515_1_.putInt("zpos"+i, poslist.get(i).getZ());
        }
        p_189515_1_.putInt("energy_level",energy);
        return super.save(p_189515_1_);
    }


    @Override
    public void bindPos(BlockPos pos) {
        if (!poslist.contains(pos) && !(level.getBlockEntity(pos) instanceof AbstractSolarCore) && Helpers.isReachable(level,worldPosition,pos,getRadius()) && !(level.getBlockEntity(pos) instanceof AbstractEnergyGeneratorTileEntity)){
            poslist.add(pos);
        }
        else if ((level.getBlockEntity(pos) instanceof AbstractEnergyGeneratorTileEntity) && Helpers.isReachable(level,worldPosition,pos,getRadius())){
            ((AbstractEnergyGeneratorTileEntity) level.getBlockEntity(pos)).bindPos(worldPosition);
        }

    }


    @Override
    public double getEnergy() {
        return energy;
    }
}
