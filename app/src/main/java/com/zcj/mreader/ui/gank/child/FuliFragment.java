package com.zcj.mreader.ui.gank.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zcj.mreader.R;
import com.zcj.mreader.adapter.FuliAdapter;
import com.zcj.mreader.base.BaseFragment;
import com.zcj.mreader.bean.gankBean.FuliBean;
import com.zcj.mreader.utils.HttpUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;


public class FuliFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rootView)
    FrameLayout rootView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<FuliBean> dataList;
    private FuliAdapter adapter;
    private int num=10;
    private int page=1;
    private boolean isLoading=false;
    private StaggeredGridLayoutManager layoutManager;
    private int row=2;

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
        if (layoutManager==null){
            layoutManager = new StaggeredGridLayoutManager(
                    row, StaggeredGridLayoutManager.VERTICAL
            );
            //防止瀑布流发生位置变化
            layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //防止第一行留空白
                layoutManager.invalidateSpanAssignments();
                //判断是否滑动到底
                if (!recyclerView.canScrollVertically(1)) {
                    //判断是否在加载状态
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
     * 连接服务器，获取数据
     * @param isLast 用于判断是否在数据末尾追加数据
     */
    private void setData(final Boolean isLast){
        isLoading=true;
        HttpUtil.getInstance().getFuliData(num, page,
                new Observer<FuliBean>() {
                    @Override
                    public void onCompleted() {
                        adapter.notifyDataSetChanged();
                        showLoadingView(false);
                        swipeRefreshLayout.setRefreshing(false);
                        page++;
                        isLoading=false;
                    }

                    @Override
                    public void onError(Throwable e) {
                        showLoadingView(false);
                        isLoading=false;
                        showErrorView(true);
                    }

                    @Override
                    public void onNext(FuliBean fuliBean) {
                        if (isLast){
                            dataList.add(fuliBean);
                        }else{
                            dataList.add(0,fuliBean);
                        }
                    }
                }
        );
    }
    @Override
    protected void lazyLoad() {
        if (dataList.size()==0){
            showLoadingView(true);
            setData(false);
        }
    }

}
