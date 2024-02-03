package serv.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import serv.packets.Packet;
import serv.packets.PacketManager;

public class Decoder extends FrameDecoder{
    @Override
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer){
        Packet packet = new Packet(channelBuffer.readUnsignedByte(),channel);
        PacketManager.handle(packet,channelBuffer);
        return packet;
    }
}
