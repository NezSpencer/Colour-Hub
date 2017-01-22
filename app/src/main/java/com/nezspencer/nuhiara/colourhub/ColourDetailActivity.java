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
 * An activity representing a single Colour detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ColourListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link ColourDetailFragment}.
 */
public class ColourDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Context context;
    private ShareActionProvider actionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_detail);
        context=getApplicationContext();
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Show the Up button in the action bar.
        if (getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_launcher);
            getSupportActionBar().setShowHideAnimationEnabled(true);
            applyFontForToolbarTitle(this);
        }

        if (getResources().getConfiguration().screenWidthDp<600)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ColourDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ColourDetailFragment.ARG_ITEM_ID));
            arguments.putInt(ColourDetailFragment.ARG_ITEM_COLOR,getIntent().getIntExtra(ColourDetailFragment.ARG_ITEM_COLOR,0));
            ColourDetailFragment fragment = new ColourDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.colour_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, ColourListActivity.class));
            return true;
        }

        if (id==R.id.rate)
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
    public Intent createShareIntent()
    {
        Intent shareIntent=new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this cool color App: \n" +
                "http://play.google.com/store/apps/details?id=" + context.getPackageName());

        shareIntent.setType("text/plain");
        //startActivity(shareIntent);
        return shareIntent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item=menu.findItem(R.id.share);
        actionProvider=(ShareActionProvider) MenuItemCompat.getActionProvider(item);
        actionProvider.setShareIntent(createShareIntent());
        return true;
    }

    public void applyFontForToolbarTitle(Activity context){
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
