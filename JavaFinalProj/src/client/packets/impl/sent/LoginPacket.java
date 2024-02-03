package client.packets.impl.sent;

import client.Client;
import client.tools.Hashing;

import java.io.IOException;

public class LoginPacket extends SPacket{

    String username;
    String password;


    public LoginPacket(String username,String password){
        this.username = username;
        this.password = password;
    }


    @Override
    public void send_packet() {


        try {
            //write packet number
            writer.writeByte(1);
            //write username
            writer.writeUTF(username);
            writer.writeUTF(password);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //send the written buffer to server
        Client.server_connection.write(writer.buffer());
    }
}
