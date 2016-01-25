package com.nezspencer.nuhiara.colourhub.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nezspencer.nuhiara.colourhub.R;
import com.nezspencer.nuhiara.colourhub.dummy.DummyContent;

import java.util.List;

/**
 * Created by Nnabueze on 1/17/2016.
 */
public class ColourListAdapter extends ArrayAdapter<DummyContent.DummyItem> {

    List<DummyContent.DummyItem> dummyColors;
    Context context;

    public ColourListAdapter(Context context, List<DummyContent.DummyItem> colors) {
        super(context, R.layout.color_item,colors);
        dummyColors =colors;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.color_item,parent,false);
        }
        /*RelativeLayout layout=(RelativeLayout)convertView.findViewById(R.id.backgrnd);
        layout.setBackgroundColor(dummyColors.get(position).color);*/
       // Log.e("adapter ", "color set for position: #" + position);
        TextView colorName=(TextView)convertView.findViewById(R.id.color_name);

        colorName.setText(DummyContent.ITEMS.get(position).color_name);
        if (DummyContent.ITEMS.get(position).color_name.equalsIgnoreCase("black"))
            colorName.setTextColor(context.getResources().getColor(R.color.white));

        convertView.setBackgroundResource(dummyColors.get(position).color);


        return convertView;
    }
}
