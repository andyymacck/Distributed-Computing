package client.packets.impl.received;

import client.Client;
import client.obj.ClientObj;
import client.packets.impl.sent.LoginPacket;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;
import client.packets.Packet;
import client.packets.PacketHandler;

import java.io.IOException;

public class LoginSuccessHandler implements PacketHandler {
    @Override
    public void handle(Packet p, ChannelBuffer buf) {
        //create channelbufferinput stream for reading multiple formats
        ChannelBufferInputStream reader = new ChannelBufferInputStream(buf);
        String random_string = null;

        try {
            //read in information
            String username = reader.readUTF();
            String password = reader.readUTF();
            String first_name = reader.readUTF();
            String last_name = reader.readUTF();
            String email = reader.readUTF();
            String phone = reader.readUTF();

            //set up local client obj
            Client.user = new ClientObj(username,password);
            //set vars
            Client.user.setFirst_name(first_name);
            Client.user.setLast_name(last_name);
            Client.user.setEmail(email);
            Client.user.setPhone_number(phone);

            //alert client
            System.out.println("Successfully logged in, Welcome " +  Client.user.getFirst_name());



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
