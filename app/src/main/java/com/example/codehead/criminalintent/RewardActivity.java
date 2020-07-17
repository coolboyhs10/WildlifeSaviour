package com.example.codehead.criminalintent;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class RewardActivity extends AppCompatActivity {
    Typeface myfont;

    TextView h1, h2, h3, h4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_reward);

        myfont = Typeface.createFromAsset(this.getApplicationContext().getAssets(), "fonts/raleway.ttf");

        h1 = findViewById(R.id.textView4);
        h2 = findViewById(R.id.textView5);
        h3 = findViewById(R.id.textView6);

        h1.setTypeface(myfont);
        h2.setTypeface(myfont);
        h3.setTypeface(myfont);
    }
}
