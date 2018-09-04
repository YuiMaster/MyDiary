package cn.yui.mydiaryapp.util.view;

import android.content.Context;
import android.widget.Toast;


/**
 * toast 统一调用处，方便统一进行定制
 *
 * @author liaoyuhuan
 * @name ToastUtil
 * @time 2018/8/8 0008
 */

public class ToastUtil {

    /**
     * @param context
     * @param msg
     */
    public static void showToast(final Context context, final String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
