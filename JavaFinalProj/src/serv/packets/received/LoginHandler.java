package serv.packets.received;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;
import serv.SQL.stmnts.getLogin;
import serv.Server;
import serv.obj.Client;
import serv.packets.Packet;
import serv.packets.PacketHandler;
import serv.packets.impl.sent.ErrorPacket;
import serv.packets.impl.sent.LoginSuccessfulPacket;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginHandler implements PacketHandler{



    @Override
    public void handle(Packet p, ChannelBuffer buf) {

        //init buffer
        ChannelBufferInputStream reader = new ChannelBufferInputStream(buf);

        try {
            //read username
            String username = reader.readUTF();
            String password = reader.readUTF();

            System.out.println("Attempting to login: username:["+username+"]");

            //perform sql checking

            try {
                Connection new_sql_con = Server.sql.getSQLCon();
                Statement sql_statement = new_sql_con.createStatement();
                ResultSet rs = sql_statement.executeQuery(getLogin.getQuery(username));
                if(rs.next()) { //result set is not empty
                    if (password.equals(rs.getString(3))) {
                        System.out.println("Login Successful, creating client obj: username:["+username+"]");
                        Client newClient = new Client(p.getChannel(),username,password);
                        //fill client info from database
                        newClient.getUserInformationFromDB();
                        //add new client to online list
                        boolean add = Server.addUserToServerList(newClient);
                        if(add) { //if the user could be added to the online listing
                            //send login success packet to client
                            LoginSuccessfulPacket packet = new LoginSuccessfulPacket(p.getChannel());
                            packet.send_packet();
                        } else {
                            System.out.println("Login Unsuccessful - multilog: username:["+username+"]");
                            ErrorPacket ep = new ErrorPacket((byte)4,p.getChannel());
                            ep.send_packet();
                        }

                    } else {
                        System.out.println("Login Unsuccessful: username:["+username+"]"); //error password matching
                        ErrorPacket ep = new ErrorPacket((byte)0,p.getChannel());
                        ep.send_packet();
                    }
                } else { //result set is empty aka no user
                    System.out.println("Login Unsuccessful: username:["+username+"]"); //error finding user information
                    ErrorPacket ep = new ErrorPacket((byte)0,p.getChannel());
                    ep.send_packet();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Attempt to test Login for validity using database.

    }

}
