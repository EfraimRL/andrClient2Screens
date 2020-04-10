package com.example.testclientsocket.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.testclientsocket.R;
import com.example.testclientsocket.ui.Thread1;
import com.example.testclientsocket.ui.Thread3;
import com.example.testclientsocket.ui.Variables;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        Variables.etIP = root.findViewById(R.id.etIP);
        Variables.etPort = root.findViewById(R.id.etPort);
        Variables.tvMessages = root.findViewById(R.id.tvMessages);
        Variables.etMessage = root.findViewById(R.id.etMessage);
        Variables.btnConnect = root.findViewById(R.id.btnConnect);
        Variables.btnSend = root.findViewById(R.id.btnSend);
        Variables.btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.tvMessages.setText("");
                Variables.SERVER_IP = Variables.etIP.getText().toString().trim();
                try{Variables.SERVER_PORT = Integer.parseInt(Variables.etPort.getText().toString().trim());} catch (Exception e){Variables.SERVER_PORT=800;}
                if ("".equals(Variables.SERVER_IP) || Variables.SERVER_PORT==0 ){
                    Toast.makeText(Variables.mainActivity.getApplicationContext(),"Llena los campos de IP y Puerto.",Toast.LENGTH_SHORT).show();
                    Variables.tvMessages.setText("Not Connected\n");
                }else {
                    Variables.thread1 = new Thread(new Thread1());
                    Variables.thread1.start();
                }
            }
        });

        Variables.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = Variables.etMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    new Thread(new Thread3(message)).start();
                }
            }
        });
        return root;
    }
}