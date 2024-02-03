package serv.util;

import org.jboss.netty.channel.Channel;
import serv.Server;
import serv.obj.Client;

public class ServerTools {


    public static Client getClientByChannel(Channel ctx){

        for(Client client : Server.live_clients){
            if(client.getClient_channel() == ctx){
                return client;
            }
        }
        return null;
    }



}
