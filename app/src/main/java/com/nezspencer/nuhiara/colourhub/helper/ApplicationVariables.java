package com.nezspencer.nuhiara.colourhub.helper;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.nezspencer.nuhiara.colourhub.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nnabueze on 2/20/2016.
 */
public class ApplicationVariables extends Application {

    private String[] colour_name;
    private int color[]={
            R.color.red,
            R.color.pink,
            R.color.purple,
            R.color.deep_purple,
            R.color.indigo,
            R.color.light_blue,
            R.color.blue,
            R.color.cyan,
            R.color.teal,
            R.color.green,
            R.color.light_green,
            R.color.lime,
            R.color.yellow,
            R.color.amber,
            R.color.orange,
            R.color.deep_orange,
            R.color.brown,
            R.color.grey,
            R.color.blue_grey,
            R.color.black
    };

    private static ApplicationVariables mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        colour_name=getResources().getStringArray(R.array.color_names);

        new colorLoader().execute();
    }



    public static ApplicationVariables getInstance() { return mInstance;}


    /**
     * An array of sample (dummy) items.
     */
    public List<DummyItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public Map<String, DummyItem> ITEM_MAP = new HashMap<>();



    public void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.color_name, item);
    }

    public void clearAll()
    {
        Log.e("data cleared", "");
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    public DummyItem getDummyObject(int color, String color_name) {return new DummyItem(color,
            color_name);}

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public int color;
        public String color_name;

        public DummyItem(int color, String color_name) {
            this.color = color;
            this.color_name = color_name;
        }

        @Override
        public String toString() {
            return color_name;
        }
    }

    /**
     * this opens up a way for the colorLoader astncTask to be called from
     * any activity
     *
     * */
    public void loadColours() { new colorLoader().execute();}

    class  colorLoader extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {

            clearAll();
            for (int i=0; i<colour_name.length; i++)
            {
                addItem(getDummyObject(color[i],
                        colour_name[i]));
            }
            return null;
        }
    }
}
