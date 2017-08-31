package com.zcj.mreader.ui.gank.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcj.mreader.R;
import com.zcj.mreader.base.BaseFragment;
import com.zcj.mreader.bean.gankBean.AndroidBean;
import com.zcj.mreader.bean.gankBean.FuliBean;
import com.zcj.mreader.bean.gankBean.QianBean;
import com.zcj.mreader.bean.gankBean.TuoBean;
import com.zcj.mreader.http.HttpUtil;
import com.zcj.mreader.utils.ImgLoadUtil;
import com.zcj.mreader.utils.StartUtil;


import java.util.ArrayList;

import rx.Observer;


public class XiaFragment extends BaseFragment {
    private boolean isFirst=true;
    private View rootView;
    private ArrayList<ImageView> androidImgs;
    private ArrayList<TextView> androidDes;
    private ArrayList<ImageView> qianImgs;
    private ArrayList<TextView> qianDes;
    private ImageView fuliImg;
    private ArrayList<AndroidBean> androidData;
    private ArrayList<QianBean> qianData;
    private ArrayList<FuliBean> fulData;
    private View androidItem;
    private View fuliItem;
    private View qianItem;
    private View tuoItem;
    private ArrayList<ImageView> tuoImgs;
    private ArrayList<TextView> tuoDes;
    private ArrayList<TuoBean> tuoData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_xia, container, false);
        return rootView;
    }

    private View getView(View rootView,int id){
        return rootView.findViewById(id);
    }
    private TextView getTextView(View itemView,int id){
        return (TextView) itemView.findViewById(id);
    }
    private ImageView getImageView(View itemView,int id){
        return (ImageView) itemView.findViewById(id);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared=true;
        initView();
        lazyLoad();
    }
    private void initView(){
        androidItem = getView(rootView, R.id.item_android);
        fuliItem = getView(rootView, R.id.item_fuli);
        qianItem = getView(rootView, R.id.item_qian);
        tuoItem = getView(rootView, R.id.item_tuo);
        //android模块初始化
        TextView androidTitle = getTextView(androidItem, R.id.title);
        androidTitle.setText("Android");
        androidImgs = new ArrayList<>();
        ImageView androidImg0 = getImageView(androidItem, R.id.img1);
        ImageView androidImg1 = getImageView(androidItem, R.id.img2);
        ImageView androidImg2 = getImageView(androidItem, R.id.img3);
        androidImgs.add(androidImg0);
        androidImgs.add(androidImg1);
        androidImgs.add(androidImg2);
        androidDes = new ArrayList<>();
        TextView androidDes1 = getTextView(androidItem, R.id.des1);
        TextView androidDes2 = getTextView(androidItem, R.id.des2);
        TextView andriodDes3 = getTextView(androidItem, R.id.des3);
        androidDes.add(androidDes1);
        androidDes.add(androidDes2);
        androidDes.add(andriodDes3);
        //fuli初始化
        TextView fuliTitle = getTextView(fuliItem, R.id.title);
        fuliTitle.setText("福利");
        fuliImg = getImageView(fuliItem, R.id.img);
        //前端初始化
        TextView qianTitle = getTextView(qianItem, R.id.title);
        qianTitle.setText("前端");
        qianImgs = new ArrayList<>();
        ImageView iosImg0 = getImageView(qianItem, R.id.img1);
        ImageView iosImg1 = getImageView(qianItem, R.id.img2);
        ImageView iosImg2 = getImageView(qianItem, R.id.img3);
        qianImgs.add(iosImg0);
        qianImgs.add(iosImg1);
        qianImgs.add(iosImg2);
        qianDes = new ArrayList<>();
        TextView iosDes1 = getTextView(qianItem, R.id.des1);
        TextView iosDes2 = getTextView(qianItem, R.id.des2);
        TextView iosDes3 = getTextView(qianItem, R.id.des3);
        qianDes.add(iosDes1);
        qianDes.add(iosDes2);
        qianDes.add(iosDes3);
        //拓展资料
        TextView tuoTitle = getTextView(tuoItem, R.id.title);
        tuoTitle.setText("拓展资料");
        tuoImgs = new ArrayList<>();
        ImageView tuoImg1 = getImageView(tuoItem, R.id.img1);
        ImageView tuoImg2 = getImageView(tuoItem, R.id.img2);
        tuoImgs.add(tuoImg1);
        tuoImgs.add(tuoImg2);
        tuoDes = new ArrayList<>();
        TextView tuoDes1 = getTextView(tuoItem, R.id.des1);
        TextView tuoDes2 = getTextView(tuoItem, R.id.des2);
        tuoDes.add(tuoDes1);
        tuoDes.add(tuoDes2);
        //数据初始化
        androidData=new ArrayList<>();
        qianData=new ArrayList<>();
        fulData=new ArrayList<>();
        tuoData = new ArrayList<>();
    }

    private void setData(){
        androidData.clear();
        qianData.clear();
        fulData.clear();
        tuoData.clear();
        HttpUtil.getInstance().getAndroidData(3, 1, new Observer<AndroidBean>() {
            @Override
            public void onCompleted() {
                for (int i=0; i<androidImgs.size();i++){
                    final AndroidBean androidBean = androidData.get(i);
                    androidDes.get(i).setText(androidBean.getDesc());
                    if (androidBean.getImages()!=null&&androidBean.getImages().size()>0){
                        ImgLoadUtil.dispalyImage(androidBean.getImages().get(0),androidImgs.get(i));
                    }else{
                        ImgLoadUtil.displayLocalImage(R.drawable.img_default_meizi,androidImgs.get(i));
                    }
                    androidImgs.get(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            StartUtil.startWebActivity(getContext(),androidBean.getUrl());
                        }
                    });
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AndroidBean androidBean) {
                androidData.add(androidBean);
            }
        });
        HttpUtil.getInstance().getQianData(3, 1, new Observer<QianBean>() {
            @Override
            public void onCompleted() {
                for (int i=0; i<qianImgs.size();i++){
                    final QianBean qianBean = qianData.get(i);
                    qianDes.get(i).setText(qianBean.getDesc());
                    if (qianBean.getImages()!=null&&qianBean.getImages().size()>0){
                        ImgLoadUtil.dispalyImage(qianBean.getImages().get(0),qianImgs.get(i));
                    }else{
                        ImgLoadUtil.displayLocalImage(R.drawable.img_default_meizi,qianImgs.get(i));
                    }
                    qianImgs.get(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            StartUtil.startWebActivity(getContext(),qianBean.getUrl());
                        }
                    });
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(QianBean qianBean) {
                qianData.add(qianBean);
            }
        });
        HttpUtil.getInstance().getFuliData(1, 1, new Observer<FuliBean>() {
            @Override
            public void onCompleted() {
                final FuliBean fuliBean = fulData.get(0);
                ImgLoadUtil.dispalyImage(fuliBean.getUrl(),fuliImg);
                //暂时跳转后有问题，先不用
//                fuliImg.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        StartUtil.startWebActivity(getContext(),fuliBean.getUrl());
//                    }
//                });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(FuliBean fuliBean) {
                fulData.add(fuliBean);
            }
        });
        HttpUtil.getInstance().getTuoData(2, 1, new Observer<TuoBean>() {
            @Override
            public void onCompleted() {
                for (int i = 0; i< tuoImgs.size(); i++){
                    final TuoBean tuoBean = tuoData.get(i);
                    tuoDes.get(i).setText(tuoBean.getDesc());
                    if (tuoBean.getImages()!=null&&tuoBean.getImages().size()>0){
                        ImgLoadUtil.dispalyImage(tuoBean.getImages().get(0),tuoImgs.get(i));
                    }else{
                        ImgLoadUtil.displayLocalImage(R.drawable.img_default_meizi,tuoImgs.get(i));
                    }
                    tuoImgs.get(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            StartUtil.startWebActivity(getContext(),tuoBean.getUrl());
                        }
                    });
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(TuoBean tuoBean) {
                tuoData.add(tuoBean);
            }
        });
    }
    @Override
    protected void lazyLoad() {
        if (isFirst){
            setData();
        }
    }
    //获取到最新的更新日期
//    @Subscribe(threadMode = ThreadMode.BACKGROUND)
//    public void handleEvent(GetLastEvent event){
//        String lastDay = event.getDay();
//        String[] days = lastDay.split("-");
//        int year = Integer.parseInt(days[0]);
//        int month = Integer.parseInt(days[1]);
//        int day = Integer.parseInt(days[2]);
//        HttpUtil.getInstance().getEveryDayData(year, month, day, new Observer<EveryDayBean>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(EveryDayBean everyDayBean) {
//                List<String> category = everyDayBean.getCategory();
//            }
//        });
//    }


}
