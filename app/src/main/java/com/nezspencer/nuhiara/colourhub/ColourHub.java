package com.nezspencer.nuhiara.colourhub;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nezspencer.nuhiara.colourhub.dummy.DummyContent;
import com.nezspencer.nuhiara.colourhub.helper.Typewriter;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

/**
 * Created by Nnabueze on 1/31/2016.
 */
public class ColourHub extends AppCompatActivity {

    private static int dimension=0;
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
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        colour_name=getResources().getStringArray(R.array.color_names);
        new colorLoader().execute();
        setContentView(R.layout.hub);
        context=this.getApplicationContext();
        Typewriter titleText=(Typewriter)findViewById(R.id.titleText);
        titleText.setCharacterDelay(300);
        titleText.animateText("Colour Hub");
        titleText.animate();

        FloatingActionButton.LayoutParams layoutParams=new FloatingActionButton.LayoutParams(120,120, Gravity.CENTER);
        layoutParams.setMargins(0, 0, 20, 20);

        ImageView icon=new ImageView(this);
        icon.setImageResource(R.mipmap.ic_launcher);
        FloatingActionButton fab=new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setLayoutParams(layoutParams)
                .build();


        int radius;
        if(getResources().getConfiguration().smallestScreenWidthDp<600)
        {
            radius=200;
            dimension=80;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            if (getResources().getDisplayMetrics().densityDpi>= DisplayMetrics.DENSITY_HIGH)
            {
                radius=300;
                dimension=100;
            }
        }
        else {
            radius=350;
            dimension=100;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }



        SubActionButton.Builder builder= new SubActionButton.Builder(this);
        FloatingActionButton.LayoutParams params=new FloatingActionButton.LayoutParams(dimension,dimension);
        builder.setLayoutParams(params);
        TextView predefinedText=new TextView(this);
        predefinedText.setText("     Use \nexisting\n   colours");
        predefinedText.setTextColor(Color.WHITE);
        predefinedText.setTextSize(12);

        TextView makeUrs=new TextView(this);
        makeUrs.setText("Make \n your own\n colour");
        makeUrs.setTextSize(12);
        makeUrs.setTextColor(Color.WHITE);
        ImageView share=new ImageView(this);
        share.setImageResource(R.drawable.ic_action_share);
        ImageView rateView=new ImageView(this);
        rateView.setImageResource(R.drawable.ic_action_favorite);

        SubActionButton predefinedColourButton=builder.setContentView(predefinedText)
                .setBackgroundDrawable(getResources().getDrawable(R.drawable.action_menu_green))
                .build();
        predefinedColourButton.setForegroundGravity(Gravity.CENTER);
        predefinedColourButton.requestLayout();
        predefinedColourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ColourHub.this,ColourListActivity.class));
            }
        });


        SubActionButton makeUrColour=builder.setContentView(makeUrs)
                .setBackgroundDrawable(getResources().getDrawable(R.drawable.action_menu_blue))
                .build();

        makeUrColour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ColourHub.this, ColorMixActivity.class));
            }
        });
        SubActionButton rateButton=builder.setContentView(rateView)
                .setBackgroundDrawable(getResources().getDrawable(R.drawable.action_menu_red))
                .build();
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    /*this takes the user straight to the app on playstore if the user has the playstore
                    app installed*/
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?=" + context.getPackageName())));
                } catch (android.content.ActivityNotFoundException e) {
//                    if the user does not have the playstore app installed on the phone, this launches a browser intent
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?="
                            + context.getPackageName())));

                }

            }
        });

        SubActionButton shareButton=builder.setContentView(share)
                .setBackgroundDrawable(getResources().getDrawable(R.drawable.action_menu_grey))
                .setTheme(SubActionButton.THEME_DARKER)
                .build();

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this colour app: \n" +
                        "https://play.google.com/store/apps/details?=" + context.getPackageName());
                shareIntent.setType("text/plain");
                startActivityForResult(shareIntent, 9);
            }
        });


        FloatingActionMenu actionMenu=new FloatingActionMenu.Builder(this)
                .addSubActionView(predefinedColourButton)
                .addSubActionView(makeUrColour)
                .addSubActionView(rateButton)
                .addSubActionView(shareButton)
                .setRadius(radius)
                .attachTo(fab)
                .build();

    }

    class  colorLoader extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {

            DummyContent.clearAll();
            for (int i=0; i<colour_name.length; i++)
            {

                DummyContent.addItem(new DummyContent.DummyItem(color[i],colour_name[i]));
            }
            return null;
        }
    }
}
