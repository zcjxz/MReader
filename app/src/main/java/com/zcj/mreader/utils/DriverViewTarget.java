package com.zcj.mreader.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.zcj.mreader.event.UpdateImgSize;

import org.greenrobot.eventbus.EventBus;

/**
 * 用于设置福利界面的图片缩放模式
 */
public class DriverViewTarget extends BitmapImageViewTarget {

    public static class ImgSize{
        public ImgSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int width;
        public int height;
    }
    private static final String TAG = "DriverViewTarget";
    ImageView.ScaleType scaleType=ImageView.ScaleType.FIT_XY;
    public DriverViewTarget(ImageView view) {
        super(view);
    }

    @Override
    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
        if (resource!=null){
            view.setScaleType(scaleType);
            float scale = (float)resource.getHeight()/resource.getWidth();
            Log.d(TAG, "scale: "+ scale);
            Log.d(TAG, "onResourceReady: "+"width: "+resource.getWidth()+" height: "+resource.getHeight());
//            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
//            layoutParams.height= (int) (view.getMeasuredWidth()*scale);
            Log.d(TAG, "finalWidth: "+view.getWidth());
            Log.d(TAG, "finalHeight: "+(int) (view.getWidth()*scale));
            view.setLayoutParams(new FrameLayout.LayoutParams(view.getMeasuredWidth(),(int) (view.getWidth()*scale)));
        }
        super.onResourceReady(resource, transition);
    }

    @Override
    public void onLoadStarted(Drawable placeholder) {
        if (placeholder!=null&&view!=null&&view.getScaleType()!= scaleType){
            view.setScaleType(scaleType);
        }
        super.onLoadStarted(placeholder);
    }

    @Override
    public void onLoadFailed(Drawable errorDrawable) {
        if (errorDrawable!=null&&view!=null&&view.getScaleType()!= scaleType){
            view.setScaleType(scaleType);
        }
        super.onLoadFailed(errorDrawable);
    }

    @Override
    public void onLoadCleared(Drawable placeholder) {
        if (placeholder!=null&&view!=null&&view.getScaleType()!= scaleType){
            view.setScaleType(scaleType);
        }
        super.onLoadCleared(placeholder);
    }
}
