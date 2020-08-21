package com.joymacharia.collaboapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joymacharia.collaboapp.Adapters.ViewPagerAdapter;


public class onBoardActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private Button btnLeft, btnRight;
    private ViewPagerAdapter adapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        init();
    }

    private void init() {
        viewPager = findViewById(R.id.view_pager);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
        dotsLayout = findViewById(R.id.dotsLayout);
        adapter = new ViewPagerAdapter(this);
        addDots(0);
        viewPager.addOnPageChangeListener(listener);
        viewPager.setAdapter(adapter);
//To enable tje v-> go to project structure change to 1.8 in two places

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnRight.getText().toString().equals("Next")) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

                } else {
                    Intent intent = new Intent(onBoardActivity.this, AuthActivity.class);
                    startActivity(intent);
                }
            }
        });
//        btnLeft.setOnClickListener(v -> {
//            if (btnRight.getText().toString().equals("Next")) {
//                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
//            } else {
//                Intent intent = new Intent(onBoardActivity.this, AuthActivity.class);
//                startActivity(intent);
//            }
//        });
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+2);
            }
        });
//        btnLeft.setOnClickListener(v -> viewPager.setCurrentItem(viewPager.getCurrentItem()));
    }

    private void addDots(int position) {
        dotsLayout.removeAllViews();
        dots = new TextView[3];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            //html code that creates dots
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.cycan));
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.midnightBlue));
        }
    }

    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            if (position == 0) {
                btnLeft.setVisibility(View.VISIBLE);
                btnLeft.setEnabled(true);
                btnRight.setText("Next");

            } else if (position == 1) {
                btnLeft.setVisibility(View.GONE);
                btnLeft.setEnabled(true);
                btnRight.setText("Next");

            } else {
                btnLeft.setVisibility(View.GONE);
                btnLeft.setEnabled(true);
                btnRight.setText("Finish");

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}

