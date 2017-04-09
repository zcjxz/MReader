package com.zcj.mreader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zcj.mreader.R;
import com.zcj.mreader.bean.gankBean.FuliBean;
import com.zcj.mreader.utils.ImgLoadUtil;
import com.zcj.mreader.utils.StartUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FuliAdapter extends RecyclerView.Adapter<FuliAdapter.ViewHolder>{
    private ArrayList<FuliBean> dataList;
    private Context context;
    private final int minImgSize=550;
    private final String reduceFormat="?imageView2/3/h/";
    private final String qiBaseUrl="http://7xi8d6.com1.z0.glb.clouddn.com/";
    public FuliAdapter(ArrayList<FuliBean> dataList) {
        this.dataList=dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_fuli, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final FuliBean fuliBean=dataList.get(position);
//        ImgBean imgBean=fuliBean.getImgBean();
//        int scale = imgBean.getHeight() / imgBean.getWidth();
//        ImageView img=holder.img;
//        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) img.getLayoutParams();
//        layoutParams.height=layoutParams.width*scale;
//        img.setLayoutParams(layoutParams);
        String imgUrl = fuliBean.getUrl() + reduceFormat + minImgSize;
        ImgLoadUtil.dispalyImage(imgUrl,holder.img,ImgLoadUtil.DF_MEIZI);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartUtil.startWebActivity(context,fuliBean.getUrl());
            }
        });
//        String imgSrc = fuliBean.getUrl().substring(qiBaseUrl.length());
//        DebugUtil.debug(imgSrc);
//        HttpUtil.getInstance().getImgInfo(imgSrc,
//                new Observer<ImgBean>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(ImgBean imgBean) {
//                        float scale=imgBean.getHeight()/imgBean.getWidth();
//                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) holder.img.getLayoutParams();
//                        layoutParams.height= (int) (layoutParams.width*scale);
//                        holder.img.setLayoutParams(layoutParams);
//                        ImgLoadUtil.dispalyImage(imgUrl,holder.img,ImgLoadUtil.DF_MEIZI);
//                    }
//                }
//        );

    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img)
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
