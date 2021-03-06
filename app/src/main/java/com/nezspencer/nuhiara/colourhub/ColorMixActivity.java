package com.nezspencer.nuhiara.colourhub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Nnabueze on 1/29/2016.
 */
public class ColorMixActivity extends AppCompatActivity {
    private static Toolbar toolbar;
    private ShareActionProvider actionProvider;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_mix);

        if (getResources().getConfiguration().screenWidthDp<600)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        context=getApplicationContext();
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null)
        {
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.app_name);
            applyFontForToolbarTitle(this);
        }
        getSupportFragmentManager().beginTransaction().add(R.id.mix_frame,new ColorMixFragment()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            NavUtils.navigateUpTo(this, new Intent(this, ColourHub.class));
            return true;
        }

        if (item.getItemId()==R.id.rate)
        {
            final String packageName=context.getPackageName();
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("" +
                        "market://details?id=" + packageName)));
            }
            catch (android.content.ActivityNotFoundException e)
            {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("" +
                        "https://play.google.com/store/apps/details?id="+packageName)));
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item=menu.findItem(R.id.share);
        actionProvider=(ShareActionProvider) MenuItemCompat.getActionProvider(item);
        actionProvider.setShareIntent(createShareIntent());
        return super.onCreateOptionsMenu(menu);
    }



    public Intent createShareIntent()
    {
        Intent shareIntent=new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this colour app: \n" +
                "http://play.google.com/store/apps/details?id=" + context.getPackageName());

        shareIntent.setType("text/plain");
        return shareIntent;
    }

    public static void applyFontForToolbarTitle(Activity context){
        /*Toolbar toolbar = (Toolbar) context.findViewById(R.id.a);*/
        for(int i = 0; i < toolbar.getChildCount(); i++){
            View view = toolbar.getChildAt(i);
            if(view instanceof TextView){
                TextView tv = (TextView) view;
                Typeface titleFont = Typeface.
                        createFromAsset(context.getAssets(), "Raleway-Medium.ttf");
                if(tv.getText().equals(toolbar.getTitle())){
                    tv.setTypeface(titleFont);
                    break;
                }
            }
        }
    }
}
