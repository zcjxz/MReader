package com.zcj.mreader.bean.gankBean;

import com.zcj.mreader.bean.TypeFactory;
import com.zcj.mreader.bean.Visitable;

//瞎推荐
public class XiuBean implements Visitable{
    /**
     * _id : 58a1aae3421aa901f56d3689
     * createdAt : 2017-02-13T20:47:31.959Z
     * desc : 旅行的意义是什么？
     * publishedAt : 2017-02-14T11:42:37.303Z
     * source : chrome
     * type : 休息视频
     * url : http://www.miaopai.com/show/day~eckkasQUW7~0u6O~fg__.htm
     * used : true
     * who : lxxself
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

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
