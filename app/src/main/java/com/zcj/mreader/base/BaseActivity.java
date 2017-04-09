package com.zcj.mreader.base;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zcj.mreader.R;
import com.zcj.mreader.ui.WebActivity;
import com.zcj.mreader.utils.DebugUtil;

import butterknife.BindView;


public class BaseActivity extends AppCompatActivity {
    ImageView ivLoading;
    ImageView ivError;
    View loadingView;
    View errorView;
    private Animation rotate;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //透明状态栏：方案一
//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
//        }
        //透明状态栏：方案二
//        if (Build.VERSION.SDK_INT>=21){
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            |View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
    }

    protected void initView(FrameLayout rootView){
        setErrorView(rootView);
        setLoadingView(rootView);
    }

    protected void setLoadingView(FrameLayout rootView){
        //填充界面
        LayoutInflater inflater=LayoutInflater.from(this);
        loadingView = inflater.inflate(R.layout.loading_layout, null, false);
//        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        loadingView.setLayoutParams(layoutParams);
        ivLoading= (ImageView) loadingView.findViewById(R.id.iv_loading);
        Glide.with(this).load(R.drawable.loading).into(ivLoading);
        //添加界面
        rootView.addView(loadingView);
        loadingView.setVisibility(View.INVISIBLE);
        //设置动画
        rotate =AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        LinearInterpolator lir = new LinearInterpolator();
        rotate.setInterpolator(lir);

    }
    public void showLoadingView(boolean show){
        if (loadingView==null){
            throw new NullPointerException("loadingView is null");
        }
        if (show){
            loadingView.setVisibility(View.VISIBLE);
            ivLoading.startAnimation(rotate);
        }else {
            loadingView.setVisibility(View.INVISIBLE);
            rotate.cancel();
        }
    }
    protected void setErrorView(FrameLayout rootView){
        //填充界面
        LayoutInflater inflater=LayoutInflater.from(this);
        errorView = inflater.inflate(R.layout.error_layout, null, false);
//        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        loadingView.setLayoutParams(layoutParams);
        ivError= (ImageView) errorView.findViewById(R.id.iv_error);
        Glide.with(this).load(R.drawable.error).into(ivError);
        //添加界面
        rootView.addView(errorView);
        errorView.setVisibility(View.INVISIBLE);
    }
    public void showErrorView(boolean show){
        if (errorView==null){
            throw new NullPointerException("errorView is null");
        }
        if (show){
            errorView.setVisibility(View.VISIBLE);
        }else {
            errorView.setVisibility(View.INVISIBLE);
        }
    }
}
