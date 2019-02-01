package com.zcj.mreader.ui.gank.child;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zcj.mreader.Const;
import com.zcj.mreader.R;
import com.zcj.mreader.adapter.FuliAdapter;
import com.zcj.mreader.base.BaseFragment;
import com.zcj.mreader.base.BaseGankBean;
import com.zcj.mreader.bean.gankBean.FuliBean;
import com.zcj.mreader.http.ApiRequest;
import com.zcj.mreader.utils.ImgLoadUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class FuliFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rootView)
    FrameLayout rootView;

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
            //防止瀑布流发生位置变化，但是会导致很多空白
//            layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // 刷新布局，重新计算布局
//                layoutManager.invalidateSpanAssignments();
                //判断是否滑动到底
                if (!recyclerView.canScrollVertically(1)) {
                    //判断是否在加载状态
                    if (!isLoading){
                        showLoadingView(true);
                        setData();
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
    }

    int lastSize =0;
    /**
     * 连接服务器，获取数据
     */
    private void setData(){
        isLoading=true;
        //请求数据
        ApiRequest.getServer().getFuliData(num, page)
                .compose(ApiRequest.<BaseGankBean<FuliBean>>preRequest())
                .map(new Function<BaseGankBean<FuliBean>, List<FuliBean>>() {
                    @Override
                    public List<FuliBean> apply(BaseGankBean<FuliBean> fuliBeanBaseGankBean) throws Exception {
                        return fuliBeanBaseGankBean.getResults();
                    }
                })
                .flatMap(new Function<List<FuliBean>, ObservableSource<FuliBean>>() {
                    @Override
                    public ObservableSource<FuliBean> apply(List<FuliBean> fuliBeans) throws Exception {
                        return Observable.fromIterable(fuliBeans);
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<FuliBean, FuliBean>() {
                    @Override
                    public FuliBean apply(FuliBean fuliBean) throws Exception {
                        String url = fuliBean.getUrl();
                        if (url!=null) {
                            try {
                                // 同步下载图片，可设置获取到的 bitmap 大小，但是实际下载的还是原图
                                Bitmap bitmap = Glide.with(getActivity())
                                        .asBitmap()
                                        .load(url)
                                        .into(400, 400)
                                        .get();
                                ImgLoadUtil.wirteBitmap2File(bitmap, Const.FilePath.imgCache,fuliBean.get_id()+".png");
                                int height = bitmap.getHeight();
                                int width = bitmap.getWidth();
                                fuliBean.setHeight(height);
                                fuliBean.setWidth(width);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                        }
                        return fuliBean;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FuliBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        showLoadingView(false);
                        showErrorView(false);
                    }

                    @Override
                    public void onNext(FuliBean fuliBean) {
                        dataList.add(fuliBean);
                        int index = dataList.indexOf(fuliBean);
                        adapter.notifyItemChanged(index);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showLoadingView(false);
                        isLoading=false;
                        showErrorView(true);
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        lastSize = dataList.size();
                        page++;
                        isLoading=false;
                    }
                });
    }
    @Override
    protected void lazyLoad() {
        if (dataList.size()==0){
            showLoadingView(true);
            setData();
        }
    }

}
