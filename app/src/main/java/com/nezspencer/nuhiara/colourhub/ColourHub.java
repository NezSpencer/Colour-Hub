package com.nezspencer.nuhiara.colourhub;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.nezspencer.nuhiara.colourhub.helper.ApplicationVariables;
import com.nineoldandroids.animation.Animator;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

/**
 * Created by Nnabueze on 1/31/2016.
 */
public class ColourHub extends AppCompatActivity {

    private static final int IMAGE_PIX =6;

    TextView c_view;
    TextView o1_view;
    TextView l_view;
    TextView o_view;
    TextView u1_view;
    TextView r_view;
    TextView h_view;
    TextView u_view;
    TextView b_view;

    private int color[]={
            R.color.red,
            R.color.pink,
            R.color.purple,
            R.color.deep_purple,
            R.color.light_blue,
            R.color.blue,
            R.color.teal,
            R.color.green,
            R.color.light_green,
            R.color.lime,
            R.color.yellow,
            R.color.amber,
            R.color.orange,
            R.color.deep_orange,

    };
    private boolean isBigScreen;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationVariables.getInstance();
        setContentView(R.layout.hub);
        context=this.getApplicationContext();

        findAndColourAllViews();
        makeInvisible();
        animateText();

        FloatingActionButton.LayoutParams layoutParams=new FloatingActionButton.LayoutParams(120,120, Gravity.CENTER);
        layoutParams.setMargins(0, 0, 20, 20);

        ImageView icon=new ImageView(this);
        icon.setImageResource(R.mipmap.ic_launcher);
        FloatingActionButton fab=new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setLayoutParams(layoutParams)
                .build();


        int radius;
        int dimension = 0;
        if(getResources().getConfiguration().smallestScreenWidthDp<600)
        {
            radius=200;
            dimension =80;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            if (getResources().getDisplayMetrics().densityDpi>= DisplayMetrics.DENSITY_HIGH)
            {
                radius=300;
                dimension =100;
            }
        }
        else {
            isBigScreen=true;
            radius = 350;
            dimension =100;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }



        SubActionButton.Builder builder= new SubActionButton.Builder(this);
        FloatingActionButton.LayoutParams params=new FloatingActionButton.LayoutParams(dimension, dimension);
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



        SubActionButton makeUrColour=builder.setContentView(makeUrs)
                .setBackgroundDrawable(getResources().getDrawable(R.drawable.action_menu_blue))
                .build();



        SubActionButton CaptureColourButton=builder.setContentView(capture)
                .build();



        SubActionButton rateButton=builder.setContentView(rateView)
                .setBackgroundDrawable(getResources().getDrawable(R.drawable.action_menu_red))
                .build();





        final FloatingActionMenu actionMenu=new FloatingActionMenu.Builder(this)
                .addSubActionView(predefinedColourButton)
                .addSubActionView(makeUrColour)
                .addSubActionView(rateButton)
                .addSubActionView(CaptureColourButton)
                .setRadius(radius)
                .attachTo(fab)
                .build();


        CaptureColourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ShowcaseView showcaseView=new ShowcaseView.Builder(ColourHub.this)
                        //.setTarget(new ViewTarget(preview))
                        .setContentText("touch any part of the image you snapped to get " +
                                "colour code of" +
                                " touched part")
                        //.replaceEndButton(R.id.showcase)
                        .withMaterialShowcase()
                        .build();

                showcaseView.overrideButtonClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        if (captureIntent.resolveActivity(getPackageManager()) != null)
                            startActivityForResult(captureIntent, IMAGE_PIX);

                        actionMenu.close(true);
                        showcaseView.hide();

                    }
                });
                showcaseView.setButtonText("Got It");
                showcaseView.setShouldCentreText(true);

            }
        });

        predefinedColourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ColourHub.this, ColourListActivity.class));

                actionMenu.close(true);
            }
        });

        makeUrColour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ColourHub.this, ColorMixActivity.class));
                actionMenu.close(true);

            }
        });

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

                actionMenu.close(true);
            }
        });
    }

    public void findAndColourAllViews()
    {
        c_view=(TextView)findViewById(R.id.c);
        c_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        c_view.setTypeface(Typeface.createFromAsset(getAssets(), "Raleway-Medium.ttf"));
        o1_view=(TextView)findViewById(R.id.o1);
        o1_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        o1_view.setTypeface(Typeface.createFromAsset(getAssets(), "Raleway-Medium.ttf"));
        l_view=(TextView)findViewById(R.id.l);
        l_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        l_view.setTypeface(Typeface.createFromAsset(getAssets(), "Raleway-Medium.ttf"));
        o_view=(TextView)findViewById(R.id.o);
        o_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        o_view.setTypeface(Typeface.createFromAsset(getAssets(), "Raleway-Medium.ttf"));
        u1_view=(TextView)findViewById(R.id.u1);
        u1_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        u1_view.setTypeface(Typeface.createFromAsset(getAssets(), "Raleway-Medium.ttf"));
        r_view=(TextView)findViewById(R.id.r);
        r_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        r_view.setTypeface(Typeface.createFromAsset(getAssets(), "Raleway-Medium.ttf"));
        h_view=(TextView)findViewById(R.id.h);
        h_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        h_view.setTypeface(Typeface.createFromAsset(getAssets(), "Raleway-Medium.ttf"));
        u_view=(TextView)findViewById(R.id.u);
        u_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        u_view.setTypeface(Typeface.createFromAsset(getAssets(), "Raleway-Medium.ttf"));
        b_view=(TextView)findViewById(R.id.b);
        b_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        b_view.setTypeface(Typeface.createFromAsset(getAssets(), "Raleway-Medium.ttf"));
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
            final LinearLayout preview=(LinearLayout)view.findViewById(R.id.imagePreview);
            final TextView colourCode=(TextView)view.findViewById(R.id.preview_colour_code);
            final Button cancel=(Button)view.findViewById(R.id.discard);
            final TextView colorPreview=(TextView)view.findViewById(R.id.previewColor);

            final AlertDialog imagePopUp=new AlertDialog.Builder(this)
                    .setView(view)
                    .setCancelable(false)
                    .create();
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    preview.setBackgroundDrawable(null);
                    imagePopUp.cancel();
                    imagePopUp.dismiss();
                }
            });

            imagePopUp.getWindow().setGravity(Gravity.BOTTOM);

            Bitmap bitmap;
            if (isBigScreen)
            {
                bitmap=getResizedBitmap(snappedImage,500,500);
            }
            else if (getResources().getConfiguration().smallestScreenWidthDp<=320)
                bitmap=getResizedBitmap(snappedImage,250,250);
            else{bitmap=getResizedBitmap(snappedImage,300,300);}

            preview.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));


            preview.setOnTouchListener(new LinearLayout.OnTouchListener(){
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    int x=(int)motionEvent.getX();
                    int y=(int)motionEvent.getY();
                    LinearLayout layout=(LinearLayout)view;
                    Bitmap touchedPart=((BitmapDrawable)layout.getBackground()).getBitmap();

                    if (x<touchedPart.getWidth() && x>=0 && y<touchedPart.getHeight() && y>=0)
                    {
                        int pixel=touchedPart.getPixel(x,y);

                        String alpha = intToHex(Color.alpha(pixel));
                        String red = intToHex(Color.red(pixel));
                        String green = intToHex(Color.green(pixel));
                        String blue = intToHex(Color.blue(pixel));

                        colourCode.setText("#" + alpha + red + green + blue);
                        colorPreview.setBackgroundColor(Color.parseColor("#"+alpha+red+green+blue));
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

    public void animateText()
    {

        YoYo.with(Techniques.RotateInUpLeft)
                .duration(1500)
                .delay(500)
                .withListener(setListener(o1_view))
                .playOn(c_view);



        YoYo.with(Techniques.RotateInUpLeft)
                .duration(1500)
                .delay(1000)
                .withListener(setListener(l_view))
                .playOn(o1_view);

        YoYo.with(Techniques.RotateInUpLeft)
                .duration(1500)
                .delay(1500)
                .withListener(setListener(o_view))
                .playOn(l_view);

        YoYo.with(Techniques.RotateInUpLeft)
                .duration(1500)
                .delay(2000)
                .withListener(setListener(u1_view))
                .playOn(o_view);

        YoYo.with(Techniques.RotateInUpLeft)
                .duration(1500)
                .delay(2500)
                .withListener(setListener(r_view))
                .playOn(u1_view);

        YoYo.with(Techniques.RotateInUpLeft)
                .duration(1500)
                .delay(3000)
                .withListener(setListener(h_view))
                .playOn(r_view);

        YoYo.with(Techniques.RotateInUpLeft)
                .duration(1500)
                .delay(3500)
                .withListener(setListener(u_view))
                .playOn(h_view);

        YoYo.with(Techniques.RotateInUpLeft)
                .duration(1500)
                .delay(4000)
                .withListener(setListener(b_view))
                .playOn(u_view);

        YoYo.with(Techniques.RotateInUpLeft)
                .duration(1500)
                .delay(4500)
                //.withListener(setListener(b_view))
                .playOn(b_view);
    }

    public Animator.AnimatorListener setListener(final TextView view)
    {
        return new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e("ttt"," "+view.getText().toString()+" is starting");


            }



            @Override
            public void onAnimationEnd(Animator animation) {

                if (animation.isRunning())
                {
                    Log.e("ttt"," "+view.getText().toString()+" is running");
                    view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
    }

    public void makeInvisible()
    {
        o1_view.setVisibility(View.INVISIBLE);
        l_view.setVisibility(View.INVISIBLE);
        o_view.setVisibility(View.INVISIBLE);
        u1_view.setVisibility(View.INVISIBLE);
        r_view.setVisibility(View.INVISIBLE);
        h_view.setVisibility(View.INVISIBLE);
        u_view.setVisibility(View.INVISIBLE);
        b_view.setVisibility(View.INVISIBLE);
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
