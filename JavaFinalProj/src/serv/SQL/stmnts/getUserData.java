package serv.SQL.stmnts;

import serv.Server;
import serv.packets.impl.sent.ErrorPacket;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getUserData {

    ResultSet rs;
    String query;

    public void setQuery(String username) {
        query = "SELECT * from user_info WHERE username = '"+username+"'";
    }

    public void query(){

        Connection new_sql_con = null;
        try {
            new_sql_con = Server.sql.getSQLCon();
            Statement sql_statement = new_sql_con.createStatement();
            this.rs = sql_statement.executeQuery(query);
            this.rs.next();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getFirstName(){
        try {
            if(this.rs != null) { //result set is not empty
                return this.rs.getString(2);
            } else { //result set is empty aka no user

                System.out.println("Data handling error"); //error finding user information
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getLastName(){
        try {
            if(this.rs != null) { //result set is not empty
                return this.rs.getString(3);
            } else { //result set is empty aka no user

                System.out.println("Data handling error"); //error finding user information
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getPhoneNumber(){
        try {
            if(this.rs != null) { //result set is not empty
                return this.rs.getString(4);
            } else { //result set is empty aka no user

                System.out.println("Data handling error"); //error finding user information
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getEmail(){
        try {
            if(this.rs != null) { //result set is not empty
                return this.rs.getString(5);
            } else { //result set is empty aka no user

                System.out.println("Data handling error"); //error finding user information
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
