package com.finderfeed.solarforge.packet_handler.packets;

import com.finderfeed.solarforge.ClientHelpers;
import com.finderfeed.solarforge.misc_things.AbstractPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class ProcImmortalityTotemAnimation extends AbstractPacket {



    public ProcImmortalityTotemAnimation(FriendlyByteBuf buf) {}
    public ProcImmortalityTotemAnimation(){}
    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(()->{
            DistExecutor.safeRunWhenOn(Dist.CLIENT,()-> ClientHelpers::playTotemAnimation);
        });
        ctx.get().setPacketHandled(true);
    }
}
