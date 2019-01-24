package com.zcj.mreader.utils;


import android.content.Context;
import android.content.Intent;

import com.zcj.mreader.ui.WebActivity;


public class StartUtil {
    private static StartUtil INSTANCE;
    private StartUtil(){
    }
    public static synchronized StartUtil getInstance(){
        if (INSTANCE==null){
            INSTANCE=new StartUtil();
        }
        return INSTANCE;
    }
    public static void startWebActivity(Context context,String url){
        Intent intent=new Intent(context, WebActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }
}
