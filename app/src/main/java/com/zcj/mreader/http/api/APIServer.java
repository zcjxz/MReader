package com.zcj.mreader.http.api;


import com.zcj.mreader.base.BaseGankBean;
import com.zcj.mreader.bean.gankBean.AndroidBean;
import com.zcj.mreader.bean.gankBean.EveryDayBean;
import com.zcj.mreader.bean.gankBean.FuliBean;
import com.zcj.mreader.bean.gankBean.IOSBean;
import com.zcj.mreader.bean.gankBean.ImgBean;
import com.zcj.mreader.bean.gankBean.QianBean;
import com.zcj.mreader.bean.gankBean.TuoBean;
import com.zcj.mreader.bean.gankBean.XiuBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface APIServer {
    //获取Android数据
    @GET("data/Android/{num}/{page}")
    Observable<BaseGankBean<AndroidBean>> getAndroidData(@Path("num") int num, @Path("page") int page);

    //获取福利数据
    @GET("data/福利/{num}/{page}")
    Observable<BaseGankBean<FuliBean>> getFuliData(@Path("num") int num, @Path("page") int page);

    //获取IOS数据
    @GET("data/IOS/{num}/{page}")
    Observable<BaseGankBean<IOSBean>> getIOSData(@Path("num") int num, @Path("page") int page);

    //获取前端数据
    @GET("data/前端/{num}/{page}")
    Observable<BaseGankBean<QianBean>> getQianData(@Path("num") int num,@Path("page") int page);

    //获取休息视频数据
    @GET("data/休息视频/{num}/{page}")
    Observable<BaseGankBean<XiuBean>> getXiuData(@Path("num") int num,@Path("page") int page);

    @GET("data/拓展资源/{num}/{page}")
    Observable<BaseGankBean<TuoBean>> getTuoData(@Path("num") int num, @Path("page") int page);

    //获取每日推荐
    @GET("day/{year}/{month}/{day}")
    Observable<EveryDayBean> getEveryDayData(@Path("year") int year,@Path("month") int month,@Path("day") int day);

    //获取更新历史
    @GET("day/history")
    Observable<BaseGankBean<String>> getLastDay();
    //获取图片信息
    @GET("{src}?imageInfo")
    Observable<ImgBean> getImgInfo(@Path("src") String src);
}
