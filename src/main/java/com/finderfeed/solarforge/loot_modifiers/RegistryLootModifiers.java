package com.finderfeed.solarforge.loot_modifiers;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,modid = "solarforge")
public class RegistryLootModifiers {


    @SubscribeEvent
    public static void registerThemAll(final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event){
        event.getRegistry().register(new SmeltingLootModifier.Serializer().setRegistryName(new ResourceLocation("solarforge","smelting")));
    }
}
