package com.zcj.mreader.bean;

import com.zcj.mreader.R;
import com.zcj.mreader.bean.gankBean.AndroidBean;
import com.zcj.mreader.bean.gankBean.FuliBean;
import com.zcj.mreader.bean.gankBean.IOSBean;
import com.zcj.mreader.bean.gankBean.QianBean;
import com.zcj.mreader.bean.gankBean.TuoBean;
import com.zcj.mreader.bean.gankBean.XiaBean;
import com.zcj.mreader.bean.gankBean.XiuBean;

public class TyepFactoryForList implements TypeFactory{
    private final int TYPE_ONE= R.layout.item_one;
    private final int TYPR_TWO=R.layout.item_two;
    private final int TYPE_THEE=R.layout.item_three;
    @Override
    public int type(AndroidBean androidBean) {
        return TYPE_THEE;
    }

    @Override
    public int type(FuliBean fuliBean) {
        return TYPE_ONE;
    }

    @Override
    public int type(IOSBean iosBean) {
        return TYPE_THEE;
    }

    @Override
    public int type(QianBean qianBean) {
        return 0;
    }

    @Override
    public int type(TuoBean tuoBean) {
        return 0;
    }

    @Override
    public int type(XiaBean xiaBean) {
        return 0;
    }

    @Override
    public int type(XiuBean xiuBean) {
        return 0;
    }
}
