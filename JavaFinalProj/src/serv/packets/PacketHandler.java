package serv.packets;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;


public interface PacketHandler {

    public void handle(Packet p, ChannelBuffer buf);
}
