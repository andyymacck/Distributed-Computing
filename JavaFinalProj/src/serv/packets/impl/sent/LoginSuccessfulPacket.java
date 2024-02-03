package serv.packets.impl.sent;

import org.jboss.netty.channel.Channel;
import serv.obj.Client;
import serv.packets.Packet;
import serv.util.ServerTools;

import java.io.IOException;

public class LoginSuccessfulPacket extends SPacket {

    public LoginSuccessfulPacket(Channel connection){
        this.connection = connection;
    }
    @Override
    public void send_packet() {
        try {

            Client user = ServerTools.getClientByChannel(this.connection);
            if(user != null) { //if we find the user obj, lets inform the client connec
                //write packet number
                writer.writeByte(2);
                //write payload
                writer.writeUTF(user.getUsername());
                writer.writeUTF(user.getPassword());
                writer.writeUTF(user.getFirst_name());
                writer.writeUTF(user.getLast_name());
                writer.writeUTF(user.getEmail());
                writer.writeUTF(user.getPhone_number());
                //Finally send out buffer
                connection.write(writer.buffer());
            } else {
                System.err.println("There was an error handling a client login on channel: " + this.connection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
