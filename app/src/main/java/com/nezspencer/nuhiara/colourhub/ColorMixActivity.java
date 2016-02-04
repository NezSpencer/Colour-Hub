package com.nezspencer.nuhiara.colourhub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Nnabueze on 1/29/2016.
 */
public class ColorMixActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_mix);

        getSupportFragmentManager().beginTransaction().add(R.id.mix_frame,new ColorMixFragment()).commit();
    }
}
