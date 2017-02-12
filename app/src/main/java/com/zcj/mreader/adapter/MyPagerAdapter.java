package com.zcj.mreader.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;



public class MyPagerAdapter extends FragmentPagerAdapter {
    SparseArray<Fragment> fragmentMap;
    SparseArray<String> titleMap;
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyPagerAdapter(FragmentManager fm, SparseArray<Fragment> fragmentMap,SparseArray<String> titleMap){
        super(fm);
        this.fragmentMap=fragmentMap;
        this.titleMap=titleMap;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentMap.get(position);
    }

    @Override
    public int getCount() {
        return fragmentMap.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleMap.get(position);
    }
}
