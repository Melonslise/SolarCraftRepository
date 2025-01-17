package com.finderfeed.solarforge.packet_handler;

import com.finderfeed.solarforge.Helpers;
import com.finderfeed.solarforge.magic_items.items.solar_lexicon.achievements.Achievement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class ResetAllAbilitiesPacket {

    public ResetAllAbilitiesPacket(FriendlyByteBuf buf){


    }
    public ResetAllAbilitiesPacket(){

    }
    public void toBytes(FriendlyByteBuf buf){

    }
    public void handle(Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{
            ServerPlayer enti = ctx.get().getSender();
            Player entity = (Player)enti;
            enti.getPersistentData().putBoolean("solar_forge_can_player_use_fireball",false);
            enti.getPersistentData().putBoolean("solar_forge_can_player_use_lightning",false);
            enti.getPersistentData().putBoolean("solar_forge_can_player_use_solar_strike",false);
            enti.getPersistentData().putBoolean("solar_forge_can_player_use_solar_stun",false);
            enti.getPersistentData().putBoolean("solar_forge_can_player_use_meteorite",false);
            enti.getPersistentData().putBoolean("solar_forge_can_player_use_solar_heal",false);
            enti.getPersistentData().putBoolean("solar_forge_can_player_use_alchemist",false);
            enti.getPersistentData().putBoolean("solar_forge_can_player_use_solar_dispel",false);
            for (Achievement a : Achievement.ALL_ACHIEVEMENTS){
                Helpers.setAchievementStatus(a,entity,false);

            }

        });
        ctx.get().setPacketHandled(true);
    }
}
