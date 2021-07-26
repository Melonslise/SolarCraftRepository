package com.finderfeed.solarforge.world_generation.features.foliage_placers;

import com.finderfeed.solarforge.Helpers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageAttachment;

public class BurntTreeFoliagePlacer extends FoliagePlacer {
    public static final Codec<BurntTreeFoliagePlacer> CODEC = RecordCodecBuilder.create((p_236737_0_) -> {
        return foliagePlacerParts(p_236737_0_).apply(p_236737_0_, BurntTreeFoliagePlacer::new);
    });
    public BurntTreeFoliagePlacer(ConstantInt p_i241999_1_, ConstantInt p_i241999_2_) {
        super(p_i241999_1_, p_i241999_2_);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return FoliagePlacerRegistry.BURNT_TREE_FOLIAGE_PLACER_FOLIAGE_PLACER_TYPE.get();
    }


    @Override
    protected void createFoliage(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> biConsumer, Random random, TreeConfiguration cfg, int i, FoliageAttachment foliageAttachment, int i1, int i2, int i3) {

//        BlockPos foliagePos = foliage.foliagePos();
//        BlockPos foliagePosBelow = foliagePos.below();
//        BlockPos foliagePosAbove = foliagePos.above();
//        BlockPos foliagePosAboveX2 = foliagePos.above().above();
//        Helpers.getSurroundingBlockPositionsHorizontal(foliagePos).forEach((pos)->{
//            reader.setBlock(pos,cfg.leavesProvider.getState(random,pos).setValue(BlockStateProperties.DISTANCE,1),19);
//            reader.setBlock(pos.above(),cfg.leavesProvider.getState(random,pos.above()).setValue(BlockStateProperties.DISTANCE,1),19);
//        });
////        reader.setBlock(foliagePos,cfg.leavesProvider.getState(random,foliagePos),19);
////        reader.setBlock(foliagePos.above(),cfg.leavesProvider.getState(random,foliagePos.above()),19);
//
//        reader.setBlock(foliagePosAboveX2,cfg.leavesProvider.getState(random,foliagePosAboveX2).setValue(BlockStateProperties.DISTANCE,1),19);
//        reader.setBlock(foliagePosAboveX2.east(),cfg.leavesProvider.getState(random,foliagePosAboveX2.east()).setValue(BlockStateProperties.DISTANCE,1),19);
//        reader.setBlock(foliagePosAboveX2.west(),cfg.leavesProvider.getState(random,foliagePosAboveX2.west()).setValue(BlockStateProperties.DISTANCE,1),19);
//        reader.setBlock(foliagePosAboveX2.north(),cfg.leavesProvider.getState(random,foliagePosAboveX2.north()).setValue(BlockStateProperties.DISTANCE,1),19);
//        reader.setBlock(foliagePosAboveX2.south(),cfg.leavesProvider.getState(random,foliagePosAboveX2.south()).setValue(BlockStateProperties.DISTANCE,1),19);
//
//        reader.setBlock(foliagePosBelow.east(),cfg.leavesProvider.getState(random,foliagePosBelow.east()).setValue(BlockStateProperties.DISTANCE,1),19);
//        reader.setBlock(foliagePosBelow.west(),cfg.leavesProvider.getState(random,foliagePosBelow.west()).setValue(BlockStateProperties.DISTANCE,1),19);
//        reader.setBlock(foliagePosBelow.north(),cfg.leavesProvider.getState(random,foliagePosBelow.north()).setValue(BlockStateProperties.DISTANCE,1),19);
//        reader.setBlock(foliagePosBelow.south(),cfg.leavesProvider.getState(random,foliagePosBelow.south()).setValue(BlockStateProperties.DISTANCE,1),19);
//
//        reader.setBlock(foliagePosBelow.east(3),cfg.leavesProvider.getState(random,foliagePosBelow.east(3)).setValue(BlockStateProperties.DISTANCE,1),19);
//        reader.setBlock(foliagePosBelow.west(3),cfg.leavesProvider.getState(random,foliagePosBelow.west(3)).setValue(BlockStateProperties.DISTANCE,1),19);
//        reader.setBlock(foliagePosBelow.north(3),cfg.leavesProvider.getState(random,foliagePosBelow.north(3)).setValue(BlockStateProperties.DISTANCE,1),19);
//        reader.setBlock(foliagePosBelow.south(3),cfg.leavesProvider.getState(random,foliagePosBelow.south(3)).setValue(BlockStateProperties.DISTANCE,1),19);
//
//
//        reader.setBlock(foliagePosAbove.east(2),cfg.leavesProvider.getState(random,foliagePosAbove.east(2)).setValue(BlockStateProperties.DISTANCE,1),19);
//        reader.setBlock(foliagePosAbove.west(2),cfg.leavesProvider.getState(random,foliagePosAbove.west(2)).setValue(BlockStateProperties.DISTANCE,1),19);
//        reader.setBlock(foliagePosAbove.north(2),cfg.leavesProvider.getState(random,foliagePosAbove.north(2)).setValue(BlockStateProperties.DISTANCE,1),19);
//        reader.setBlock(foliagePosAbove.south(2),cfg.leavesProvider.getState(random,foliagePosAbove.south(2)).setValue(BlockStateProperties.DISTANCE,1),19);
//
//        for (int i = 2;i <= 3;i++){
//            reader.setBlock(foliagePos.east(i),cfg.leavesProvider.getState(random,foliagePos.east(i)).setValue(BlockStateProperties.DISTANCE,1),19);
//            reader.setBlock(foliagePos.west(i),cfg.leavesProvider.getState(random,foliagePos.west(i)).setValue(BlockStateProperties.DISTANCE,1),19);
//            reader.setBlock(foliagePos.north(i),cfg.leavesProvider.getState(random,foliagePos.north(i)).setValue(BlockStateProperties.DISTANCE,1),19);
//            reader.setBlock(foliagePos.south(i),cfg.leavesProvider.getState(random,foliagePos.south(i)).setValue(BlockStateProperties.DISTANCE,1),19);
//        }

    }

    @Override
    public int foliageHeight(Random p_230374_1_, int p_230374_2_, TreeConfiguration p_230374_3_) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        return false;
    }



}
