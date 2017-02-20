package com.zcj.mreader.bean.gankBean;


import com.zcj.mreader.bean.TypeFactory;
import com.zcj.mreader.bean.Visitable;

import java.util.List;

public class XiaBean implements Visitable{

    /**
     * _id : 58a1be65421aa901ef40579c
     * createdAt : 2017-02-13T22:10:45.641Z
     * desc : 为什么未读数要显示 99+
     * images : ["http://img.gank.io/dc57e549-894d-4203-9fab-69adf38d6de6"]
     * publishedAt : 2017-02-14T11:42:37.303Z
     * source : chrome
     * type : 瞎推荐
     * url : https://iammapping.com/why-use-99plus/
     * used : true
     * who : iammapping
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
