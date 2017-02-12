package com.zcj.mreader.ui.gank.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zcj.mreader.R;
import com.zcj.mreader.adapter.AndroidAdapter;
import com.zcj.mreader.base.BaseFragment;
import com.zcj.mreader.bean.AndroidBean;
import com.zcj.mreader.utils.HttpUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;


public class AndroidFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rootView)
    FrameLayout rootView;

    private ArrayList<AndroidBean> dataList;
    private AndroidAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_android, container, false);
        ButterKnife.bind(this,view);
        if (dataList==null){
            dataList=new ArrayList<>();
        }
        if (adapter==null){
            adapter=new AndroidAdapter(dataList);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared=true;
        initView(rootView);

    }

    @Override
    protected void lazyLoad() {
        showLoadingView(true);
        HttpUtil.getInstance().getAndroidData(10, 1,
                new Observer<AndroidBean>() {
                    @Override
                    public void onCompleted() {
                        adapter.notifyDataSetChanged();
                        showLoadingView(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AndroidBean androidBean) {
                        dataList.add(androidBean);
                    }
                });

    }
}
