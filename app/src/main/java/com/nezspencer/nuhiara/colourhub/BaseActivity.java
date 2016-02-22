package com.nezspencer.nuhiara.colourhub;

import android.animation.AnimatorSet;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nezspencer.nuhiara.colourhub.helper.ApplicationVariables;

/**
 *
 * Created by Nnabueze on 2/20/2016.
 */
public class BaseActivity extends AppCompatActivity {

    RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp);
        int top;
        int left;
        int bottom;
        int right;


        layout=(RelativeLayout)findViewById(R.id.temp);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout
                .LayoutParams
                .WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
                RelativeLayout.TRUE);
        bottom=layout.getBottom();
        right=layout.getRight();
        top=layout.getTop();
        left=layout.getLeft();

        TextView c_view=(TextView)findViewById(R.id.c);
        TextView o1_view=(TextView)findViewById(R.id.o1);
        TextView l_view=(TextView)findViewById(R.id.l);
        TextView o_view=(TextView)findViewById(R.id.o);
        TextView u1_view=(TextView)findViewById(R.id.u1);
        TextView r_view=(TextView)findViewById(R.id.r);
        TextView space_view=(TextView)findViewById(R.id.space);
        TextView h_view=(TextView)findViewById(R.id.h);
        TextView u_view=(TextView)findViewById(R.id.u);
        TextView b_view=(TextView)findViewById(R.id.b);



        TranslateAnimation animation=new TranslateAnimation(right,left,bottom,top);
        animation.setDuration(4000);
        animation.setRepeatCount(1);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.setStartOffset(2000);

        TextView c=new TextView(this);
        c.setText("C");
        c.setTextSize(25);
        c.setTextColor(Color.GREEN);
        c.setLayoutParams(params);
        layout.addView(c);


        TranslateAnimation o_anim=initializeAnimation(right, c_view.getRight(),
                bottom, top);
        TextView o_txt=initializeTextView("O", params);
        TranslateAnimation l_anim=initializeAnimation(right, o1_view.getRight(), bottom, o1_view
                .getTop());
        TextView l_txt=initializeTextView("L", params);
        TranslateAnimation o2_anim=initializeAnimation(right, l_view.getRight(), bottom, top);
        TextView o2_txt=initializeTextView("O", params);

        TranslateAnimation u_anim=initializeAnimation(right, o_view.getRight(), bottom, top);
        TextView u_txt=initializeTextView("U", params);

        TranslateAnimation r_anim=initializeAnimation(right, u1_view.getRight(), bottom, top);
        TextView r_txt=initializeTextView("R", params);

        TranslateAnimation sp_anim=initializeAnimation(right, r_view.getRight(), bottom, top);
        TextView sp_txt=initializeTextView("  ", params);

        TranslateAnimation h_anim=initializeAnimation(right, space_view.getRight(), bottom, top);
        TextView h_txt=initializeTextView("H", params);

        TranslateAnimation u2_anim=initializeAnimation(right, h_view.getRight(), bottom, top);
        TextView u2_txt=initializeTextView("U", params);

        TranslateAnimation b_anim=initializeAnimation(right, u_view.getRight(), bottom, top);
        TextView b_txt=initializeTextView("B", params);


        animation.setAnimationListener(setAnimListener(o_anim, o_txt));
        o_anim.setAnimationListener(setAnimListener(l_anim, l_txt));
        l_anim.setAnimationListener(setAnimListener(o2_anim, o2_txt));
        o2_anim.setAnimationListener(setAnimListener(u_anim, u_txt));
        u_anim.setAnimationListener(setAnimListener(r_anim, r_txt));
        r_anim.setAnimationListener(setAnimListener(sp_anim, sp_txt));
        sp_anim.setAnimationListener(setAnimListener(h_anim, h_txt));
        h_anim.setAnimationListener(setAnimListener(u2_anim, u2_txt));
        u_anim.setAnimationListener(setAnimListener(b_anim, b_txt));

        c.startAnimation(animation);
        /*o_txt.startAnimation(o_anim);
        l_txt.startAnimation(l_anim);
        o2_txt.startAnimation(o2_anim);
        u_txt.startAnimation(u_anim);
        r_txt.startAnimation(r_anim);
        sp_txt.startAnimation(sp_anim);
        h_txt.startAnimation(h_anim);
        u2_txt.startAnimation(u2_anim);
        b_txt.startAnimation(b_anim);*/
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

    public Animation.AnimationListener setAnimListener(final TranslateAnimation anim, final TextView textView)
    {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layout.addView(textView);
                textView.startAnimation(anim);
                Toast.makeText(BaseActivity.this,"Started: "+textView.getText(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Toast.makeText(BaseActivity.this,"Started: "+textView.getText(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }
}
