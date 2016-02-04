package com.nezspencer.nuhiara.colourhub;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nezspencer.nuhiara.colourhub.dummy.DummyContent;

/**
 * Created by Nnabueze on 1/31/2016.
 */
public class ColourHub extends AppCompatActivity {

    private static int numClicked=0;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        colour_name=getResources().getStringArray(R.array.color_names);
        new colorLoader().execute();
        setContentView(R.layout.hub);
        Button mainButton=(Button)findViewById(R.id.main_button);
        final Button colorListButton=(Button)findViewById(R.id.colour_list_button);
        final Button colorMixButton=(Button)findViewById(R.id.color_mix_button);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numClicked++;
                if (numClicked%2!=0)
                {
                    colorListButton.setVisibility(View.VISIBLE);
                    colorMixButton.setVisibility(View.VISIBLE);
                }
                else {
                    colorListButton.setVisibility(View.GONE);
                    colorMixButton.setVisibility(View.GONE);
                }

            }
        });

        colorListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ColourHub.this, ColourListActivity.class));
            }
        });

        colorMixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ColourHub.this,ColorMixActivity.class));
            }
        });

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
