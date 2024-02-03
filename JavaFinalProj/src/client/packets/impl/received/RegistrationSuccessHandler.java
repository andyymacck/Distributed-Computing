package client.packets.impl.received;

import client.packets.Packet;
import client.packets.PacketHandler;
import client.tools.Net;
import org.jboss.netty.buffer.ChannelBuffer;

public class RegistrationSuccessHandler implements PacketHandler {
    @Override
    public void handle(Packet p, ChannelBuffer buf) {
        Net.registrationPacketReceived();
    }
}
