package com.zcj.mreader.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


public class DebugUtil {
    public static final String TAG = "zcj";
    public static final boolean DEBUG = true;

    public static void toast(Context context, String content) {
        if (DEBUG){
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
        }
    }

    public static void debug(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void debug(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void error(String tag, String error) {

        if (DEBUG) {
            Log.e(tag, error);
        }
    }

    public static void error(String error) {

        if (DEBUG) {
            Log.e(TAG, error);
        }
    }
}

