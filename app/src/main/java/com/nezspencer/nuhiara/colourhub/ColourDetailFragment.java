package com.nezspencer.nuhiara.colourhub;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.nezspencer.nuhiara.colourhub.helper.ApplicationVariables;

/**
 * A fragment representing a single Colour detail screen.
 * This fragment is either contained in a {@link ColourListActivity}
 * in two-pane mode (on tablets) or a {@link ColourDetailActivity}
 * on handsets.
 */
public class ColourDetailFragment extends Fragment implements SeekBar.OnSeekBarChangeListener{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ITEM_COLOR="color";
    private ActionMode.Callback callback;
    private String colorName="none";
    private String[] colorShades;
    private String[] alpha_level;
    String T_color;
    String A_color;
    String SUM_color;
    Spinner spinner;


    SeekBar alphaSeekBar;
    SeekBar ColorSeekBar;
    CardView backLayout;
    FrameLayout fore_layout;
    TextView argb_color;
    TextView rgb_color;
    boolean isBlack;
    /**
     * The dummy content this fragment is presenting.
     */
    private ApplicationVariables.DummyItem mItem;

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
            mItem = ApplicationVariables.getInstance().ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
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
        TextView intensity_label=(TextView)rootView.findViewById(R.id.intensity_text);
        ColorSeekBar=(SeekBar)rootView.findViewById(R.id.colorIntensity);
        backLayout=(CardView)rootView.findViewById(R.id.back_layout);
        fore_layout=(FrameLayout)rootView.findViewById(R.id.fore_layout);
        argb_color=(TextView)rootView.findViewById(R.id.argb_color);
        rgb_color=(TextView)rootView.findViewById(R.id.rgb_color);
        ColorSeekBar.setMax(colorShades.length - 1);
        if (isBlack)
        {
            ColorSeekBar.setVisibility(View.GONE);
            intensity_label.setVisibility(View.GONE);
        }


        displayColorCode();


        alphaSeekBar.setMax(alpha_level.length - 1);

        ColorSeekBar.setProgress(colorShades.length/2);

        ColorSeekBar.setOnSeekBarChangeListener(this);
        alphaSeekBar.setOnSeekBarChangeListener(this);
        rgb_color.setCustomSelectionActionModeCallback(setUpActionMode());
        argb_color.setCustomSelectionActionModeCallback(setUpActionMode());
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            //((TextView) rootView.findViewById(R.id.colour_detail)).setText(mItem.color_name);
            fore_layout.setBackgroundResource(mItem.color);
        }

        return rootView;
    }


    public Integer stringToColor(String colorString)
    {
        return Color.parseColor(colorString);
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

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (seekBar.getId()==R.id.colorIntensity)
            T_color=colorShades[progress];
        else
            A_color=alpha_level[progress];
        fore_layout.setBackgroundColor(stringToColor(computeColor()));
        displayColorCode();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public ActionMode.Callback setUpActionMode()
    {
        callback=new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.setTitle("Copy colour code");
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        };

        return callback;
    }
}
