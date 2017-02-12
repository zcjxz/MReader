package com.zcj.mreader.ui.gank;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.zcj.mreader.MainActivity;
import com.zcj.mreader.R;
import com.zcj.mreader.adapter.MyPagerAdapter;
import com.zcj.mreader.base.BaseFragment;
import com.zcj.mreader.ui.gank.child.AndroidFragment;
import com.zcj.mreader.ui.gank.child.FuliFragment;
import com.zcj.mreader.ui.gank.child.XiaFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GankFragment extends BaseFragment {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    SparseArray<Fragment> fragmentMap;
    SparseArray<String> titleMap;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gank, container, false);
        ButterKnife.bind(this,view);
        initFragment();
        viewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager(),fragmentMap,titleMap));
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void initFragment() {
        XiaFragment xiaFragment=new XiaFragment();
        FuliFragment fuliFragment=new FuliFragment();
        AndroidFragment androidFragment=new AndroidFragment();
        fragmentMap=new SparseArray<>();
        fragmentMap.put(0,xiaFragment);
        fragmentMap.put(1,fuliFragment);
        fragmentMap.put(2,androidFragment);
        titleMap=new SparseArray<>();
        titleMap.put(0,"瞎推荐");
        titleMap.put(1,"福利");
        titleMap.put(2,"android");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared=true;
    }

    @Override
    protected void lazyLoad() {

    }
}
