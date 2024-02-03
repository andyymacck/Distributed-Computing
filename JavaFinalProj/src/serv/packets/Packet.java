package serv.packets;

import org.jboss.netty.channel.Channel;

public class Packet {


    private final int id;
    private final Channel channel;

    public int getId(){
        return this.id;
    }

    public Channel getChannel(){
        return this.channel;
    }

    public Packet(int id, Channel channel){
        this.id = id;
        this.channel = channel;
    }
}
