package com.zcj.mreader;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.zcj.mreader.adapter.MyPagerAdapter;
import com.zcj.mreader.base.BaseActivity;
import com.zcj.mreader.ui.book.BookFragment;
import com.zcj.mreader.ui.gank.GankFragment;
import com.zcj.mreader.ui.movies.MoviesFragment;
import com.zcj.mreader.ui.nav.AboutActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.btn_gank)
    ImageView btnGank;
    @BindView(R.id.btn_movies)
    ImageView btnMovies;
    @BindView(R.id.btn_book)
    ImageView btnBook;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    private SparseArray<Fragment> fragmentMap;
    private SparseArray<ImageView> btnMap;
    private SparseArray<String> titleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initView(flContent);
        initFragment();
        initListener();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTabSelected(0);
    }

    private void initListener() {
        btnBook.setOnClickListener(this);
        btnGank.setOnClickListener(this);
        btnMovies.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setTabSelected(position);
            }
        });
    }

    private void initFragment(){
        fragmentMap=new SparseArray<>();
        fragmentMap.put(0,new GankFragment());
        fragmentMap.put(1,new MoviesFragment());
        fragmentMap.put(2,new BookFragment());
        btnMap=new SparseArray<>();
        btnMap.put(0,btnGank);
        btnMap.put(1,btnMovies);
        btnMap.put(2,btnBook);
        titleMap=new SparseArray<>();
        titleMap.put(0,"干货");
        titleMap.put(1,"电影");
        titleMap.put(2,"书籍");
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),fragmentMap,titleMap));
        viewPager.setOffscreenPageLimit(3);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.titlebar_menu);
        }
    }
    public void setTitle(String title){
        toolbar.setTitle(title);
    }

    private void setTabSelected(int position){
        for (int i=0;i<btnMap.size();i++){
            if (position==i){
                btnMap.get(position).setSelected(true);
                setTitle(titleMap.get(position));
            }else{
                btnMap.get(i).setSelected(false);
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_gank:
                if (viewPager.getCurrentItem()!=0){
                    btnBook.setSelected(false);
                    btnGank.setSelected(true);
                    btnMovies.setSelected(false);
                    viewPager.setCurrentItem(0);
                }
                break;
            case R.id.btn_movies:
                if (viewPager.getCurrentItem()!=1){
                    btnBook.setSelected(false);
                    btnMovies.setSelected(true);
                    btnGank.setSelected(false);
                    viewPager.setCurrentItem(1);
                }
                break;
            case R.id.btn_book:
                if (viewPager.getCurrentItem()!=2){
                    btnBook.setSelected(true);
                    btnGank.setSelected(false);
                    btnMovies.setSelected(false);
                    viewPager.setCurrentItem(2);
                }
                break;
        }
    }

}
