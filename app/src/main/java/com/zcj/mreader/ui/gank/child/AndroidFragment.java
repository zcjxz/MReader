package com.zcj.mreader.ui.gank.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zcj.mreader.R;
import com.zcj.mreader.adapter.AndroidAdapter;
import com.zcj.mreader.base.BaseFragment;
import com.zcj.mreader.base.BaseGankBean;
import com.zcj.mreader.bean.gankBean.AndroidBean;
import com.zcj.mreader.http.ApiManager;
import com.zcj.mreader.http.ApiRequest;
import com.zcj.mreader.utils.DebugUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class AndroidFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rootView)
    FrameLayout rootView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<AndroidBean> dataList;
    private AndroidAdapter adapter;
    private final int num=10;
    private int page=1;
    private boolean isLoading=false;
    private LinearLayoutManager layoutManager;

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
        if (layoutManager==null){
            layoutManager = new LinearLayoutManager(getContext());
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)){
                    if (!isLoading){
                        showLoadingView(true);
                        setData(true);
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared=true;
        initView(rootView);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isLoading){
                    swipeRefreshLayout.setRefreshing(false);
                    return;
                }
                setData(false);
            }
        });
    }

    /**
     * 获取数据
     * @param isLast 判断是否在数据末尾追加数据
     */
    public void setData(final Boolean isLast){
        isLoading=true;
        Disposable subscribe = ApiRequest.getServer().getAndroidData(num, page)
                .compose(ApiRequest.<BaseGankBean<AndroidBean>>preRequest())
                .subscribe(new Consumer<BaseGankBean<AndroidBean>>() {
                    @Override
                    public void accept(BaseGankBean<AndroidBean> data) throws Exception {
                        List<AndroidBean> androidBeanList = data.getResults();
                        DebugUtil.debug(androidBeanList.toString());
                    }
                });
        addObserver(subscribe);
    }

    @Override
    protected void lazyLoad() {
        if (dataList.size()==0){
            showLoadingView(true);
            setData(false);
        }
    }
}
