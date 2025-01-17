package com.finderfeed.solarforge.world_generation.structures;


import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;


public class Structures {

    public static List<BlockEntity> checkInfusingStandStructure(BlockPos pos, Level world){
        List<BlockEntity> list = new ArrayList<>(0);
                    list.add(blockGet(pos.offset(4,0,0),world));
                    list.add(blockGet(pos.offset(3,0,-3),world));
                    list.add(blockGet(pos.offset(0,0,-4),world)) ;
                    list.add(blockGet(pos.offset(-3,0,-3),world)) ;
                    list.add(blockGet(pos.offset(-4,0,0),world));
                    list.add(blockGet(pos.offset(-3,0,3),world));
                    list.add(blockGet(pos.offset(0,0,4),world));
                    list.add(blockGet(pos.offset(3,0,3),world));

                    /*
                    Recipe:
                              N
                              *
                            *   *
                          *       *^
                            *   *
                              *
                              S
                     */
        return list;

    }
    public static BlockEntity blockGet(BlockPos pos,Level world){
        return world.getBlockEntity(pos);
    }
}
