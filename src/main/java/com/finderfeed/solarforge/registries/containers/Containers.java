package com.finderfeed.solarforge.registries.containers;

import com.finderfeed.solarforge.magic_items.blocks.blockentities.containers.RunicTableContainer;
import com.finderfeed.solarforge.magic_items.blocks.blockentities.containers.SolarFurnaceContainer;
import com.finderfeed.solarforge.magic_items.items.solar_lexicon.SolarLexiconContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeContainerType;

import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Containers {
    public static final DeferredRegister<MenuType<?>> CONTAINER_TYPE = DeferredRegister.create(ForgeRegistries.CONTAINERS,"solarforge");
    public static final RegistryObject<MenuType<SolarFurnaceContainer>> SOLAR_FURNACE_CONTAINER = CONTAINER_TYPE.register("solar_furnace_container",()-> IForgeContainerType.create(SolarFurnaceContainer::new));
    public static final RegistryObject<MenuType<RunicTableContainer>> RUNIC_TABLE_CONTAINER = CONTAINER_TYPE.register("runic_table_container",()-> IForgeContainerType.create(RunicTableContainer::new));
    public static final RegistryObject<MenuType<SolarLexiconContainer>> SOLAR_LEXICON_CONTAINER = CONTAINER_TYPE.register("solar_lexicon_container",()-> IForgeContainerType.create(SolarLexiconContainer::new));
}
