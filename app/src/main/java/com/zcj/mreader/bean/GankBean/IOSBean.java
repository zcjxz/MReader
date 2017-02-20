package com.zcj.mreader.bean.gankBean;


import com.zcj.mreader.bean.TypeFactory;
import com.zcj.mreader.bean.Visitable;

import java.util.List;

public class IOSBean implements Visitable{

    /**
     * _id : 58a27783421aa901f7902c7c
     * createdAt : 2017-02-14T11:20:35.561Z
     * desc : 给 UICollectionView 加上各种漂亮的过渡效果。
     * images : ["http://img.gank.io/5fccc6dd-091d-4562-90ee-431015865cba","http://img.gank.io/efe9f74c-0fe1-4c99-a201-6bbb5f312b67","http://img.gank.io/caa08430-8d9e-477b-b726-19fa19d7dbbf"]
     * publishedAt : 2017-02-14T11:42:37.303Z
     * source : chrome
     * type : iOS
     * url : https://github.com/KelvinJin/AnimatedCollectionViewLayout
     * used : true
     * who : 代码家
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
