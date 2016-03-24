package com.example.alex_lenovi.faultboxcontroller;

/**
 * Created by alex-lenovi on 3/23/2016.
 */
public class clsRelay {
    String relay_title = "";
    String relay_num = "";

    public clsRelay(String relay_title, String relay_num) {
        this.relay_title = relay_title;
        this.relay_num = relay_num;
    }

    public String getRelayTitle() {
        return relay_title;
    }

    public void setRelayTitle(String relay_title) {
        this.relay_title = relay_title;
    }

    public String getRelayNum() {
        return relay_num;
    }

    public void setRelayNum(String relay_num) {
        this.relay_num = relay_num;
    }

    public String getONCommand() {
        clsConnection.getInstance().send("relay/" + relay_num + "/on");
        return "relay/" + relay_num + "/on";
    }

    public String getOFFCommand() {
        clsConnection.getInstance().send("relay/" + relay_num + "/off");
        return "relay/" + relay_num + "/off";
    }

    public String getFlickerCommand() {
        clsConnection.getInstance().send("relay/" + relay_num + "/flicker");
        return "relay/" + relay_num + "/flicker";
    }

}