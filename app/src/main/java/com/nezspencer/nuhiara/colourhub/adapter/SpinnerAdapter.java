package com.nezspencer.nuhiara.colourhub.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nezspencer.nuhiara.colourhub.R;
import com.nezspencer.nuhiara.colourhub.dummy.DummyContent;

import java.util.List;


/**
 * Created by Nnabueze on 1/22/2016.
 */
public class SpinnerAdapter extends ArrayAdapter<DummyContent.DummyItem> {

    List<DummyContent.DummyItem> items;
    Context context;


    public SpinnerAdapter(Context context,List<DummyContent.DummyItem> items) {
        super(context, R.layout.spinner_item,items);
        this.items=items;
        this.context=context;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position,convertView,parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return getCustomView(position,convertView,parent);
    }

    public View getCustomView(int position,View custom,ViewGroup parent)
    {
        if (custom==null)
        {
            custom= LayoutInflater.from(context).inflate(R.layout.spinner_item,parent,false);
        }

        CardView cardView=(CardView)custom.findViewById(R.id.spinner_card);
        cardView.setCardBackgroundColor(items.get(position).color);
        Log.e("color set: "," "+items.get(position).color_name);

        return custom;
    }
}
