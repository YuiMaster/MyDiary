package cn.yui.mydiaryapp.third;

import android.app.Activity;

import java.util.Stack;


/**
 * 退出app：容器式
 *
 * @author liaoyuhuan
 * @name ActivityManager
 * @time 2018/9/4 0004
 */

public class ActivityManager {
    private Stack<Activity> activityStack;
    private static ActivityManager instance;

    private ActivityManager() {

    }

    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     *
     */
    public void finishActivity() {
        final Activity activity = activityStack.lastElement();
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    public void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
    }

    /**
     * 退出应用程序
     */
    public void exitApp() {
        try {
            finishAllActivity();
            //根据进程ID，杀死该进程
            android.os.Process.killProcess(android.os.Process.myPid());
            //退出真个应用程序
            System.exit(0);
        } catch (Exception e) {
        }
    }

}
