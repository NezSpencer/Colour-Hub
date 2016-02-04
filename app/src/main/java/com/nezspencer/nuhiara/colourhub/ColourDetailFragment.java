package com.nezspencer.nuhiara.colourhub;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.nezspencer.nuhiara.colourhub.adapter.SpinnerAdapter;
import com.nezspencer.nuhiara.colourhub.dummy.DummyContent;

/**
 * A fragment representing a single Colour detail screen.
 * This fragment is either contained in a {@link ColourListActivity}
 * in two-pane mode (on tablets) or a {@link ColourDetailActivity}
 * on handsets.
 */
public class ColourDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ITEM_COLOR="color";

    private String colorName="none";
    private String[] colorShades;
    private String[] alpha_level;
    String T_color;
    String A_color;
    String SUM_color;
    Spinner spinner;

    private int initalColorSeekBarPosition =0;
    private int initialAlphaSeekBarPosition=0;

    SeekBar alphaSeekBar;
    SeekBar ColorSeekBar;
    LinearLayout backLayout;
    LinearLayout fore_layout;
    TextView argb_color;
    TextView rgb_color;
    boolean isBlack;
    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ColourDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            colorName=getArguments().getString(ARG_ITEM_ID);
        }

        alpha_level=getResources().getStringArray(R.array.alpha_chart);

        if ("Red".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.red);
        }

        else if ("Pink".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.pink);
        }

        else if ("Purple".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.purple);
        }

        else if ("Deep Purple".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.deep_purple);
        }

        else if ("Indigo".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.indigo);
        }

        else if ("Light Blue".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.light_blue);
        }

        else if ("Blue".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.blue);
        }

        else if ("Cyan".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.cyan);
        }

        else if ("Teal".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.teal);
        }

        else if ("Green".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.green);
        }

        else if ("Light Green".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.light_green);
        }

        else if ("Lime".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.lime);
        }

        else if ("Yellow".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.yellow);
        }

        else if ("Amber".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.amber);
        }

        else if ("Orange".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.orange);
        }

        else if ("Deep Orange".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.deep_orange);
        }

        else if ("Brown".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.brown);
        }

        else if ("Grey".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.grey);
        }

        else if ("Blue Grey".equals(colorName))
        {
            colorShades=getResources().getStringArray(R.array.blue_grey);
        }

        else
        {
            isBlack=true;
            colorShades=getResources().getStringArray(R.array.black);
        }
        T_color=colorShades[6];
        A_color=alpha_level[0];

        SUM_color="#"+A_color+T_color;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_colour_detail, container, false);
        alphaSeekBar=(SeekBar)rootView.findViewById(R.id.alpha_level);
        ColorSeekBar=(SeekBar)rootView.findViewById(R.id.colorIntensity);
        backLayout=(LinearLayout)rootView.findViewById(R.id.back_layout);
        fore_layout=(LinearLayout)rootView.findViewById(R.id.fore_layout);
        argb_color=(TextView)rootView.findViewById(R.id.argb_color);
        rgb_color=(TextView)rootView.findViewById(R.id.rgb_color);
        spinner=(Spinner)rootView.findViewById(R.id.spinner);
        if (isBlack)
            ColorSeekBar.setEnabled(false);
        displayColorCode();
        /*ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getActivity(),R.array.color_names,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        */
        //SpinnerAdapter spinnerAdapter=new SpinnerAdapter(getActivity(),DummyContent.ITEMS);

        spinner.setAdapter(new SpinnerAdapter(getActivity(),DummyContent.ITEMS));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                backLayout.setBackgroundColor(DummyContent.ITEMS.get(position).color);
                Log.e("backLayout_color set:"," "+DummyContent.ITEMS.get(position).color_name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ColorSeekBar.setProgress(60);

        alphaSeekBar.setMax(alpha_level.length-1);

        ColorSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                //if (fromUser) {

                    int startPos;
                    startPos = (initalColorSeekBarPosition <= 10 ? 0 : ((initalColorSeekBarPosition / 10) - 1));
                    boolean isForward = true; // when true it means the user is dragging seekBar forward
                    if (seekBar.getKeyProgressIncrement() - initalColorSeekBarPosition < 0) // backwards seek
                    {

                        isForward = false;
                        getSeekBarChange(seekBar.getKeyProgressIncrement(), isForward, startPos);

                    } else if (seekBar.getKeyProgressIncrement() - initalColorSeekBarPosition > 0) //forward seek
                    {

                        isForward = true;
                        getSeekBarChange(seekBar.getKeyProgressIncrement(), isForward, startPos);
                    } else //same position
                    {
                        getSeekBarChange(seekBar.getKeyProgressIncrement(), isForward, startPos);
                    }
                //}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                initalColorSeekBarPosition = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                T_color=colorShades[seekBar.getProgress() <= 10 ? 0 : (seekBar.getProgress() / 10) - 1];
                displayColorCode();
                fore_layout.setBackgroundColor(stringToColor(computeColor()));


            }
        });

        alphaSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

               // T_color="#"+T_color.substring(1);
                boolean isFront=false;
                if (seekBar.getKeyProgressIncrement()-initialAlphaSeekBarPosition<0)
                {
                    isFront=false;
                    changeTransparency(seekBar.getKeyProgressIncrement(),isFront,initialAlphaSeekBarPosition);
                }
                else if (seekBar.getKeyProgressIncrement()-initialAlphaSeekBarPosition>0)
                {
                    isFront=true;
                    changeTransparency(seekBar.getKeyProgressIncrement(),isFront,initialAlphaSeekBarPosition);
                }
                else
                {

                    changeTransparency(seekBar.getKeyProgressIncrement(),isFront,initialAlphaSeekBarPosition);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                initialAlphaSeekBarPosition=seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                A_color=alpha_level[seekBar.getProgress()];
                Log.e("ttt"," "+A_color);
                displayColorCode();
                fore_layout.setBackgroundColor(stringToColor(computeColor()));
            }
        });

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            //((TextView) rootView.findViewById(R.id.colour_detail)).setText(mItem.color_name);
            fore_layout.setBackgroundResource(mItem.color);
        }

        return rootView;
    }

    public void getSeekBarChange(int change,boolean isForward,int initalSeekBarPosition){


        if (change<=10){

            if (isForward)
            {
                T_color = colorShades[initalSeekBarPosition];
            }
            else {
                T_color =  colorShades[initalSeekBarPosition];
            }


        }
        else if (change<=20)
        {

            if (isForward)
            {
                T_color = colorShades[initalSeekBarPosition+1];
            }
            else {
                T_color = colorShades[initalSeekBarPosition-1];
            }

        }
        else if (change<=30)
        {
            if (isForward)
            {
                T_color = colorShades[initalSeekBarPosition+2];
            }
            else {
                T_color = colorShades[initalSeekBarPosition-2];
            }

        }
        else if (change<=40)
        {
            if (isForward)
            {
                T_color = colorShades[initalSeekBarPosition+3];
            }
            else {
                T_color = colorShades[initalSeekBarPosition-3];
            }

        }
        else if (change<=50)
        {
            if (isForward)
            {
                T_color = colorShades[initalSeekBarPosition+4];
            }
            else {
                T_color = colorShades[initalSeekBarPosition-4];
            }

        }
        else if (change<=60)
        {
            if (isForward)
            {
                T_color = colorShades[initalSeekBarPosition+5];
            }
            else {
                T_color = colorShades[initalSeekBarPosition-5];

            }

        }
        else if (change<=70)
        {
            if (isForward)
            {
                T_color = colorShades[initalSeekBarPosition+6];
            }
            else {
                T_color =colorShades[initalSeekBarPosition-6];
            }

        }
        else if (change<=80)
        {
            if (isForward)
            {
                T_color = colorShades[initalSeekBarPosition+7];
            }
            else {
                T_color =colorShades[initalSeekBarPosition-7];
            }

        }
        else if (change<=90)
        {
            if (isForward)
            {
                T_color =colorShades[initalSeekBarPosition+8];
            }
            else {
                T_color = colorShades[initalSeekBarPosition-8];
            }

        }
        else { //change is frm 91-100
            if (isForward)
            {
                T_color =colorShades[initalSeekBarPosition+9];

            }
            else {
                T_color =colorShades[initalSeekBarPosition-9];


            }
        }
        fore_layout.setBackgroundColor(stringToColor(computeColor()));
        displayColorCode();

    }

    public Integer stringToColor(String colorString)
    {
        return Color.parseColor(colorString);
    }

    public void changeTransparency(int change,boolean isForward,int initalSeekBarPosition)
    {

        if (isForward)
        {

            Log.e("color"," "+SUM_color);
            A_color=alpha_level[initalSeekBarPosition+change];


        }
        else {
            A_color=alpha_level[initalSeekBarPosition-change];


        }
        fore_layout.setBackgroundColor(stringToColor(computeColor()));
        displayColorCode();

    }
    public String computeColor()
    {
        return "#"+A_color+T_color;
    }

    public void displayColorCode()
    {
        rgb_color.setText("#"+T_color);
        argb_color.setText(computeColor());
    }
}
