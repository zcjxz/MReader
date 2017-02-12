package com.zcj.mreader.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    private static String PAT_SERVICE="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static String PAT_LOCAL="MM-dd";
//    private static String PAT_LOCAL="yyyy年MM月dd日HH时mm分ss秒SSS毫秒";
    private static SimpleDateFormat sdf_s=null;
    private static SimpleDateFormat sdf_l=null;
    private static TimeUtil INSTANCE;
    private TimeUtil(){
    }
    public static TimeUtil getINSTANCE(){
        if (INSTANCE==null){
            INSTANCE=new TimeUtil();
        }
        return INSTANCE;
    }
    public static String getTime(String serviceTime){
        if (sdf_s==null){
            sdf_s=new SimpleDateFormat(PAT_SERVICE);
        }
        if (sdf_l==null){
            sdf_l=new SimpleDateFormat(PAT_LOCAL);
        }
        Date date=null;
        try {
            date=sdf_s.parse(serviceTime);
            return sdf_l.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "时间格式化失败";
    }
}
