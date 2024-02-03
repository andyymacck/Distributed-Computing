package client.packets.impl.received;

import client.packets.Packet;
import client.packets.PacketHandler;
import client.tools.Net;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;

import java.io.IOException;

public class ErrorPacketHandler implements PacketHandler {
    @Override
    public void handle(Packet p, ChannelBuffer buf) {

        ChannelBufferInputStream reader = new ChannelBufferInputStream(buf);

        //read error bytecode

        try {
            byte error_num = reader.readByte();
            //handler known errors
            switch (error_num){
                case 0:
                    Net.loginFail(0);
                break;
                case 1:
                    Net.registrationFail(0);
                break;
                case 2:
                    Net.registrationFail(1);
                break;
                case 3:
                    Net.registrationFail(2);
                break;
                case 4:
                    Net.loginFail(1);
                break;
                case 5:
                    Net.informationUpdateFail(0);
                break;
                default:
                    Net.unhandledError();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
