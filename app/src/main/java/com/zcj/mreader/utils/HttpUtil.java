package com.zcj.mreader.utils;



import com.zcj.mreader.base.BaseGankBean;
import com.zcj.mreader.bean.gankBean.AndroidBean;
import com.zcj.mreader.bean.gankBean.EveryDayBean;
import com.zcj.mreader.bean.gankBean.FuliBean;
import com.zcj.mreader.bean.gankBean.IOSBean;
import com.zcj.mreader.bean.gankBean.ImgBean;
import com.zcj.mreader.bean.gankBean.QianBean;
import com.zcj.mreader.bean.gankBean.TuoBean;
import com.zcj.mreader.bean.gankBean.XiuBean;
import com.zcj.mreader.bean.other.TimeBean;
import com.zcj.mreader.event.GetLastEvent;
import com.zcj.mreader.http.GankServer;
import com.zcj.mreader.http.QiServer;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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

    public static synchronized HttpUtil getInstance(){
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
        Observable<BaseGankBean<AndroidBean>> observable = gankServer.getAndroidData(num, page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<BaseGankBean<AndroidBean>, Observable<AndroidBean>>() {
                    @Override
                    public Observable<AndroidBean> call(BaseGankBean<AndroidBean> baseGankBean) {
                        return Observable.from(baseGankBean.getResults());
                    }
                })
                .subscribe(observer);
    }

    public void getFuliData(int num , int page , Observer<FuliBean> observer){
        createGankRetrofit();
        Observable<BaseGankBean<FuliBean>> observable = gankServer.getFuliData(num, page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<BaseGankBean<FuliBean>, Observable<FuliBean>>() {
                    @Override
                    public Observable<FuliBean> call(BaseGankBean<FuliBean> baseGankBean) {
                        return Observable.from(baseGankBean.getResults());
                    }
                })
                .subscribe(observer);
    }
    //ios
    public void getIOSData(int num, int page, Observer<IOSBean> observer){
        createGankRetrofit();
        Observable<BaseGankBean<IOSBean>> observable = gankServer.getIOSData(num, page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<BaseGankBean<IOSBean>, Observable<IOSBean>>() {
                    @Override
                    public Observable<IOSBean> call(BaseGankBean<IOSBean> baseGankBean) {
                        return Observable.from(baseGankBean.getResults());
                    }
                })
                .subscribe(observer);
    }
    //前端
    public void getQianData(int num, int page, Observer<QianBean> observer){
        createGankRetrofit();
        Observable<BaseGankBean<QianBean>> observable =gankServer.getQianData(num,page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<BaseGankBean<QianBean>, Observable<QianBean>>() {
                    @Override
                    public Observable<QianBean> call(BaseGankBean<QianBean> baseGankBean) {
                        return Observable.from(baseGankBean.getResults());
                    }
                })
                .subscribe(observer);
    }
    //休息视频
    public void getXiuData(int num, int page, Observer<XiuBean> observer){
        createGankRetrofit();
        Observable<BaseGankBean<XiuBean>> observable=gankServer.getXiuData(num,page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<BaseGankBean<XiuBean>, Observable<XiuBean>>() {
                    @Override
                    public Observable<XiuBean> call(BaseGankBean<XiuBean> baseGankBean) {
                        return Observable.from(baseGankBean.getResults());
                    }
                })
                .subscribe(observer);
    }

    public void getTuoData(int num, int page, Observer<TuoBean> observer){
        createGankRetrofit();
        Observable<BaseGankBean<TuoBean>> observable = gankServer.getTuoData(num, page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<BaseGankBean<TuoBean>, Observable<TuoBean>>() {
                    @Override
                    public Observable<TuoBean> call(BaseGankBean<TuoBean> baseGankBean) {
                        return Observable.from(baseGankBean.getResults());
                    }
                })
                .subscribe(observer);
    }



    public void getEveryDayData(int year, int month, int day, Observer<EveryDayBean> observer){
        createGankRetrofit();
        Observable<EveryDayBean> observable = gankServer.getEveryDayData(year, month, day);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getLastDay(){
        createGankRetrofit();
        Observable<BaseGankBean<String>> observable = gankServer.getLastDay();
        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Func1<BaseGankBean<String>, String>() {
                    @Override
                    public String call(BaseGankBean<String> baseGankBean) {
                        return baseGankBean.getResults().get(0);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        //返回格式："2017-02-16",
                        GetLastEvent event=new GetLastEvent();
                        event.setDay(s);
                        EventBus.getDefault().post(event);
                    }
                });

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
