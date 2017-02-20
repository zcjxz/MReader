package com.zcj.mreader.bean.gankBean;


import com.zcj.mreader.bean.TypeFactory;
import com.zcj.mreader.bean.Visitable;

import java.util.List;

public class QianBean implements Visitable{

    /**
     * _id : 58a1a996421aa901ef40579b
     * createdAt : 2017-02-13T20:41:58.443Z
     * desc : 三分钟创建一个简单精致的 About Me 页面
     * images : ["http://img.gank.io/73917ab1-84f1-4f22-a2c0-9ddf93856245"]
     * publishedAt : 2017-02-14T11:42:37.303Z
     * source : web
     * type : 前端
     * url : http://blog.xcatliu.com/2017/02/13/about_me/
     * used : true
     * who : xcatliu
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
