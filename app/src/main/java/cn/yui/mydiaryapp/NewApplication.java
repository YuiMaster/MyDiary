package cn.yui.mydiaryapp;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;


/**
 * @author liaoyuhuan
 * @name NewApplication
 * @time 2018/9/4 0004
 */

public class NewApplication extends Application {
    private static NewApplication instance;

    private RefWatcher refWatcher;

    public static NewApplication getInstance() {
        return instance;
    }

    /**
     * 监控内存
     *
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(final Context context) {
        final NewApplication application = (NewApplication) context.getApplicationContext();
        return application.refWatcher;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        /** LeakCanary */
        refWatcher = LeakCanary.install(this);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
