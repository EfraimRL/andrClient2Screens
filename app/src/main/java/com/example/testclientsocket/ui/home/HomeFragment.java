package com.example.testclientsocket.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.testclientsocket.R;
import com.example.testclientsocket.ui.Functions;
import com.example.testclientsocket.ui.Variables;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button prev, next, play, btn_nextScreen, btn_prevScreen, btn_visibility,btn_hidden,btn_maxScreen,btn_normalScreen;
    TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        prev = (Button)root.findViewById(R.id.btn_prev);
        next = (Button)root.findViewById(R.id.btn_next);
        play = (Button)root.findViewById(R.id.btn_play);
        btn_nextScreen = (Button)root.findViewById(R.id.btn_next_screen);
        btn_prevScreen = (Button)root.findViewById(R.id.btn_prev_screen);
        btn_visibility = (Button)root.findViewById(R.id.btn_show);
        btn_hidden = (Button)root.findViewById(R.id.btn_hidden);
        btn_maxScreen = (Button)root.findViewById(R.id.btn_fullscreen);
        btn_normalScreen = (Button)root.findViewById(R.id.btn_fullscreen_exit);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.Do(Functions.Previous);
                setName();
            }
        });
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Variables.Do(Functions.Next);
                setName();
            }
        });
        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Variables.Do(Functions.Play);
                setName();
            }
        });
        btn_nextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.Do(Functions.Screen_next);
            }
        });
        btn_prevScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.Do(Functions.Screen_previous);
            }
        });
        btn_visibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.Do(Functions.Show);
            }
        });
        btn_hidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.Do(Functions.Hide);
            }
        });
        btn_maxScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.Do(Functions.Maximize);
            }
        });
        btn_normalScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.Do(Functions.Normalize_size);
            }
        });
        return root;
    }
    public void setName(){
        if (Variables.connected && !Variables.mediaActual.equals("")) {
            textView.setText(Variables.mediaActual);
        }
    }
}