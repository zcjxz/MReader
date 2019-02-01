package com.zcj.mreader;

import android.os.Environment;

import java.io.File;

/**
 * Created by ZCJ on 2019/1/30.
 */
public class Const {
    public static class FilePath{
        public static String basePath = App.getInstance().getExternalCacheDir().getAbsolutePath();
        public static String imgCache = basePath + File.separator + "imgCache";
    }
}
