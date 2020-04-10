package com.example.testclientsocket.ui;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testclientsocket.MainActivity;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Variables{
    public static boolean connected = false;
    public static Thread thread1 = null;
    public static EditText etIP, etPort;
    public static TextView tvMessages;
    public static EditText etMessage;
    public static String SERVER_IP;
    public static int SERVER_PORT;
    public static PrintWriter output;
    public static BufferedReader input;

    public static MainActivity mainActivity;
    public static Button btnConnect;
    public static Button btnSend;

    public static String mediaActual = "";
    public static String[] listItems;

    public static void Do(Functions action){
        Do(action,"");
    }
    public static void Do(Functions action, Object value) {
        switch (action){
            case Play: new Thread(new Thread3("play")).start();break;
            case Stop: new Thread(new Thread3("stop")).start();break;
            case Next: new Thread(new Thread3("next")).start();break;
            case Previous: new Thread(new Thread3("prev")).start();break;
            case Set_Index: new Thread(new Thread3("setx"+value)).start();break;
            case Insert_media: new Thread(new Thread3("insr")).start();break;               //Temporal hasta ver como hacerlo.
            case Remv: new Thread(new Thread3("remv"+value)).start();break;
            case Screen_next: new Thread(new Thread3("scnx")).start();break;
            case Screen_previous: new Thread(new Thread3("scpv")).start();break;
            case Get_list: new Thread(new Thread3("gtls")).start();break;
            case Get_media: new Thread(new Thread3("gtmd")).start();break;
            case Show: new Thread(new Thread3("show")).start();break;
            case Hide: new Thread(new Thread3("hide")).start();break;
            case Maximize: new Thread(new Thread3("maxi")).start();break;
            case Normalize_size: new Thread(new Thread3("norm")).start();break;
        }
    }
    public static void Reconnect(){
        Variables.thread1 = new Thread(new Thread1());
        Variables.thread1.start();
    }

}
