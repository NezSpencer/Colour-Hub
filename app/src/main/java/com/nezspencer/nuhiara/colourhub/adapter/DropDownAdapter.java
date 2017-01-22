package com.nezspencer.nuhiara.colourhub.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nezspencer.nuhiara.colourhub.R;
import com.nezspencer.nuhiara.colourhub.helper.ApplicationVariables;

import java.util.List;

/**
 * Created by nezspencer on 2/23/16.
 */
public class DropDownAdapter implements android.widget.SpinnerAdapter {

    List<ApplicationVariables.DummyItem> items;
    Context context;
    public DropDownAdapter(Context context,List<ApplicationVariables.DummyItem> items) {
        super();
        this.context=context;
        this.items=items;
    }

    @Override
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {

        return getCustomView(i,view,viewGroup);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
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
        Log.e("color set: ", " " + items.get(position).color_name);

        return view;
    }
}
