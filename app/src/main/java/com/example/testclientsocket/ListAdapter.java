package com.example.testclientsocket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testclientsocket.ui.Variables;

import java.util.List;

public class ListAdapter extends ArrayAdapter {
    private final Context context;
    private final List<String> values;

    public ListAdapter(Context context, List<String> values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values.get(position));
        // Change the icon for Windows and iPhone
        String s = values.get(position);
        if (Variables.mediaActual!="" && s.startsWith(Variables.mediaActual) ) {
            imageView.setImageResource(R.drawable.play_circle_outline_white_24dp);
        } else {
            imageView.setImageResource(R.drawable.play_next_white_24dp);
        }

        return rowView;
    }
}
