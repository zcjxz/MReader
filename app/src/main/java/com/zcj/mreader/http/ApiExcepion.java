package com.zcj.mreader.http;

public class ApiExcepion extends Exception{
    //服务器异常
    private static final int SERVER_ERROR = 1;

    public ApiExcepion(int code){
        this(getApiException(code));
    }

    public ApiExcepion(String message) {
        super(message);
    }

    private static String getApiException(int code){
        String message="";
        switch (code){
            case SERVER_ERROR:
                message="服务器异常";
                break;
        }
        return message;
    }
}
