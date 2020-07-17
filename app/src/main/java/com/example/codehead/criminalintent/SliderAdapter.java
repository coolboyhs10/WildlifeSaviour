package com.example.codehead.criminalintent;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    Typeface myfont;



    public  SliderAdapter(Context context) {
        this.context = context;

        myfont = Typeface.createFromAsset(this.context.getAssets(), "fonts/raleway.ttf");
    }

    public int[] slide_images = {
            R.drawable.panda,
            R.drawable.tiger1,
            R.drawable.ele
    };

    public String[] slide_headings = {
            "SAVE",
            "HELP",
            "LOVE"

    };

    public String[] slide_desc = {
            "“Humankind must learn to understand that the life of an animal is in no way less precious than our own”",
            "“Only when the last of the animals horns, tusks, skin and bones have been sold, will mankind realize that money can never buy back our wildlife”",
            "“Wildlife in the world can only be protected by the love of compassionate hearts in the world!”"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object  instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.slide_image);
        TextView sliderHeading = view.findViewById(R.id.slide_header);
        TextView sliderDesc = view.findViewById(R.id.slide_desc);

        sliderHeading.setTypeface(myfont);
        sliderDesc.setTypeface(myfont);

        slideImageView.setImageResource(slide_images[position]);
        sliderHeading.setText(slide_headings[position]);
        sliderDesc.setText(slide_desc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);

    }
}
