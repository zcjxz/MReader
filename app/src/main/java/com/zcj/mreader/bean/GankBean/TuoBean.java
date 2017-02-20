package com.zcj.mreader.bean.gankBean;

import com.zcj.mreader.bean.TypeFactory;
import com.zcj.mreader.bean.Visitable;

import java.util.List;

//拓展资源
public class TuoBean implements Visitable{


    /**
     * _id : 58a641e1421aa9662f429735
     * createdAt : 2017-02-17T08:20:49.318Z
     * desc : 欧洲卡车2 游戏的自动驾驶方案，厉害了。
     * images : ["http://img.gank.io/ea2cb677-b8b7-4b5e-90a3-34de72da1f3d","http://img.gank.io/c1302824-d556-452b-8076-5d6b9f61c669"]
     * publishedAt : 2017-02-17T11:31:19.996Z
     * source : chrome
     * type : 拓展资源
     * url : https://github.com/bethesirius/ChosunTruck
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

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }

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
}
