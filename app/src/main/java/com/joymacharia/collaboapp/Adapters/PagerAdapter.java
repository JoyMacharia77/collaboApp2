package com.joymacharia.collaboapp.Adapters;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.joymacharia.collaboapp.AddTaskFragment;
import com.joymacharia.collaboapp.ViewTasksFragment;


public class PagerAdapter extends FragmentStatePagerAdapter {
    int myNumberOfTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm, numOfTabs);
        this.myNumberOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new ViewTasksFragment();
            case 1: return new AddTaskFragment();
            default:return  null;
        }
    }

    @Override
    public int getCount() {
        return myNumberOfTabs;
    }
}

