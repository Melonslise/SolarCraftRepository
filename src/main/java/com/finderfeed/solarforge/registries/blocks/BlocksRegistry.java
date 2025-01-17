package com.finderfeed.solarforge.registries.blocks;

import com.finderfeed.solarforge.magic_items.blocks.infusing_table_things.infusing_pool.InfusingPool;
import com.finderfeed.solarforge.magic_items.blocks.BlueGemDoorBlock;
import com.finderfeed.solarforge.magic_items.blocks.*;
import com.finderfeed.solarforge.magic_items.decoration_blocks.SolarFlower;
import com.finderfeed.solarforge.world_generation.structures.blocks.ColdStarInfuser;
import com.finderfeed.solarforge.world_generation.structures.blocks.InvincibleStone;
import com.finderfeed.solarforge.world_generation.structures.blocks.KeyDefender;
import com.finderfeed.solarforge.world_generation.structures.blocks.KeyLockBlock;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.common.ToolType;

import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlocksRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,"solarforge");
    public  static  final RegistryObject<InfusingPool> SOLAR_POOL = BLOCKS.register("solar_forge_infusion_pool",()-> new InfusingPool(BlockBehaviour.Properties.of(Material.STONE)
            .sound(SoundType.METAL)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(1)
            .requiresCorrectToolForDrops()
            .noOcclusion()
            .strength(3,3)));
    public  static  final RegistryObject<SolarStoneBlock> SOLAR_STONE = BLOCKS.register("solar_stone",()-> new SolarStoneBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE)));
    public  static  final RegistryObject<Block> SOLAR_STONE_CHISELED = BLOCKS.register("chiseled_solar_stone",()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public  static  final RegistryObject<Block> SOLAR_STONE_COLLUMN = BLOCKS.register("solar_stone_collumn",()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public  static  final RegistryObject<Block> SOLAR_STONE_BRICKS = BLOCKS.register("solar_stone_bricks",()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public  static  final RegistryObject<Block> SOLAR_STONE_STAIRS = BLOCKS.register("solar_stone_stairs",()-> new StairBlock(()-> SOLAR_STONE_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));
    public  static  final RegistryObject<Block> SOLAR_STONE_COLLUMN_HORIZONTAL = BLOCKS.register("solar_stone_collumn_horizontal",()-> new GlazedTerracottaBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public  static  final RegistryObject<Block> KEY_LOCK_BLOCK = BLOCKS.register("key_lock_block",()-> new KeyLockBlock());
    public  static  final RegistryObject<Block> INVINCIBLE_STONE = BLOCKS.register("invincible_solar_stone",()-> new InvincibleStone());
    public  static  final RegistryObject<Block> COLD_STAR_INFUSER = BLOCKS.register("cold_star_charger",()-> new ColdStarInfuser(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public  static  final RegistryObject<Block> KEY_DEFENDER = BLOCKS.register("defence_trap_block",()-> new KeyDefender(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public  static  final RegistryObject<Block> SOLAR_LENS = BLOCKS.register("solar_lens",()-> new SolarLensBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.GLASS).noOcclusion()));
    public  static  final RegistryObject<Block> MAGNET_BLOCK = BLOCKS.register("magnet_block",()-> new MagnetBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public  static  final RegistryObject<Block> SOLAR_ENERGY_GENERATOR = BLOCKS.register("solar_energy_generator",()-> new SolarEnergyGenerator(BlockBehaviour.Properties.copy(Blocks.ANDESITE).noOcclusion()));
    public  static  final RegistryObject<Block> SOLAR_REPEATER = BLOCKS.register("solar_energy_repeater",()-> new SolarRepeaterBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE).noOcclusion()));
    public  static  final RegistryObject<Block> ILLIDIUM_BLOCK = BLOCKS.register("illidium_block",()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public  static  final RegistryObject<Block> ALGADIUM_BLOCK = BLOCKS.register("algadium_block",()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public  static  final RegistryObject<Block> SOLAR_CORE = BLOCKS.register("solar_core_block",()-> new SolarCoreBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public  static  final RegistryObject<Block> AURA_HEALER_BLOCK = BLOCKS.register("aura_healer_block",()-> new AuraHealerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public  static  final RegistryObject<Block> SOLAR_MORTAR_BLOCK = BLOCKS.register("solar_mortar_block",()-> new SolarMortar(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public  static  final RegistryObject<Block> SOLAR_FLOWER = BLOCKS.register("solar_flower",()-> new SolarFlower(BlockBehaviour.Properties.copy(Blocks.DEAD_BUSH)));
    public  static  final RegistryObject<Block> DEAD_SPROUT = BLOCKS.register("dead_sprout",()-> new FlowerBlock(MobEffects.BLINDNESS,2,BlockBehaviour.Properties.copy(Blocks.POPPY)));
    public  static  final RegistryObject<Block> SOLAR_FURNACE = BLOCKS.register("solar_furnace",()-> new SolarEnergyFurnace(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public  static  final RegistryObject<Block> TURRET_BLOCK = BLOCKS.register("turret_block",()-> new TurretBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));
    public  static  final RegistryObject<UldoradiumOre> ULDORADIUM_ORE = BLOCKS.register("uldoradium_ore",()-> new UldoradiumOre(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public  static  final RegistryObject<RayTrapBlock> RAY_TRAP_BLOCK = BLOCKS.register("ray_trap_block",()-> new RayTrapBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK).noOcclusion()));
    public  static  final RegistryObject<TrapStructureController> TRAP_CONTROLLER = BLOCKS.register("trap_controller",()-> new TrapStructureController(BlockBehaviour.Properties.copy(Blocks.BEDROCK)));
    public  static  final RegistryObject<BlueGemDoorBlock> BLUE_GEM_DOOR_BLOCK = BLOCKS.register("blue_gem_door_block",()-> new BlueGemDoorBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK)));
    public  static  final RegistryObject<LeavesBlock> ASH_LEAVES = BLOCKS.register("ash_leaves",()-> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noOcclusion()));
    public  static  final RegistryObject<RotatedPillarBlock> BURNT_LOG = BLOCKS.register("burnt_log",()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public  static  final RegistryObject<Block> RUNE_ENERGY_PYLON = BLOCKS.register("rune_energy_pylon",()-> new RuneEnergyPylonBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK).noOcclusion()));
    public  static  final RegistryObject<Block> RUNIC_TABLE = BLOCKS.register("runic_table",()-> new RunicTableBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));
    public  static  final RegistryObject<LeavesBlock> RUNIC_LEAVES = BLOCKS.register("runic_leaves",()-> new RunicLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noOcclusion()));
    public  static  final RegistryObject<RotatedPillarBlock> RUNIC_LOG = BLOCKS.register("runic_log",()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public  static  final RegistryObject<Block> RUNIC_PLANKS = BLOCKS.register("runic_planks",()-> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
   // public  static  final RegistryObject<Block> SOLAR_CHEST = BLOCKS.register("solar_chest",()-> new ChestBlock(AbstractBlock.Properties.copy(Blocks.STONE), ()->TileEntityType.CHEST));
}
