package client.packets.impl.received;

import client.packets.Packet;
import client.packets.PacketHandler;
import org.jboss.netty.buffer.ChannelBuffer;

public class DefaultPacketHandler implements PacketHandler {

    @Override
    public void handle(Packet p, ChannelBuffer buf) {
        int id = p.getId();
        System.out.println("Unhandled packet: " + id);
    }
}