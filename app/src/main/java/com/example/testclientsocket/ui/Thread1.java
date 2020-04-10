package com.example.testclientsocket.ui;

import android.annotation.SuppressLint;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import static com.example.testclientsocket.ui.Variables.SERVER_PORT;
import static com.example.testclientsocket.ui.Variables.SERVER_IP;
public class Thread1 implements Runnable {
    public Socket socket;
    public Thread1(){}
    public void run() {
        boolean not_connected = true;
        try {
            if (SERVER_IP!=null && SERVER_IP!="") {
                socket = new Socket();
                socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT), 2000);
                Variables.output = new PrintWriter(socket.getOutputStream());
                Variables.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Variables.mainActivity.runOnUiThread(new Runnable() {
                    @SuppressLint("ShowToast")
                    @Override
                    public void run() {
                        Variables.tvMessages.append("Connected\n");
                        Variables.connected = true;
                        Toast.makeText(Variables.mainActivity.getApplicationContext(), "Conected", Toast.LENGTH_SHORT).show();
                    }
                });
                not_connected = false;
                new Thread(new Thread2()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (not_connected) {
                Variables.mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(Variables.tvMessages!=null){Variables.tvMessages.setText("Not Connected\n");}
                        Variables.connected = false;
                        Toast.makeText(Variables.mainActivity.getApplicationContext(), "Not Conected", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}