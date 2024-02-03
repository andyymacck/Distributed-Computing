package client.packets;

import client.packets.impl.received.*;
import org.jboss.netty.buffer.ChannelBuffer;

public class PacketManager {

    private static final PacketHandler[] mass = new PacketHandler[255];

    public static void handle(Packet p, ChannelBuffer buffer){
        if(mass[p.getId()] != null) {
            mass[p.getId()].handle(p, buffer);
            return;
        }
        mass[0].handle(p,buffer);
    }

    static {
        mass[0] = new DefaultPacketHandler();
        mass[1] = new ServerConnectionHandler();
        mass[2] = new LoginSuccessHandler();
        mass[3] = new ErrorPacketHandler();
        mass[4] = new RegistrationSuccessHandler();
    }

}
