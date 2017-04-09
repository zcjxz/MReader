package com.zcj.mreader.base;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zcj.mreader.R;
import com.zcj.mreader.ui.WebActivity;

public abstract class BaseFragment extends Fragment{
    protected boolean isVisible=false;
    protected boolean isPrepared=false;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            isVisible=true;
            onVisible();
        }else{
            isVisible=false;
            onInvisible();
        }
    }

    protected void onVisible(){
        if (isPrepared&&isVisible){
            lazyLoad();
        }
    }
    protected void onInvisible(){

    }

    /**
     * 懒加载数据
     * 使用说明：
     * 要重写onActivityCreate，把isPrepared设置为true
     */
    protected abstract void lazyLoad();
    ImageView ivLoading;
    ImageView ivError;
    View loadingView;
    View errorView;
    private Animation rotate;

    protected void initView(FrameLayout rootView){
        setErrorView(rootView);
        setLoadingView(rootView);
    }

    protected void setLoadingView(FrameLayout rootView){
        //填充界面
        LayoutInflater inflater=LayoutInflater.from(getContext());
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
        rotate = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anim);
//        rotate=new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//        rotate.setDuration(800);
//        LinearInterpolator lir = new LinearInterpolator();
//        rotate.setInterpolator(lir);
//        rotate.setRepeatCount(-1);
//        rotate.setRepeatMode(Animation.RESTART);
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
        LayoutInflater inflater=LayoutInflater.from(getContext());
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
