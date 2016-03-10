package com.nezspencer.nuhiara.colourhub;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nezspencer.nuhiara.colourhub.helper.ApplicationVariables;
import com.nineoldandroids.animation.Animator;

/**
 *
 * Created by Nnabueze on 2/20/2016.
 */
public class BaseActivity extends AppCompatActivity {

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
    RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp);

        findAndColourAllViews();


        if (SystemClock.elapsedRealtime()==2000)
            c_view.setVisibility(View.VISIBLE);

        if (SystemClock.elapsedRealtime()==4000)
            o1_view.setVisibility(View.VISIBLE);

        if (SystemClock.elapsedRealtime()==6000)
            l_view.setVisibility(View.VISIBLE);

        if (SystemClock.elapsedRealtime()==8000)
            o_view.setVisibility(View.VISIBLE);

        if (SystemClock.elapsedRealtime()==10000)
            u1_view.setVisibility(View.VISIBLE);

        if (SystemClock.elapsedRealtime()==12000)
            r_view.setVisibility(View.VISIBLE);

        if (SystemClock.elapsedRealtime()==13000)
            h_view.setVisibility(View.VISIBLE);

        if (SystemClock.elapsedRealtime()==14000)
            u_view.setVisibility(View.VISIBLE);

        if (SystemClock.elapsedRealtime()==14500)
            b_view.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.RotateInUpLeft)
                .duration(3000)
                .delay(2000)
             //   .withListener(setListener(c_view))
                .playOn(c_view);



        YoYo.with(Techniques.RotateInUpLeft)
                .duration(3000)
                .delay(4000)
         //       .withListener(setListener(o1_view))
                .playOn(o1_view);

        YoYo.with(Techniques.RotateInUpLeft)
                .duration(3000)
                .delay(6000)
           //     .withListener(setListener(l_view))
                .playOn(l_view);

        YoYo.with(Techniques.RotateInUpLeft)
                .duration(3000)
                .delay(8000)
             //   .withListener(setListener(o_view))
                .playOn(o_view);

        YoYo.with(Techniques.RotateInUpLeft)
                .duration(3000)
                .delay(10000)
               // .withListener(setListener(u1_view))
                .playOn(u1_view);

        YoYo.with(Techniques.RotateInUpLeft)
                .duration(3000)
                .delay(12000)
                //.withListener(setListener(r_view))
                .playOn(r_view);

        YoYo.with(Techniques.RotateInUpLeft)
                .duration(3000)
                .delay(13000)
                //.withListener(setListener(h_view))
                .playOn(h_view);

        YoYo.with(Techniques.RotateInUpLeft)
                .duration(3000)
                .delay(14000)
               // .withListener(setListener(u_view))
                .playOn(u_view);

        YoYo.with(Techniques.RotateInUpLeft)
                .duration(3000)
                .delay(14500)
               // .withListener(setListener(b_view))
                .playOn(b_view);


    }

    public Animator.AnimatorListener setListener(final TextView view)
    {
        return new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (animation.isRunning())
                    view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
    }
    public void findAndColourAllViews()
    {
        c_view=(TextView)findViewById(R.id.c);
        c_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        o1_view=(TextView)findViewById(R.id.o1);
        o1_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        l_view=(TextView)findViewById(R.id.l);
        l_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        o_view=(TextView)findViewById(R.id.o);
        o_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        u1_view=(TextView)findViewById(R.id.u1);
        u1_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        r_view=(TextView)findViewById(R.id.r);
        r_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        h_view=(TextView)findViewById(R.id.h);
        h_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        u_view=(TextView)findViewById(R.id.u);
        u_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
        b_view=(TextView)findViewById(R.id.b);
        b_view.setTextColor(getResources().getColor(color[(int) (Math.random() * color.length)]));
    }
    public TextView initializeTextView(String text,RelativeLayout.LayoutParams params)
    {
        TextView c=new TextView(this);
        c.setText(text);
        c.setTextSize(25);
        c.setTextColor(Color.GREEN);
        c.setLayoutParams(params);
        return c;
    }

    public TranslateAnimation initializeAnimation(int right,int left,int bottom,int top)
    {
        TranslateAnimation animation=new TranslateAnimation(right,left,bottom,top);
        animation.setDuration(4000);
        animation.setRepeatCount(0);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.setStartOffset(2000);

        return animation;
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (ApplicationVariables.getInstance().ITEMS.isEmpty())
        {
            ApplicationVariables.getInstance().loadColours();
            Toast.makeText(this,"Colour was empty \n baseActivity loaded new colours to save ur " +
                    "ass ",Toast.LENGTH_LONG).show();
        }

    }


}
