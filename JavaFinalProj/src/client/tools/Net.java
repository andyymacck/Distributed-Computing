package client.tools;

import client.packets.impl.sent.LoginPacket;
import client.packets.impl.sent.RegistrationPacket;
import client.packets.impl.sent.UserInformationUpdatePacket;

public class Net {

    public static void login(String username,String password){
        LoginPacket lp = new LoginPacket(username,password);
        lp.send_packet();
    }

    public static void updateInformation(String password,String first_name,String last_name,String email,String phone){
        UserInformationUpdatePacket up = new UserInformationUpdatePacket(password,first_name,last_name,phone,email);
        up.send_packet();
    }

    public static void registerAccount(String username, String password, String first_name, String last_name, String email, String phone){
        RegistrationPacket rp = new RegistrationPacket(username, password, first_name, last_name, email, phone);
        rp.send_packet();
    }

    public static void loginPacketReceived(){

    }

    public static void updateInformationPacketReceived(){

    }

    public static void registrationPacketReceived(){
        System.out.println("Registration successful!");
    }

    public static void loginFail(int code){
        switch(code){
            case 0: System.out.println("Login Failed: Invalid username or password");
                break;
            case 1: System.out.println("Login Failed: Username already in use");
                break;
        }
    }

    public static void registrationFail(int code){
        switch(code){
            case 0: System.out.println("Registration Failed: Username is already registered");
                break;
            case 1: System.out.println("Registration Failed: Email is already in use");
                break;
            case 2:  System.out.println("Registration Failed: Unknown network error");
                break;

        }
    }

    public static void informationUpdateFail(int code){
        switch(code){
            case 0:
                System.out.println("Information Update Failed: User not logged in");
                break;
        }
    }

    public static void unhandledError(){
        System.out.println("Unhandled Error");
    }
}
