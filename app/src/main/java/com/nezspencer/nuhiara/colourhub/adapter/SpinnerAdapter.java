package com.nezspencer.nuhiara.colourhub.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nezspencer.nuhiara.colourhub.R;
import com.nezspencer.nuhiara.colourhub.helper.ApplicationVariables;

import java.util.List;


/**
 * Created by Nnabueze on 1/22/2016.
 */
public class SpinnerAdapter extends ArrayAdapter<ApplicationVariables.DummyItem> {

    List<ApplicationVariables.DummyItem> items;
    Context context;


    public SpinnerAdapter(Context context,List<ApplicationVariables.DummyItem> items) {
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
        View view;
        if (custom==null)
        {
            view= LayoutInflater.from(context).inflate(R.layout.spinner_item,parent,false);
        }
        else
            view=custom;

        /*CardView cardView=(CardView)view.findViewById(R.id.spinner_card);
        cardView.setCardBackgroundColor(getItem(position).color);*/
        view.setBackgroundResource(items.get(position).color);
        Log.e("color set: "," "+items.get(position).color_name);

        return view;
    }
}
