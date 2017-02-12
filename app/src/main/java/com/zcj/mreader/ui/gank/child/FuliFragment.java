package com.zcj.mreader.ui.gank.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zcj.mreader.R;
import com.zcj.mreader.adapter.FuliAdapter;
import com.zcj.mreader.base.BaseFragment;
import com.zcj.mreader.bean.FuliBean;
import com.zcj.mreader.utils.HttpUtil;
import com.zcj.mreader.utils.TimeUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;


public class FuliFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rootView)
    FrameLayout rootView;
    private ArrayList<FuliBean> dataList;
    private FuliAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fuli, container, false);
        ButterKnife.bind(this,view);
        if (dataList==null){
            dataList=new ArrayList<>();
        }
        if (adapter==null){
            adapter=new FuliAdapter(dataList);
        }
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                2,  StaggeredGridLayoutManager.VERTICAL
        ));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared=true;
        initView(rootView);
    }
    private void setData(){
        showLoadingView(true);
        HttpUtil.getInstance().getFuliData(10, 1,
                new Observer<FuliBean>() {
                    @Override
                    public void onCompleted() {
                        adapter.notifyDataSetChanged();
                        showLoadingView(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FuliBean fuliBean) {
                        dataList.add(fuliBean);
                    }
                }
        );
    }
    @Override
    protected void lazyLoad() {
        if (dataList.size()==0){
            setData();
        }
    }
}
