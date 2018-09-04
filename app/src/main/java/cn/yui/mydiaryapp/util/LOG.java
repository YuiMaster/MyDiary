package cn.yui.mydiaryapp.util;

import android.util.Log;

import cn.yui.mydiaryapp.BuildConfig;


/**
 * @author liaoyuhuan
 * @name ${PROJECT_NAME}
 * @class
 * @time 2018/4/11  11:00
 * @description
 */
public class LOG {
    static final String TAG = "hd/";
    /**
     * false 调试
     * true 不调试
     */
    static final boolean disdebug = !BuildConfig.DEBUG;

    public static int i(String tag, String data) {
        if (disdebug) {
            return 0;
        }

        return Log.i(TAG + tag, data);
    }

    public static int d(String tag, String data) {
        if (disdebug) {
            return 0;
        }
        return Log.d(TAG + tag, data);
    }

    public static int w(String tag, String data) {
        if (disdebug) {
            return 0;
        }
        return Log.w(TAG + tag, data);
    }

    public static int e(String tag, String data) {
        if (disdebug) {
            return 0;
        }
        return Log.e(TAG + tag, data);
    }
}
