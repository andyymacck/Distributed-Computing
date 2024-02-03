package serv.SQL.stmnts;

import serv.Server;
import serv.packets.impl.sent.ErrorPacket;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class updateUserData {

    String query;
    String update_password_query;

    public void setQuery(String username,String password,String firstName, String lastName, String email, String phone) {
        query = "UPDATE user_info " +
                "SET first_name = '"+firstName+"',last_name = '"+lastName+"',phone = '"+phone+"',email = '"+email+"'" +
                "WHERE username = '"+username+"'";
        update_password_query = "UPDATE users " +
                "SET password = '"+password+"'" +
                "WHERE username ='"+username+"'";
    }


    public boolean query(){
        Connection new_sql_con = null;
        try {
            new_sql_con = Server.sql.getSQLCon();
            Statement sql_statement = new_sql_con.createStatement();
            int i = sql_statement.executeUpdate(query);
            if(i > 0){
                sql_statement = new_sql_con.createStatement();
                i = sql_statement.executeUpdate(update_password_query);
                new_sql_con.close();
                if(i > 0){
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
