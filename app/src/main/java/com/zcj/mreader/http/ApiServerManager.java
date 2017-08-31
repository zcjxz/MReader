package com.zcj.mreader.http;


import com.zcj.mreader.http.api.GankServer;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServerManager {
    private static String gankBaseUrl ="http://gank.io/api/";
    public static ApiServerManager Instance;
    public static Retrofit gankRetrofit;
    public static GankServer gankServer;

    private ApiServerManager(){
    }

    public static ApiServerManager getInstance(){
        if (Instance==null){
            synchronized (ApiServerManager.class){
                Instance=new ApiServerManager();
            }
        }
        return Instance;
    }

    public GankServer createGankServer(){
        if (gankServer==null){
            if (gankRetrofit==null){
                gankRetrofit=new Retrofit.Builder()
                        .baseUrl(gankBaseUrl)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            gankServer=gankRetrofit.create(GankServer.class);
        }
        return gankServer;
    }
}
