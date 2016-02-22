package com.nezspencer.nuhiara.colourhub;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


/**
 * An activity representing a list of Colours. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ColourDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ColourListFragment} and the item details
 * (if present) is a {@link ColourDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link ColourListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class ColourListActivity extends AppCompatActivity
        implements ColourListFragment.Callbacks,ShareActionProvider.OnShareTargetSelectedListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    public static final String INTENT_EXTRA="extra";
    private boolean mTwoPane;
    private ShareActionProvider actionProvider;


    private Toolbar toolbar;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_colour_list);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_launcher);
            getSupportActionBar().setTitle(R.string.app_name);


        }




        if (findViewById(R.id.colour_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ColourListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.colour_list))
                    .setActivateOnItemClick(true);

        }


        context=this.getApplicationContext();
    }

    /**
     * Callback method from {@link ColourListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id,int color) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ColourDetailFragment.ARG_ITEM_ID, id);
            arguments.putInt(ColourDetailFragment.ARG_ITEM_COLOR,color);
            ColourDetailFragment fragment = new ColourDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.colour_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ColourDetailActivity.class);
            detailIntent.putExtra(ColourDetailFragment.ARG_ITEM_ID, id);
            detailIntent.putExtra(ColourDetailFragment.ARG_ITEM_COLOR,color);
            startActivity(detailIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item=menu.findItem(R.id.share);
        actionProvider=(ShareActionProvider)MenuItemCompat.getActionProvider(item);
        actionProvider.setShareIntent(createShareIntent());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.rate)
        {
            final String packageName=context.getPackageName();
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("" +
                        "market://details?id="+packageName)));
            }
            catch (android.content.ActivityNotFoundException e)
            {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("" +
                        "https://play.google.com/store/apps/details?="+packageName)));
            }
        }
        if (item.getItemId()==android.R.id.home)
        {
            NavUtils.navigateUpTo(this,new Intent(this,ColourHub.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Intent createShareIntent()
    {
        Intent shareIntent=new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this colour app: \n" +
                "http://play.google.com/store/apps/details?id=" + context.getPackageName());

        shareIntent.setType("text/plain");
        return shareIntent;
    }

    @Override
    public boolean onShareTargetSelected(ShareActionProvider source, Intent intent) {



        return false;
    }

}
