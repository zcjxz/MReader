package com.zcj.mreader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zcj.mreader.R;
import com.zcj.mreader.bean.FuliBean;
import com.zcj.mreader.utils.ImgLoadUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FuliAdapter extends RecyclerView.Adapter<FuliAdapter.ViewHolder>{
    private ArrayList<FuliBean> dataList;
    private Context context;
    private final String reduceFormat="?imageView2/0/w/500";
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        FuliBean fuliBean=dataList.get(position);
        String imgUrl=fuliBean.getUrl()+reduceFormat;
        ImgLoadUtil.dispalyImage(imgUrl,holder.img,ImgLoadUtil.DF_MEIZI);
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
