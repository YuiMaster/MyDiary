package cn.yui.mydiaryapp.third.leaks;

import android.os.Build;

import java.lang.reflect.Field;


/**
 * 华为手势功能导致的内存泄漏
 *
 * @author liaoyuhuan
 * @name HuaweiLeak
 * @time 2018/8/22 0022
 */

public class HuaweiLeak {
    public static void fixHuaWeiMemoryLeak() {
        if (!"HUAWEI".equals(Build.MANUFACTURER)) {
            return;
        }
        try {
            Class<?> GestureBoostManagerClass = Class.forName("android.gestureboost.GestureBoostManager");
            Field sGestureBoostManagerField = GestureBoostManagerClass.getDeclaredField("sGestureBoostManager");
            sGestureBoostManagerField.setAccessible(true);
            Object gestureBoostManager = sGestureBoostManagerField.get(GestureBoostManagerClass);
            Field contextField = GestureBoostManagerClass.getDeclaredField("mContext");
            contextField.setAccessible(true);
            if (contextField.get(gestureBoostManager) != null) {
                contextField.set(gestureBoostManager, null);
            }
        } catch (Throwable t) {

        }
    }

}
