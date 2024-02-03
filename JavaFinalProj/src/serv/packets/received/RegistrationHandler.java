package serv.packets.received;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;
import serv.SQL.stmnts.createUser;
import serv.packets.Packet;
import serv.packets.PacketHandler;
import serv.packets.impl.sent.ErrorPacket;
import serv.packets.impl.sent.RegistrationSuccessPacket;

import java.io.IOException;

public class RegistrationHandler implements PacketHandler {
    @Override
    public void handle(Packet p, ChannelBuffer buf) {
        ChannelBufferInputStream reader = new ChannelBufferInputStream(buf);
        //start reading data received from client
        try {
            String username = reader.readUTF();
            String password = reader.readUTF();
            String first_name = reader.readUTF();
            String last_name = reader.readUTF();
            String email = reader.readUTF();
            String phone = reader.readUTF();



            createUser user_creation_sql = new createUser();
            user_creation_sql.setQuery(username,password,first_name,last_name,email,phone,p.getChannel());
            boolean result = user_creation_sql.query();

            if(result){
                System.out.println("User creation success: username:["+username+"]");
                RegistrationSuccessPacket regSucc = new RegistrationSuccessPacket(p.getChannel());
                regSucc.send_packet();
            } else {
                System.out.println("User creation fail");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
