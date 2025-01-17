package com.finderfeed.solarforge.world_generation.structures.maze_key_keeper;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

public class MazeStructure extends StructureFeature<NoneFeatureConfiguration> {
    public MazeStructure(Codec<NoneFeatureConfiguration> codec){
        super(codec);
    }





    @Override
    public StructureFeature.StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return MazeStructure.Start::new;
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, WorldgenRandom chunkRandom, ChunkPos pos, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration featureConfig,LevelHeightAccessor access) {
        BlockPos centerOfChunk = new BlockPos((pos.x << 4) + 7, 0, (pos.z << 4) + 7);
        int landHeight = chunkGenerator.getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG,access);
        NoiseColumn columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ(),access);
        BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));
        return topBlock.getFluidState().isEmpty();
    }


    public static class Start extends StructureStart<NoneFeatureConfiguration> {


        public Start(StructureFeature<NoneFeatureConfiguration> p_163595_, ChunkPos p_163596_, int p_163597_, long p_163598_) {
            super(p_163595_, p_163596_, p_163597_, p_163598_);
        }

        @Override
        public void generatePieces(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager templateManagerIn, ChunkPos pos, Biome biomeIn, NoneFeatureConfiguration config,LevelHeightAccessor access) {
            Rotation rotation = Rotation.values()[this.random.nextInt(Rotation.values().length)];

            // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
            int x = (pos.x << 4) + 7;
            int z = (pos.z << 4) + 7;

            // Finds the y value of the terrain at location.
            int surfaceY = chunkGenerator.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG,access);
            BlockPos blockpos = new BlockPos(x, surfaceY, z);

            // Now adds the structure pieces to this.components with all details such as where each part goes
            // so that the structure can be added to the world by worldgen.
            MazeStructurePieces.start(templateManagerIn, blockpos, rotation, this.pieces, this.random);

            // Sets the bounds of the structure.


            // I use to debug and quickly find out if the structure is spawning or not and where it is.
            //SolarForge.LOGGER.log(Level.DEBUG, "Structure at " + (blockpos.getX()) + " " + blockpos.getY() + " " + (blockpos.getZ()));
        }


    }
}
