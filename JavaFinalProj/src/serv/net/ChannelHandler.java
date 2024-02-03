package serv.net;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferOutputStream;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import serv.Server;
import serv.obj.Client;
import serv.packets.impl.sent.SPacket;
import serv.packets.impl.sent.ServerConnectionPacket;
import serv.util.ServerTools;

public class ChannelHandler extends SimpleChannelHandler{

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println(ctx.getChannel().getRemoteAddress()+" Connected...");
        Channel connection = ctx.getChannel();

        ServerConnectionPacket packet = new ServerConnectionPacket(connection);
        packet.send_packet();

    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        //ClientObj disconnect... remove them from live client list
        ctx.getChannel().getRemoteAddress();
        System.out.println(ctx.getChannel().getRemoteAddress()+" Disconnected..");
        Client user = ServerTools.getClientByChannel(ctx.getChannel());
        if(Server.live_clients.contains(user)){
            Server.live_clients.remove(user);
            System.out.println("User removed");
        }
    }
}
