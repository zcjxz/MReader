package com.zcj.mreader.event;

/**
 * Created by ZCJ on 2019/1/30.
 */
public class UpdateImgSize {
    public int position;
    public int width;
    public int height;

    public UpdateImgSize(int position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }
}
