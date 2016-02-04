package com.nezspencer.nuhiara.colourhub;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.nezspencer.nuhiara.colourhub.adapter.SpinnerAdapter;
import com.nezspencer.nuhiara.colourhub.dummy.DummyContent;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Nnabueze on 1/30/2016.
 */
public class ColorMixFragment extends Fragment implements View.OnClickListener,SeekBar.OnSeekBarChangeListener {

    private SeekBar A_seekbar;
    private SeekBar R_seekBar;
    private SeekBar G_seekbar;
    private SeekBar B_seekbar;
    private EditText inputColor;
    private CircularProgressButton renderButton;
    private TextView rgbColor;
    private TextView argbColor;
    private Spinner backgroundColorSpinner;
    private LinearLayout screenLayout;
    private LinearLayout backLayout;
    private static String A_string=null;
    private static String R_string=null;
    private static String G_string=null;
    private static String B_string=null;
    private List<String>colorCodes;
    int size;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        colorCodes=Arrays.asList(getResources().getStringArray(R.array.color_codes));
        size = colorCodes.size() - 1;

        A_string=colorCodes.get(size);
        R_string=colorCodes.get(0);
        G_string=colorCodes.get(0);
        B_string=colorCodes.get(0);

        View view=inflater.inflate(R.layout.fragment_colour_mix,container,false);
        findAllViews(view);
        displayColorCode();
        screenLayout.setBackgroundColor(stringToColor(computeColor()));
        renderButton.setOnClickListener(this);
        A_seekbar.setMax(size);
        R_seekBar.setMax(size);
        G_seekbar.setMax(size);
        B_seekbar.setMax(size);
        inputColor.setFocusable(false);
        inputColor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                inputColor.setFocusableInTouchMode(true);
                return false;
            }
        });
        inputColor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    renderButton.setProgress(0);
                    renderButton.setIndeterminateProgressMode(false);
                }
            }
        });
        A_seekbar.setOnSeekBarChangeListener(this);
        R_seekBar.setOnSeekBarChangeListener(this);
        G_seekbar.setOnSeekBarChangeListener(this);
        B_seekbar.setOnSeekBarChangeListener(this);

        backgroundColorSpinner.setAdapter(new SpinnerAdapter(getActivity(), DummyContent.ITEMS));
        backgroundColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                backLayout.setBackgroundColor(DummyContent.ITEMS.get(position).color);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }



    private void findAllViews(View view)
    {
        A_seekbar=(SeekBar)view.findViewById(R.id.colorA);
        R_seekBar=(SeekBar)view.findViewById(R.id.colorR);
        G_seekbar=(SeekBar)view.findViewById(R.id.colorG);
        B_seekbar=(SeekBar)view.findViewById(R.id.colorB);
        inputColor=(EditText)view.findViewById(R.id.input_colour);
        renderButton=(CircularProgressButton)view.findViewById(R.id.btnWithText);
        rgbColor=(TextView)view.findViewById(R.id.rgb_color);
        argbColor=(TextView)view.findViewById(R.id.argb_color);
        backgroundColorSpinner=(Spinner)view.findViewById(R.id.spinner);
        screenLayout=(LinearLayout)view.findViewById(R.id.fore_layout);
        backLayout=(LinearLayout)view.findViewById(R.id.back_layout);
    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.btnWithText)
        {
            String t=inputColor.getText().toString();
            if (TextUtils.isEmpty(t))
            {
                inputColor.setError("enter color code");
                return;
            }

            try {
                Color.parseColor(t);
                new RotateButton().execute(t);
            }
            catch (IllegalArgumentException e)
            {
                inputColor.setError("not a color");
                e.printStackTrace();
            }
        }
    }



    public void getStringEquivalent(int change,String id)
    {

            if (id.equalsIgnoreCase("alpha"))
            {
                A_string=colorCodes.get(change);
            }

            else if (id.equalsIgnoreCase("red"))
            {
                R_string=colorCodes.get(change);
            }

            else if (id.equalsIgnoreCase("green"))
            {
                G_string=colorCodes.get(change);
            }
            else {
                B_string=colorCodes.get(change);
            }


        screenLayout.setBackgroundColor(stringToColor(computeColor()));
        displayColorCode();

    }

    public String computeColor() {return "#"+A_string+R_string+G_string+B_string;}

    public Integer stringToColor(String colorString)
    {
        return Color.parseColor(colorString);
    }

    public void displayColorCode()
    {
        rgbColor.setText("#" + R_string + G_string + B_string);
        argbColor.setText(computeColor());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        final String alpha="alpha";
        final String red="red";
        final String green="green";
        final String blue="blue";

        if (seekBar.getId()==R.id.colorA)
            getStringEquivalent(size-progress,alpha);
        else if (seekBar.getId()==R.id.colorR)
            getStringEquivalent(progress,red);
        else if (seekBar.getId()==R.id.colorG)
            getStringEquivalent(progress,green);
        else
            getStringEquivalent(progress,blue);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void extractColorString(String colorString)
    {

        String temp=colorString.substring(1);
        Log.e("temp"," "+temp);
        int length=temp.length();

        if (length==6)
        {
            A_string=colorCodes.get(0);
            R_string=temp.substring(0,2);
            G_string=temp.substring(2,4);
            B_string=temp.substring(4,6);
        }


        else {
            A_string=temp.substring(0,2);
            R_string=temp.substring(2,4);
            G_string=temp.substring(4,6);
            B_string=temp.substring(6,8);
        }

        A_seekbar.setProgress(colorCodes.indexOf(A_string.toUpperCase()));
        R_seekBar.setProgress(colorCodes.indexOf(R_string.toUpperCase()));
        G_seekbar.setProgress(colorCodes.indexOf(G_string.toUpperCase()));
        B_seekbar.setProgress(colorCodes.indexOf(B_string.toUpperCase()));

        displayColorCode();
    }



    class RotateButton extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            renderButton.setProgress(30);
            renderButton.setIndeterminateProgressMode(true);
        }

        @Override
        protected String doInBackground(String... params) {

            for (int i=0; i<5000; i++)
                Log.i("roll button ",""+i);

            return params[0];
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);

            renderButton.setIndeterminateProgressMode(false);
            renderButton.setProgress(100);
            screenLayout.setBackgroundColor(Color.parseColor(aVoid));
            inputColor.setFocusable(false);
            extractColorString(aVoid);
        }
    }
}
