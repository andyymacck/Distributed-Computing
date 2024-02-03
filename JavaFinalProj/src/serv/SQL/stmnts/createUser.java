package serv.SQL.stmnts;

import jdk.nashorn.internal.runtime.linker.Bootstrap;
import org.jboss.netty.channel.Channel;
import serv.SQL.MySQLCon;
import serv.Server;
import serv.packets.impl.sent.ErrorPacket;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class createUser {


    String user_table_insert;
    String user_info_table_insert;
    String username;
    String email;
    Channel connection;

    public void setQuery(String username, String password, String first_name, String last_name, String email, String phone, Channel connection){

        //store these values so we can test them later
        this.username = username;
        this.email = email;
        this.connection = connection;

        user_table_insert = "INSERT INTO users (username,password)" +
                "VALUES ('"+username+"','"+password+"')";
        user_info_table_insert = "INSERT INTO user_info (username,first_name,last_name,phone,email)" +
                "VALUES ('"+username+"','"+first_name+"','"+last_name+"','"+phone+"','"+email+"')";

    }

    public boolean query(){

        if(checkUsernameAvailability(this.username)){
            if(checkEmailAvailability(this.email)){

                try {
                    Connection new_con = Server.sql.getSQLCon();
                    PreparedStatement stmt = new_con.prepareStatement(user_table_insert,Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement stmnt2 = new_con.prepareStatement(user_info_table_insert,Statement.RETURN_GENERATED_KEYS);
                    int result = stmt.executeUpdate();
                    if(result != 0){
                        result = stmnt2.executeUpdate();
                        if(result != 0){
                            return true;
                        }
                    } else {
                        ErrorPacket ep = new ErrorPacket((byte)3,connection);
                        ep.send_packet();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                System.err.println("Registration error: email is already registered");
                ErrorPacket ep = new ErrorPacket((byte)2,connection);
                ep.send_packet();
            }
        } else {
            System.err.println("Registration error: username is already registered");
            ErrorPacket ep = new ErrorPacket((byte)1,connection);
            ep.send_packet();
        }
        return false;
    }



    public boolean checkUsernameAvailability(String username){

        try {
            Connection new_con = Server.sql.getSQLCon();
            Statement stmt = new_con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from users WHERE username ='"+username+"'");
            if(rs.next()){
                //fail the db has records of same username
                new_con.close();
                return false;
            } //no records exist return true
            new_con.close();
            return true;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //possible error
        return false;
    }

    public boolean checkEmailAvailability(String email){

        try {
            Connection new_con = Server.sql.getSQLCon();
            Statement stmt = new_con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from user_info WHERE email ='"+email+"'");
            if(rs.next()){
                //fail the db has records of same username
                new_con.close();
                return false;
            } //no records exist return true
            new_con.close();
            return true;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //possible error
        return false;

    }


}
