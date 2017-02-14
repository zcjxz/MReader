package com.zcj.mreader.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zcj.mreader.R;
import com.zcj.mreader.bean.AndroidBean;

import com.zcj.mreader.utils.ImgLoadUtil;
import com.zcj.mreader.utils.TimeUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.ViewHolder>{
    private ArrayList<AndroidBean> dataList;
    private Context context;
    private final int minImgSize=300;
    private final String reduceFormat="?imageView2/0/h/";
    public AndroidAdapter(ArrayList<AndroidBean> dataList){
        this.dataList=dataList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_android, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AndroidBean bean=dataList.get(position);
        holder.title.setText(bean.getDesc());
        holder.author.setText(bean.getWho());
        holder.time.setText(TimeUtil.getINSTANCE().getTime(bean.getCreatedAt()));
        if (bean.getImages()==null||bean.getImages().size()==0){
//            holder.img.setLayoutParams(new RelativeLayout.LayoutParams(0, RelativeLayout.LayoutParams.MATCH_PARENT));
            holder.img.setVisibility(View.GONE);
        }else{
//            holder.img.setLayoutParams(new RelativeLayout.LayoutParams(
//                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT
//            ));
            holder.img.setVisibility(View.VISIBLE);
            String imgUrl = bean.getImages().get(0) + reduceFormat + minImgSize;
            ImgLoadUtil.dispalyImage(imgUrl,holder.img);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.img)
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
