package com.nezspencer.nuhiara.colourhub.dummy;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 *
 */
public class DummyContent {

    private static DummyContent dummyContentInstance;

    public static DummyContent getInstance()
    {
        if (dummyContentInstance==null)
            dummyContentInstance=new DummyContent();
        return dummyContentInstance;
    }

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();



    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.color_name, item);
    }

    public static void clearAll()
    {
        Log.e("data cleared","");
        ITEMS.clear();
        ITEM_MAP.clear();
    }

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
}
