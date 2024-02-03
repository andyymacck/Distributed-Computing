package client.packets.impl.sent;

import client.Client;

import java.io.IOException;

public class UserInformationUpdatePacket extends SPacket {


    String password;
    String first_name;
    String last_name;
    String phone_number;
    String email;

    public UserInformationUpdatePacket(String password,String first_name,String last_name,String phone_number,String email){
        try {
            //write packet number
            writer.writeByte(3);
            //write info to buffer
            writer.writeUTF(password);
            writer.writeUTF(first_name);
            writer.writeUTF(last_name);
            writer.writeUTF(phone_number);
            writer.writeUTF(email);

            //send buffer
            Client.server_connection.write(writer.buffer());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void send_packet() {



    }
}
