package com.zcj.mreader.bean.gankBean;


public class ImgBean {

    /**
     * format : jpeg
     * width : 750
     * height : 750
     * colorModel : ycbcr
     */

    private String format;
    private int width;
    private int height;
    private String colorModel;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColorModel() {
        return colorModel;
    }

    public void setColorModel(String colorModel) {
        this.colorModel = colorModel;
    }
}
