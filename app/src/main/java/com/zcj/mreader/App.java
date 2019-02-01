package com.zcj.mreader;

import android.app.Application;

import com.zcj.mreader.http.ApiManager;

/**
 * Created by ZCJ on 2019/1/28.
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        ApiManager.init();
    }

    public static App getInstance(){
        if (instance!=null){
            return instance;
        }else{
            throw new RuntimeException("Application is not init");
        }
    }
}
