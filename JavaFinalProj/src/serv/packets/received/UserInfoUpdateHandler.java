package serv.packets.received;


import serv.packets.Packet;
import serv.packets.PacketHandler;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;
import serv.obj.Client;
import serv.packets.impl.sent.ErrorPacket;
import serv.util.ServerTools;

import java.io.IOException;

public class UserInfoUpdateHandler implements PacketHandler {

    @Override
    public void handle(Packet p, ChannelBuffer buf) {
        ChannelBufferInputStream reader = new ChannelBufferInputStream(buf);

        try {

            String password = reader.readUTF();
            String first_name = reader.readUTF();
            String last_name = reader.readUTF();
            String phone_number = reader.readUTF();
            String email = reader.readUTF();

            Client user = ServerTools.getClientByChannel(p.getChannel());
            if(user != null){ //user exists so we can update it
                user.setPassword(password);
                user.setFirst_name(first_name);
                user.setLast_name(last_name);
                user.setPhone_number(phone_number);
                user.setEmail(email);
                user.updateUserInformationOnDB();
            } else {
                //send update error null account
                ErrorPacket ep = new ErrorPacket((byte)5,p.getChannel());
                ep.send_packet();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
