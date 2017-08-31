package com.zcj.mreader.ui.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zcj.mreader.R;
import com.zcj.mreader.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesFragment extends BaseFragment {
    @BindView(R.id.text)
    TextView text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared=true;
    }

    @Override
    protected void lazyLoad() {
        text.setText(text.getText()+"\n数据加载完成");
    }
}
