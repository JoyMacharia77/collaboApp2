package com.joymacharia.collaboapp.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.joymacharia.collaboapp.R;


public class ViewPagerAdapter  extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    private int images[] = {
            R.drawable.three,
            R.drawable.one,
            R.drawable.six,

    };
    private String titles[] = {
            "Interact",
            "Create",
            "Enjoy",
    };
    private String desc[] = {
            "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor",
            "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor",
            "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor",
    };


    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }
    @NonNull
    @Override
    public Object instantiateItem( @NonNull ViewGroup container , int position){
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.view_pager,container, false);

        ImageView imageView = v.findViewById(R.id.imgViewPager);
        TextView textTitle = v.findViewById(R.id.textTitleViewPager);
        TextView textDesc = v.findViewById(R.id.textdescViewPager);

        imageView.setImageResource(images[position]);
        textTitle.setText(titles[position]);
        textDesc.setText(desc[position]);

        container.addView(v);
        return v;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
        container.removeView((LinearLayout)object);
    }

}



