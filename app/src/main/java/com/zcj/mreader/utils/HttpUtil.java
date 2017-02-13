package com.zcj.mreader.utils;



import com.zcj.mreader.base.BaseBean;
import com.zcj.mreader.bean.AndroidBean;
import com.zcj.mreader.bean.FuliBean;
import com.zcj.mreader.bean.ImgBean;
import com.zcj.mreader.http.GankServer;
import com.zcj.mreader.http.QiServer;

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
    private static HttpUtil INSTANCE;
    //gank API
    //http://gank.io/api/data/Android/10/1
    private static String gankBaseUrl ="http://gank.io/api/";
    private Retrofit gankRetrofit;
    private GankServer gankServer;

    private HttpUtil(){
    }

    public static HttpUtil getInstance(){
        if (INSTANCE==null){
            INSTANCE=new HttpUtil();
        }
        return INSTANCE;
    }

    private void createGankRetrofit(){
        if (gankRetrofit==null){
            gankRetrofit =new Retrofit.Builder()    //获取Retrofit对象
                    .baseUrl(gankBaseUrl)                   //绑定base url
                    .addConverterFactory(GsonConverterFactory.create())         //使用工厂模式创建Gason的解析器
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   //添加支持rxJava
                    .build();
        }
        if (gankServer==null) {
            gankServer = gankRetrofit.create(GankServer.class);
        }
    }


    public void getAndroidData(int num ,int page,Observer<AndroidBean> observer){
        createGankRetrofit();
        Observable<BaseBean<AndroidBean>> observable = gankServer.getAndroidData(num, page);
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
        createGankRetrofit();
        Observable<BaseBean<FuliBean>> observable = gankServer.getFuliData(num, page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<BaseBean<FuliBean>, Observable<FuliBean>>() {
                    @Override
                    public Observable<FuliBean> call(BaseBean<FuliBean> baseBean) {
                        return Observable.from(baseBean.getResults());
                    }
                })
//                .flatMap(new Func1<FuliBean, Observable<FuliBean>>() {
//                    @Override
//                    public Observable<FuliBean> call(FuliBean fuliBean) {
//                        final FuliBean fuli=fuliBean;
//                        String imgSrc = fuliBean.getUrl().substring(qiBaseUrl.length());
//                        HttpUtil.getInstance().getImgInfo(imgSrc,
//                                new Observer<ImgBean>() {
//                                    @Override
//                                    public void onCompleted() {
//
//                                    }
//
//                                    @Override
//                                    public void onError(Throwable e) {
//
//                                    }
//
//                                    @Override
//                                    public void onNext(ImgBean imgBean) {
//                                        fuli.setImgBean(imgBean);
//                                    }
//                                });
//                        return Observable.just(fuli);
//                    }
//                })
                .subscribe(observer);
    }

    //7星 API
    //http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-13-16464498_1247940031909047_2763412151866490880_n.jpg?imageInfo
    private static String qiBaseUrl="http://7xi8d6.com1.z0.glb.clouddn.com/";
    private Retrofit qiRetrofit;
    private QiServer qiServer;

    public void createQiRetrofit(){
        if (qiRetrofit==null) {
            qiRetrofit = new Retrofit.Builder()
                    .baseUrl(qiBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        if (qiServer==null){
            qiServer=qiRetrofit.create(QiServer.class);
        }
    }
    //获取图片信息
    public void getImgInfo(String imgSrc,Observer<ImgBean> observer){
        createQiRetrofit();
        Observable<ImgBean> observable = qiServer.getImgInfo(imgSrc);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
