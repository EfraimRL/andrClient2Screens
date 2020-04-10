package com.example.testclientsocket.ui;


import android.widget.Toast;

import com.example.testclientsocket.MainActivity;

public class Thread3 implements Runnable {
    private String message;
    public Thread3(String message) {
        this.message = message;
    }
    @Override
    public void run() {
        if (Variables.output!=null) {
            Variables.output.write(message);
            Variables.output.flush();
            Variables.mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Variables.tvMessages.append("client: " + message + "\n");
                    Variables.etMessage.setText("");
                }
            });
        }
        else{
            Variables.mainActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Variables.mainActivity.getApplicationContext(),"No conectado",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}