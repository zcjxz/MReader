package com.zcj.mreader.ui.gank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zcj.mreader.R;
import com.zcj.mreader.utils.ImgLoadUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowMeiziActivity extends AppCompatActivity {

    @BindView(R.id.img)
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_meizi);
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra("url");
    }
}
