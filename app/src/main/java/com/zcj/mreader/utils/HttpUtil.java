package com.zcj.mreader.utils;



import com.zcj.mreader.base.BaseBean;
import com.zcj.mreader.bean.AndroidBean;
import com.zcj.mreader.bean.FuliBean;
import com.zcj.mreader.http.DataServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class HttpUtil {
    public static String D_FULI="福利";
    public static String D_ANDROID="Android";
    public static String D_IOS="IOS";
    public static String D_FRONT="前端";
    //http://gank.io/api/data/Android/10/1
    private static String BASE_URL="http://gank.io/api/";
    private static HttpUtil INSTANCE;
    private final Retrofit retrofit;
    private final DataServer dataServer;

    private HttpUtil(){
        retrofit=new Retrofit.Builder()    //获取Retrofit对象
                .baseUrl(BASE_URL)                   //绑定base url
                .addConverterFactory(GsonConverterFactory.create())         //使用工厂模式创建Gason的解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   //添加支持rxJava
                .build();
        dataServer = retrofit.create(DataServer.class);
    }
    public static HttpUtil getInstance(){
        if (INSTANCE==null){
            INSTANCE=new HttpUtil();
        }
        return INSTANCE;
    }

    public void getAndroidData(int num ,int page,Observer<AndroidBean> observer){
        Observable<BaseBean<AndroidBean>> observable = dataServer.getAndroidData(num, page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<BaseBean<AndroidBean>, Observable<AndroidBean>>() {
                    @Override
                    public Observable<AndroidBean> call(BaseBean<AndroidBean> baseBean) {
                        return Observable.from(baseBean.getResults());
                    }
                })
                .subscribe(observer);
    }

    public void getFuliData(int num , int page , Observer<FuliBean> observer){
        Observable<BaseBean<FuliBean>> observable = dataServer.getFuliData(num, page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<BaseBean<FuliBean>, Observable<FuliBean>>() {
                    @Override
                    public Observable<FuliBean> call(BaseBean<FuliBean> baseBean) {
                        return Observable.from(baseBean.getResults());
                    }
                })
                .subscribe(observer);
    }
}
