package serv.SQL.stmnts;

public class getLogin {


    public static String getQuery(String username) {
        return "SELECT * from users WHERE username = '"+username+"'";
    }


}
