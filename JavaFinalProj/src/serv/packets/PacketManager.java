package serv.packets;

import org.jboss.netty.buffer.ChannelBuffer;
import serv.packets.received.DefaultPacketHandler;
import serv.packets.received.LoginHandler;
import serv.packets.received.RegistrationHandler;
import serv.packets.received.UserInfoUpdateHandler;

public final class PacketManager {

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
        mass[1] = new LoginHandler();
        mass[2] = new RegistrationHandler();
        mass[3] = new UserInfoUpdateHandler();
    }

}



