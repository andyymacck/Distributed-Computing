package client.packets.impl.received;

import client.packets.Packet;
import client.packets.PacketHandler;
import client.packets.impl.sent.LoginPacket;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;
import org.jboss.netty.channel.Channel;

import java.io.IOException;

public class ServerConnectionHandler implements PacketHandler {


    @Override
    public void handle(Packet p, ChannelBuffer buf) {
        //create channelbufferinput stream for reading multiple formats
        ChannelBufferInputStream reader = new ChannelBufferInputStream(buf);
        String random_string = null;

        try {
            random_string = reader.readUTF();
            System.out.println("Successfully connected to the server " + random_string);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
