package serv.packets.impl.sent;

import org.jboss.netty.channel.Channel;
import serv.obj.Client;
import serv.util.ServerTools;

import java.io.IOException;

public class UserUpdateSuccessfulPacket extends SPacket {
    public UserUpdateSuccessfulPacket(Channel connection){
        this.connection = connection;
    }
    @Override
    public void send_packet() {
        //user
        Client user = ServerTools.getClientByChannel(connection);
        if(user != null) {
            try {
                writer.writeByte(4);
                //write user data
                writer.writeUTF(user.getPassword());
                writer.writeUTF(user.getFirst_name());
                writer.writeUTF(user.getLast_name());
                writer.writeUTF(user.getEmail());
                writer.writeUTF(user.getPhone_number());
                //send buffer
                connection.write(writer.buffer());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
