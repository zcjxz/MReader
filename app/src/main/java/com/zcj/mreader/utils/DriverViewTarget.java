package com.zcj.mreader.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * 用于设置福利界面的图片缩放模式
 */
public class DriverViewTarget extends BitmapImageViewTarget {
    ImageView.ScaleType scaleType=ImageView.ScaleType.FIT_XY;
    public DriverViewTarget(ImageView view) {
        super(view);
    }

    @Override
    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
        if (resource!=null&&view.getScaleType()!= scaleType){
            view.setScaleType(scaleType);
        }
        super.onResourceReady(resource, glideAnimation);
    }

    @Override
    public void onLoadStarted(Drawable placeholder) {
        if (placeholder!=null&&view!=null&&view.getScaleType()!= scaleType){
            view.setScaleType(scaleType);
        }
        super.onLoadStarted(placeholder);
    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        if (errorDrawable!=null&&view!=null&&view.getScaleType()!= scaleType){
            view.setScaleType(scaleType);
        }
        super.onLoadFailed(e, errorDrawable);
    }

    @Override
    public void onLoadCleared(Drawable placeholder) {
        if (placeholder!=null&&view!=null&&view.getScaleType()!= scaleType){
            view.setScaleType(scaleType);
        }
        super.onLoadCleared(placeholder);
    }
}
