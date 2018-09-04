package cn.yui.mydiaryapp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.githang.statusbar.StatusBarCompat;

import cn.yui.mydiaryapp.NewApplication;
import cn.yui.mydiaryapp.R;
import cn.yui.mydiaryapp.third.leaks.HuaweiLeak;
import cn.yui.mydiaryapp.third.leaks.imm.IMMLeaks;


/**
 * @author liaoyuhuan
 * @name BaseActivity
 * @time 2018/9/4 0004
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor();
    }


    /**
     * 设置状态栏颜色
     */
    protected void setStatusBarColor() {
        final int mainThemeColor = ContextCompat.getColor(this, R.color.color_main_theme);
        StatusBarCompat.setStatusBarColor(this, mainThemeColor);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        HuaweiLeak.fixHuaWeiMemoryLeak();
        IMMLeaks.fixFocusedViewLeak(getApplication());
        super.onDestroy();
        /** 监控内存泄漏 */
        NewApplication.getRefWatcher(this).watch(this);
    }
}
