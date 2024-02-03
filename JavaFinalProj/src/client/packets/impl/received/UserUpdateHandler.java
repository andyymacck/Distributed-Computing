package client.packets.impl.received;

import client.Client;
import client.packets.Packet;
import client.packets.PacketHandler;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;

import java.io.IOException;

public class UserUpdateHandler implements PacketHandler{
    @Override
    public void handle(Packet p, ChannelBuffer buf) {
        ChannelBufferInputStream reader = new ChannelBufferInputStream(buf);

        try {
            String password = reader.readUTF();
            String first_name = reader.readUTF();
            String last_name = reader.readUTF();
            String email = reader.readUTF();
            String phone = reader.readUTF();

            Client.user.setPassword(password);
            Client.user.setFirst_name(first_name);
            Client.user.setLast_name(last_name);
            Client.user.setEmail(email);
            Client.user.setPhone_number(phone);


            System.err.println("User update successful");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
