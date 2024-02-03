package client.packets;

import org.jboss.netty.buffer.ChannelBuffer;

public interface PacketHandler {

    public void handle(Packet p, ChannelBuffer buf);
}
