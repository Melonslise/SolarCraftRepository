package com.finderfeed.solarforge.events.hotkey_use_event;


import com.finderfeed.solarforge.SolarForgeClientRegistry;
import com.finderfeed.solarforge.packet_handler.ResetAllAbilitiesPacket;
import com.finderfeed.solarforge.packet_handler.SolarForgePacketHandler;
import com.finderfeed.solarforge.packet_handler.CastAbilityPacket;
import com.finderfeed.solarforge.magic_items.blocks.solar_forge_block.solar_forge_screen.SolarForgeAbilityConfigScreen;
import com.finderfeed.solarforge.magic_items.items.solar_lexicon.achievements.achievement_tree.AchievementTree;
import com.finderfeed.solarforge.magic_items.items.solar_lexicon.achievements.Achievement;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraftforge.client.event.InputEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = "solarforge",bus = Mod.EventBusSubscriber.Bus.FORGE,value = Dist.CLIENT)
public class HotkeyEventListener {



    @SubscribeEvent
    public static void ListenToEvent(final InputEvent.KeyInputEvent event){

        if (SolarForgeClientRegistry.FIRST_ABILITY_KEY.isDown() && event.getAction() == GLFW.GLFW_PRESS){

            SolarForgePacketHandler.INSTANCE.sendToServer(new CastAbilityPacket(1));

        }
        if (SolarForgeClientRegistry.SECOND_ABILITY_KEY.isDown() && event.getAction() == GLFW.GLFW_PRESS){

            SolarForgePacketHandler.INSTANCE.sendToServer(new CastAbilityPacket(2));

        }
        if (SolarForgeClientRegistry.THIRD_ABILITY_KEY.isDown() && event.getAction() == GLFW.GLFW_PRESS){

            SolarForgePacketHandler.INSTANCE.sendToServer(new CastAbilityPacket(3));

        }
        if (SolarForgeClientRegistry.FORTH_ABILITY_KEY.isDown() && event.getAction() == GLFW.GLFW_PRESS){

            SolarForgePacketHandler.INSTANCE.sendToServer(new CastAbilityPacket(4));

            System.out.println(AchievementTree.loadTree().getAchievementRequirements(Achievement.USE_SOLAR_INFUSER));
        }
        if (SolarForgeClientRegistry.ADMIN_ABILITY_KEY.isDown() && event.getAction() == GLFW.GLFW_PRESS){

            SolarForgePacketHandler.INSTANCE.sendToServer(new ResetAllAbilitiesPacket());

        }
        if (SolarForgeClientRegistry.OPEN_GUI_ABILITY_KEY.isDown() && event.getAction() == GLFW.GLFW_PRESS){

            Minecraft.getInstance().setScreen(new SolarForgeAbilityConfigScreen());

        }


    }


}
