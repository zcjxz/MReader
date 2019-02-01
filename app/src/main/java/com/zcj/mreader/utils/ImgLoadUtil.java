package com.zcj.mreader.utils;


import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;

import com.zcj.mreader.R;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class ImgLoadUtil {
    private static ImgLoadUtil INSTANCE;
    public static int DF_MEIZI=R.drawable.img_default_meizi;
    public static int DF_MOVIES=R.drawable.img_default_movie;
    public static int DF_BOOK=R.drawable.img_default_book;
    public static String TAG = "ImgLoadUtil";

    private ImgLoadUtil(){

    }

    public static void wirteBitmap2File(Bitmap bitmap,String filePath,String fileName) throws IOException {
        File dirFile = new File(filePath);
        if (!dirFile.exists()){
            dirFile.mkdirs();
        }
        File imgFile = new File(filePath + File.separator + fileName);
        FileOutputStream os = new FileOutputStream(imgFile);
        BufferedOutputStream bos = new BufferedOutputStream(os);
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
        bos.flush();
        bos.close();
        os.close();
    }

    public static synchronized ImgLoadUtil Instance(){
        if (INSTANCE==null){
            INSTANCE=new ImgLoadUtil();
        }
        return INSTANCE;
    }
    public static void displayImage(String url, final ImageView imageView, int type){
        RequestOptions options = new RequestOptions()
                .placeholder(type)
                .error(type);
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
//                .apply(options)
//                .into(new DriverViewTarget(imageView));
                .into(imageView);
    }

    public static void displayImage(String url, ImageView imageView){
        RequestOptions options = new RequestOptions().centerCrop();
        Glide.with(imageView.getContext())
//                .asBitmap()
                .load(url)
                .apply(options)
                .into(imageView);
    }
    public static void displayLocalImage(int id,ImageView imageView){
        Glide.with(imageView.getContext())
                .load(id)
                .into(imageView);
    }

}
