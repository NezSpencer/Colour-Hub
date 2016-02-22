package com.nezspencer.nuhiara.colourhub.helper;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

import com.nezspencer.nuhiara.colourhub.R;

import java.util.Random;

/**
 * Created by Nnabueze on 2/7/2016.
 */
public class Typewriter extends TextView {

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
    private CharSequence text;
    private int index;
    private long delay=500; //ms

    public Typewriter(Context context) {
        super(context);
    }

    public Typewriter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private Handler handler=new Handler();
    private Runnable characterAdder=new Runnable() {
        @Override
        public void run() {
            setText(text.subSequence(0, index++));

            if (index<=text.length())
            {

                handler.postDelayed(characterAdder,delay);
                setTextColor(Color.GREEN);
            }
        }
    };

    public void animateText(CharSequence text)
    {
        this.text=text;
        index=0;
        setText("");
        handler.removeCallbacks(characterAdder);
        handler.postDelayed(characterAdder,delay);
    }
    public void setCharacterDelay(long millis)
    {
        delay=millis;
    }
}
