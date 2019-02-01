package com.zcj.mreader.http;


import com.zcj.mreader.BuildConfig;
import com.zcj.mreader.http.api.APIServer;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static String gankBaseUrl ="http://gank.io/api/";
    private static ApiManager instance;
    private static Retrofit apiRetrofit;
    private static APIServer apiServer;

    private ApiManager(){
    }

    public static ApiManager getInstance(){
        if (instance ==null){
            throw new RuntimeException("please init ApiManager on Application first!");
        }
        return instance;
    }

    public static APIServer getApiServer(){
        if (apiServer==null){
            throw new RuntimeException("please init ApiManager on Application first!");
        }
        return apiServer;
    }

    public static void init(){
        if (apiRetrofit==null){
            synchronized (ApiManager.class){
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                if (BuildConfig.DEBUG){
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    builder.addInterceptor(logging);
                }
                OkHttpClient okHttpClient = builder.build();
                apiRetrofit =new Retrofit.Builder()
                        .client(okHttpClient)
                        .baseUrl(gankBaseUrl)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }
        if (apiServer == null){
            synchronized (ApiManager.class){
                apiServer = apiRetrofit.create(APIServer.class);
            }
        }
    }
}
