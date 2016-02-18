package com.nezspencer.nuhiara.colourhub;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
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
    private static final int IMAGE_PIX =6 ;
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
        ImageView capture=new ImageView(this);
        capture.setImageResource(R.drawable.ic_action_camera);
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
                startActivity(new Intent(ColourHub.this, ColourListActivity.class));
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

        SubActionButton CaptureColourButton=builder.setContentView(capture)
                .build();

        CaptureColourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (captureIntent.resolveActivity(getPackageManager())!=null)
                    startActivityForResult(captureIntent,IMAGE_PIX);
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




        FloatingActionMenu actionMenu=new FloatingActionMenu.Builder(this)
                .addSubActionView(predefinedColourButton)
                .addSubActionView(makeUrColour)
                .addSubActionView(rateButton)
                .addSubActionView(CaptureColourButton)
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

    public String intToHex(int i) {return String.format("%02x",i & 0xFF);}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==IMAGE_PIX && resultCode==RESULT_OK)
        {
            Bundle imageData=data.getExtras();
            Bitmap snappedImage=(Bitmap)imageData.get("data");

            View view=getLayoutInflater().inflate(R.layout.image_preview,null,false);
            final ImageView preview=(ImageView)view.findViewById(R.id.imagePreview);
            final TextView colourCode=(TextView)view.findViewById(R.id.preview_colour_code);
            ImageButton cancel=(ImageButton)view.findViewById(R.id.discard);

            final AlertDialog imagePopUp=new AlertDialog.Builder(this)
                    .setView(view)
                    .setCancelable(false)
                    .create();
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    preview.setImageBitmap(null);
                    imagePopUp.cancel();
                    imagePopUp.dismiss();
                }
            });

                preview.setImageBitmap(snappedImage);


            preview.setOnTouchListener(new ImageView.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    ImageView imageView = (ImageView) v;
                    Bitmap touchedPart = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                    if (x < touchedPart.getWidth() && x >= 0 && y < touchedPart.getHeight() && y >= 0) {

                        int pixel = touchedPart.getPixel(x, y);

                        String alpha = intToHex(Color.red(pixel));
                        String red = intToHex(Color.red(pixel));
                        String green = intToHex(Color.green(pixel));
                        String blue = intToHex(Color.blue(pixel));

                        colourCode.setText("#" + alpha + red + green + blue);

                        return true;
                    }

                    return false;
                }
            });
            imagePopUp.getWindow().setBackgroundDrawable(new ColorDrawable(
                    Color.TRANSPARENT));
            imagePopUp.show();
        }
    }
}
