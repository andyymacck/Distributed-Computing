package serv.packets.impl.sent;

import org.jboss.netty.channel.Channel;

import java.io.IOException;


public class ErrorPacket extends SPacket {

    byte error_code;

    public ErrorPacket(byte error_code, Channel connection){
        this.error_code = error_code;
        this.connection = connection;
    }

    @Override
    public void send_packet() {

        try {
            //send packet number
            writer.writeByte(3);
            //send error code
            writer.writeByte(this.error_code);

            //send buffer
            connection.write(writer.buffer());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
