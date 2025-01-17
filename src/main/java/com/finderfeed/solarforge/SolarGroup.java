package com.finderfeed.solarforge;

import com.finderfeed.solarforge.registries.items.ItemsRegister;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;

public class SolarGroup extends CreativeModeTab {
    public SolarGroup(String label) {
        super(label);
        this.setBackgroundImage(new ResourceLocation("solarforge","textures/gui/solar_items_tab.png"));
    }

    @Override
    public ItemStack makeIcon() {
        return SolarForge.TEST_ITEM.get().getDefaultInstance();
    }

    @Override
    public ResourceLocation getTabsImage() {
        return new ResourceLocation("solarforge","textures/gui/solar_items_tabs.png");
    }
}

class SolarGroupBlocks extends CreativeModeTab{

    public SolarGroupBlocks(String label) {
        super(label);
        this.setBackgroundImage(new ResourceLocation("solarforge","textures/gui/solar_items_tab.png"));
    }


    @Override
    public ResourceLocation getTabsImage() {
        return new ResourceLocation("solarforge","textures/gui/solar_items_tabs.png");
    }
    @Override
    public ItemStack makeIcon() {
        return ItemsRegister.SOLAR_STONE_BRICKS.get().getDefaultInstance();
    }
}

class SolarGroupTools extends CreativeModeTab{

    public SolarGroupTools(String label) {
        super(label);
        this.setBackgroundImage(new ResourceLocation("solarforge","textures/gui/solar_items_tab.png"));
    }


    @Override
    public ResourceLocation getTabsImage() {
        return new ResourceLocation("solarforge","textures/gui/solar_items_tabs.png");
    }
    @Override
    public ItemStack makeIcon() {
        return ItemsRegister.ILLIDIUM_SWORD.get().getDefaultInstance();
    }
}

class SolarGroupFragments extends CreativeModeTab{

    public SolarGroupFragments(String label) {
        super(label);
        this.setBackgroundImage(new ResourceLocation("solarforge","textures/gui/solar_items_tab.png"));
    }


    @Override
    public ResourceLocation getTabsImage() {
        return new ResourceLocation("solarforge","textures/gui/solar_items_tabs.png");
    }
    @Override
    public ItemStack makeIcon() {
        return ItemsRegister.INFO_FRAGMENT.get().getDefaultInstance();
    }
}
