package com.zcj.mreader.utils;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zcj.mreader.R;

public class ImgLoadUtil {
    private static ImgLoadUtil INSTANCE;
    public static int DF_MEIZI=R.drawable.img_default_meizi;
    public static int DF_MOVIES=R.drawable.img_default_movie;
    public static int DF_BOOK=R.drawable.img_default_book;

    private ImgLoadUtil(){

    }
    public static ImgLoadUtil Instance(){
        if (INSTANCE==null){
            INSTANCE=new ImgLoadUtil();
        }
        return INSTANCE;
    }
    public static void dispalyImage(String url, ImageView imageView,int type){
        Glide.with(imageView.getContext())
                .load(url)
                .crossFade(500)
                .placeholder(type)
                .error(type)
                .into(imageView);
    }

}