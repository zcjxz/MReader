package com.zcj.mreader.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zcj.mreader.Const;
import com.zcj.mreader.R;
import com.zcj.mreader.bean.gankBean.FuliBean;
import com.zcj.mreader.ui.gank.ShowMeiziActivity;
import com.zcj.mreader.utils.DebugUtil;
import com.zcj.mreader.utils.DensityUtils;
import com.zcj.mreader.utils.ImgLoadUtil;
import com.zcj.mreader.utils.StartUtil;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.greenrobot.eventbus.EventBus.TAG;

public class FuliAdapter extends RecyclerView.Adapter<FuliAdapter.ViewHolder>{
    private ArrayList<FuliBean> dataList;
    private Context context;
    private final String TAG = "fuliAdapter";
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
        String imgUrl = Const.FilePath.imgCache + File.separator + fuliBean.get_id() + ".png";
        File file = new File(imgUrl);
        if (!file.exists()){
            imgUrl=fuliBean.getUrl();
            DebugUtil.debug("图片 "+position+"  从网络获取图片");
        }else{
            DebugUtil.debug("图片 "+position+"  从本地获取图片");
            DebugUtil.debug("路径： "+imgUrl);
        }
        if (fuliBean.getWidth()!=0&&fuliBean.getHeight()!=0){
            float scale = (float)fuliBean.getHeight() / fuliBean.getWidth();
            int width = (DensityUtils.getScreenW(context)-DensityUtils.dp2px(context,20))/2;
            int height = (int) (width*scale);
            holder.img.setLayoutParams(new FrameLayout.LayoutParams(width,height));
        }
        ImgLoadUtil.displayImage(imgUrl, holder.img, ImgLoadUtil.DF_MEIZI);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowMeiziActivity.class);
                intent.putExtra("url",fuliBean.getUrl());
                context.startActivity(intent);
            }
        });
//        String imgSrc = fuliBean.getUrl().substring(qiBaseUrl.length());
//        DebugUtil.debug(imgSrc);
//        ApiRequest.getInstance().getImgInfo(imgSrc,
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
//                        ImgLoadUtil.displayImage(imgUrl,holder.img,ImgLoadUtil.DF_MEIZI);
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
