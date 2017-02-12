package com.zcj.mreader.http;


import com.zcj.mreader.base.BaseBean;
import com.zcj.mreader.bean.AndroidBean;
import com.zcj.mreader.bean.FuliBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface DataServer {
    @GET("data/Android/{num}/{page}")
    Observable<BaseBean<AndroidBean>> getAndroidData(@Path("num") int num, @Path("page") int page);

    @GET("data/福利/{num}/{page}")
    Observable<BaseBean<FuliBean>> getFuliData(@Path("num") int num, @Path("page") int page);
}
