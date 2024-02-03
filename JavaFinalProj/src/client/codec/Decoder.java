package client.codec;

import client.packets.Packet;
import client.packets.PacketManager;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class Decoder extends FrameDecoder {
    @Override
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer){
        Packet packet = new Packet(channelBuffer.readUnsignedByte(),channel);
        PacketManager.handle(packet,channelBuffer);
        return packet;
    }
}