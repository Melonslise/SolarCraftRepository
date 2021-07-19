package com.finderfeed.solarforge.magic_items.items.solar_lexicon.packets;

import com.finderfeed.solarforge.ClientHelpers;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateAllProgressionOnClient {
    public UpdateAllProgressionOnClient(PacketBuffer buf){

    }
    public UpdateAllProgressionOnClient(){

    }

    public void toBytes(PacketBuffer buf) {

    }


    public void handle(Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(()->{
                ClientHelpers.reloadProgression(ctx.get().getSender());
        });
        ctx.get().setPacketHandled(true);
    }
}