package serv.packets.impl.sent;

//basic server connection packet letting client know that connection was successful


import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferOutputStream;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;

import java.io.IOException;

public class ServerConnectionPacket extends SPacket {

    public ServerConnectionPacket(Channel connection){
        this.connection = connection;
    }

    @Override
    public void send_packet() {

        try {
            //write packet number
            writer.writeByte(1);
            //write payload
            writer.writeUTF("Welcome...");
            //Finally send out buffer
            connection.write(writer.buffer());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
