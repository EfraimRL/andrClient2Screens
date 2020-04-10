package com.example.testclientsocket.ui;

import java.io.IOException;
import java.util.regex.Pattern;


public class Thread2 implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                final String message = Variables.input.readLine();
                if (message != null) {
                    String funct="";
                    boolean recargar = false;
                    try{funct=message.substring(0,4);}catch(Exception ex){}
                    if (funct.equals("gtmd")){
                        Variables.mediaActual = message.substring(4);
                    }
                    else if(funct.equals("gtls")){
                        Variables.listItems = message.substring(4).split(Pattern.quote("||"));
                    }

                    Variables.mainActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Variables.tvMessages.append("server: " + message + "\n");
                            //Toast.makeText(Variables.mainActivity.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            if (message.equals("play") || message.equals("next") || message.equals("prev")){
                                new Thread(new Thread3("gtmd")).start();
                            }
                        }
                    });
                    Variables.Reconnect();
                    return;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}