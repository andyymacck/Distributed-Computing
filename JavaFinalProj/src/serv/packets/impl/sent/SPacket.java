package serv.packets.impl.sent;

//base class for sent packets

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferOutputStream;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;

public abstract class SPacket {

    Channel connection;

    ChannelBuffer buf = ChannelBuffers.dynamicBuffer();
    ChannelBufferOutputStream writer = new ChannelBufferOutputStream(buf);


    public abstract void send_packet();

}
