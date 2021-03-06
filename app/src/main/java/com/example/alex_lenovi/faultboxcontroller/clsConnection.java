package com.example.alex_lenovi.faultboxcontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by alex-lenovi on 3/23/2016.
 */
public class clsConnection {

    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static String address = "98:D3:32:10:45:57"; //HC-06
    private BluetoothDevice device;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;
    private Boolean isConnected = false;

    private Context context;

    private static clsConnection ourInstance = new clsConnection();

    public static clsConnection getInstance() {
        return ourInstance;
    }

    private clsConnection() {

    }

    public void setContext(Context context){
        this.context = context;
    }


    public void connect() {
        if (btAdapter==null){
            btAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        if (btAdapter.isEnabled()){
            btAdapter.cancelDiscovery();
            if(BluetoothAdapter.checkBluetoothAddress(address)){
                device = btAdapter.getRemoteDevice(address);
                try {
                    btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
                } catch (IOException e) {
                    Toast.makeText(context,"Socket creation failure: " + e.getMessage() + ".",Toast.LENGTH_SHORT).show();
                    //return;
                }
                try {
                    btSocket.connect();
                } catch (IOException e) {
                    try {
                        Toast.makeText(context,"Socket connection closing: " + e.getMessage() + ".",Toast.LENGTH_SHORT).show();
                        btSocket.close();
                        //return;
                    } catch (IOException e2) {
                        Toast.makeText(context, "Unable to close socket after connection failure" + e2.getMessage() + ".", Toast.LENGTH_SHORT).show();
                        //return;
                    }
                }
                try {
                    outStream = btSocket.getOutputStream();
                } catch (IOException e) {
                    Toast.makeText(context, "Output stream creation failure:" + e.getMessage() + ".",Toast.LENGTH_SHORT).show();
                    //return;
                }
            }
        } else {
            Toast.makeText(context,"Bluetooth Disabled - enable bluetooth to connect !",Toast.LENGTH_SHORT).show();
        }
    }

    public void send(String message) {
        byte[] msgBuffer = message.getBytes();
        try {
            outStream.write(msgBuffer);
        } catch (IOException e) {
            Toast.makeText(context, "Output stream write failure:" + e.getMessage() + ".",Toast.LENGTH_SHORT).show();
            connect();
            try {
                outStream.write(msgBuffer);
            } catch (IOException e2){
                Toast.makeText(context, "Output stream write failure - after reconnection attempt:" + e2.getMessage() + ".",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
