package com.example.testclientsocket.ui.slideshow;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.testclientsocket.ListAdapter;
import com.example.testclientsocket.R;
import com.example.testclientsocket.ui.Functions;
import com.example.testclientsocket.ui.Variables;

import java.util.ArrayList;
import java.util.Arrays;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Variables.Do(Functions.Get_list);

        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        final ListView listview = (ListView) root.findViewById(R.id.listview);
        final String[] values = Variables.listItems;
        if (null != values && values.length>0){
            final ArrayList<String> list = new ArrayList<String>(Arrays.asList(values));
            final ListAdapter adapter = new ListAdapter(root.getContext(), list);
            listview.setAdapter(adapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view,
                                        final int position, long id) {
                    final String item = (String) parent.getItemAtPosition(position);
                    view.animate().setDuration(500).alpha(0)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    //Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
//                                    adapter.remove(item);
//                                    adapter.notifyDataSetChanged();
                                    Variables.Do(Functions.Set_Index,position);
                                    Variables.mediaActual = item;
                                    view.setAlpha(1);
                                }
                            });
                }
            });
        }
        else{
            ImageView image_empty = (ImageView) root.findViewById(R.id.listview_empty);
            image_empty.setBackground((Drawable)getResources().getDrawable(R.drawable.ic_broken_image_white_24dp));

            /* Cambiar fondo */
            //listview.setBackground((Drawable)getResources().getDrawable(R.drawable.ic_broken_image_white_24dp));
        }
        return root;
    }
}