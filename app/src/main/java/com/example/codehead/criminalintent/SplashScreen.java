package com.example.codehead.criminalintent;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private ViewPager slideViewPager;
    private LinearLayout DotLayout;
    private SliderAdapter sliderAdapter;

    private TextView[] dots;

    private Button next, prev, finish;

    int mCurrentPage;

    boolean sliderFlag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);
//        setContentView(R.layout.activity_main);
//
//        setContentView(R.layout.activity_splash_screen);

        slideViewPager  = findViewById(R.id.slider);

        DotLayout = findViewById(R.id.dots);
        sliderAdapter = new SliderAdapter(this);

        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);
        finish = findViewById(R.id.finish);

        slideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        slideViewPager.addOnPageChangeListener(viewListener);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideViewPager.setCurrentItem(mCurrentPage + 1);

            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    public void addDotsIndicator(int pos) {
        dots = new TextView[3];
        DotLayout.removeAllViews();

        for (int i =0; i < dots.length; ++i) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            DotLayout.addView(dots[i]);
        }

        if(dots.length > 0) {
            dots[pos].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage = position;

            if(position ==0) {
                next.setEnabled(true);
                prev.setEnabled(false);

                prev.setVisibility(View.INVISIBLE);

                finish.setVisibility(View.INVISIBLE);


            } else if(position == dots.length-1) {
                next.setEnabled(false);
                prev.setEnabled(true);

                prev.setVisibility(View.VISIBLE);
                next.setVisibility(View.INVISIBLE);

                finish.setVisibility(View.VISIBLE);

                sliderFlag = true;

            } else {
                next.setEnabled(true);
                prev.setEnabled(true);

                prev.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);

                finish.setVisibility(View.INVISIBLE);


            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
