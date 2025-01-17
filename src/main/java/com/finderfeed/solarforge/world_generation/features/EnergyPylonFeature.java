package com.finderfeed.solarforge.world_generation.features;

import com.finderfeed.solarforge.Helpers;
import com.mojang.serialization.Codec;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;

import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraftforge.fluids.IFluidBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnergyPylonFeature extends Feature<NoneFeatureConfiguration> {
    private static final ResourceLocation FEATURE = new ResourceLocation("solarforge:worldgen_features/energy_pylon");

    public EnergyPylonFeature(Codec<NoneFeatureConfiguration> p_i231953_1_) {
        super(p_i231953_1_);
    }



    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {

        WorldGenLevel world = ctx.level();
        Random random = ctx.random();
        BlockPos pos = ctx.origin();
        Rotation rot = Rotation.getRandom(random);
        StructureManager manager = world.getLevel().getStructureManager();
        List<ResourceLocation> list = new ArrayList<>();

        StructureTemplate templ = manager.getOrCreate(FEATURE);



        StructurePlaceSettings set = new StructurePlaceSettings().addProcessor(BlockIgnoreProcessor.AIR).setRandom(random).setRotation(rot).setBoundingBox(BoundingBox.infinite());

        BlockPos pos1 = findFlatChunkPosition(world,pos,5);

        if (!pos1.equals(Helpers.NULL_POS)) {

            BlockPos blockpos1 = templ.getZeroPositionWithTransform(pos1.offset(0,1,0), Mirror.NONE, rot);
            templ.placeInWorld(world, blockpos1, blockpos1, set, random, 4);
        }
        return true;
    }

    public static BlockPos findFlatChunkPosition(WorldGenLevel world, BlockPos mainpos, int blockDiameter){
        int trueDiameter = blockDiameter-1;

        for (int i = 0;i <= 16-trueDiameter;i++){
            for (int g = 0;g <= 16-trueDiameter;g++){
                BlockPos iterator = mainpos.offset(i,world.getHeight(Heightmap.Types.WORLD_SURFACE_WG,mainpos.getX()+i,mainpos.getZ()+g)-1,g);
                if (checkIfFlat(world,iterator,trueDiameter)){
                    return iterator;
                }
            }
        }
        return Helpers.NULL_POS;
    }

    public static boolean checkIfFlat(WorldGenLevel world,BlockPos whereToCheck,int diameter){
        for (int i = 0;i <= diameter;i++){
            for (int g = 0;g <= diameter;g++){

                if ((world.getBlockState(whereToCheck.offset(i,1,g)).getBlock() != Blocks.AIR) ||
                        (world.getBlockState(whereToCheck.offset(i,0,g)).getBlock() == Blocks.AIR) ||
                        (world.getBlockState(whereToCheck.offset(i,0,g)).getBlock() instanceof LiquidBlock)){
                    return false;
                }
            }
        }
        return true;
    }
}
