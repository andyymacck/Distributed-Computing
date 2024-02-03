package serv.packets.received;

import org.jboss.netty.buffer.ChannelBuffer;
import serv.packets.Packet;
import serv.packets.PacketHandler;

public class DefaultPacketHandler implements PacketHandler{

    @Override
    public void handle(Packet p, ChannelBuffer buf) {
        int id = p.getId();
        System.out.println("Unhandled packet: " + id);
    }
}
