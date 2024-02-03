package client.packets.impl.sent;

import client.Client;

import java.io.IOException;

public class RegistrationPacket extends SPacket {

    String username;
    String password;
    String first_name;
    String last_name;
    String email;
    String phone;



    public RegistrationPacket(String username, String password, String first_name, String last_name, String email, String phone){
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
    }


    @Override
    public void send_packet() {

        try {
            //write packet number
            writer.writeByte(2);
            //send data
            writer.writeUTF(username);
            writer.writeUTF(password);
            writer.writeUTF(first_name);
            writer.writeUTF(last_name);
            writer.writeUTF(email);
            writer.writeUTF(phone);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //send the written buffer to server
        Client.server_connection.write(writer.buffer());

    }
}
