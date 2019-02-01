package com.zcj.mreader.http;



import com.zcj.mreader.bean.gankBean.ImgBean;
import com.zcj.mreader.http.api.APIServer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class ApiRequest {
    public static String D_FULI="福利";
    public static String D_ANDROID="Android";
    public static String D_IOS="IOS";
    public static String D_FRONT="前端";
    private static ApiRequest instance;
    //gank API
    //http://gank.io/api/data/Android/10/1
    private static String gankBaseUrl ="http://gank.io/api/";
    private Retrofit retrofit;
    private APIServer apiServer;

    private ApiRequest(){
    }

    public static APIServer getServer(){
        return ApiManager.getApiServer();
    }

    public static <T> ObservableTransformer<T,T> preRequest(){
        return new ObservableTransformer<T,T>(){
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }



    //7星 API
    //http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-13-16464498_1247940031909047_2763412151866490880_n.jpg?imageInfo
    private static String qiBaseUrl="http://7xi8d6.com1.z0.glb.clouddn.com/";
    private Retrofit qiRetrofit;


    public void createQiRetrofit(){
        if (qiRetrofit==null) {
            qiRetrofit = new Retrofit.Builder()
                    .baseUrl(qiBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    }
    //获取图片信息
    public void getImgInfo(String imgSrc,Observer<ImgBean> observer){
//        createQiRetrofit();
//        Observable<ImgBean> observable = qiServer.getImgInfo(imgSrc);
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
    }
}
