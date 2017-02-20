package com.zcj.mreader.http;


import com.zcj.mreader.bean.gankBean.ImgBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface QiServer {
    //获取图片信息
    @GET("{src}?imageInfo")
    Observable<ImgBean> getImgInfo(@Path("src") String src);
}
