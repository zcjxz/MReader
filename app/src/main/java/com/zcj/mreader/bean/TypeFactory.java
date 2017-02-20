package com.zcj.mreader.bean;


import com.zcj.mreader.bean.gankBean.AndroidBean;
import com.zcj.mreader.bean.gankBean.FuliBean;
import com.zcj.mreader.bean.gankBean.IOSBean;
import com.zcj.mreader.bean.gankBean.QianBean;
import com.zcj.mreader.bean.gankBean.TuoBean;
import com.zcj.mreader.bean.gankBean.XiaBean;
import com.zcj.mreader.bean.gankBean.XiuBean;

public interface TypeFactory {
    int type(AndroidBean androidBean);
    int type(FuliBean fuliBean);
    int type(IOSBean iosBean);
    int type(QianBean qianBean);
    int type(TuoBean tuoBean);
    int type(XiaBean xiaBean);
    int type(XiuBean xiuBean);
}
