package com.joymacharia.collaboapp.ui.myTasks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.joymacharia.collaboapp.PagerAdapter;
import com.joymacharia.collaboapp.R;

public class MyTasksFragment extends Fragment {

    private MyTasksViewModel myTasksViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myTasksViewModel =
                ViewModelProviders.of(this).get(MyTasksViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_tasks, container, false);
        final TextView textView = root.findViewById(R.id.text_my_tasks);
        myTasksViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       //Toolbar toolbar = view.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //Create an instance of tab layout and add the tabs
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_layout_1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_layout_2));
        //set the tab to fill the layout
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //use the pager adapter to manage screens
        //create an instance of the view pager
        final ViewPager viewPager = view.findViewById(R.id.view_pager);
        //create an instance of the Pager Adapter Class
        final PagerAdapter pagerAdapter = new PagerAdapter(getParentFragmentManager(), tabLayout.getTabCount());
        //Set the adapter to the viewpager
        viewPager.setAdapter(pagerAdapter);
        //set listener for clicks
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
