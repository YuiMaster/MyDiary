package cn.yui.mydiaryapp.third.permission;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.UUID;

import cn.yui.mydiaryapp.NewApplication;
import cn.yui.mydiaryapp.util.LOG;
import rx.functions.Action1;

/**
 * 执行权限操作 manager
 * 比如 获取imei号，需要先获取权限。
 *
 * @author liaoyuhuan
 * @name ${PROJECT_NAME}
 * @class
 * @time 2018/4/25  17:08
 * @description
 */
public class PermissionOperateManager {
    private static final String TAG = "PermissionOperateManager";

    private static PermissionOperateManager permissionOperateManager;
    private Context applicationContext;


    private PermissionOperateManager() {
        applicationContext = NewApplication.getInstance();
    }


    public static PermissionOperateManager getInstance() {
        if (permissionOperateManager == null) {
            synchronized (PermissionOperateManager.class) {
                if (permissionOperateManager == null) {
                    permissionOperateManager = new PermissionOperateManager();
                }
            }
        }
        return permissionOperateManager;
    }

    /**
     * 取得设备imei，生成设备唯一码 UUID
     *
     * @param callback
     */
    public void getUniqueId(@NonNull final PermissionOperateCallback callback) {
        requestPermission(
                new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            /** 常规获取 device id 类似：868464035605244 */
                            final String uniQueId;
                            final TelephonyManager tm = (TelephonyManager) applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
                            @SuppressLint("MissingPermission") String imei = tm.getDeviceId();

                            /** 常规获取 Android ID 类似：9774d56d682e549c
                             * 缺点：手机升级时，也可能变化
                             * */
                            if (TextUtils.isEmpty(imei)) {
                                imei = Settings.Secure.getString(applicationContext.getContentResolver(), Settings.Secure.ANDROID_ID);
                            }

                            /** 手机升级时，也可能变化
                             * 国内厂商有个bug，可能都为 9774d56d682e549c
                             * 如果是，则自动生成
                             * */
                            if ("9774d56d682e549c".equals(imei)) {
//                                imei = PROFILE.getRadomUuid();
                            }

                            if (TextUtils.isEmpty(imei)) {
                                imei = UUID.randomUUID().toString();
                                imei = imei.replace("-", "");
//                                PROFILE.setRadomUuid(imei);
                            }

                            final String additive = "com.android.application";
                            LOG.w(TAG, " imei" + imei);

                            if (!TextUtils.isEmpty(imei)) {
                                UUID deviceUuid = new UUID(((long) imei.hashCode() << 32), additive.hashCode());
                                uniQueId = deviceUuid.toString().replace("-", "");
                                callback.onPermissionGrantResult(uniQueId);
                            } else {
                                callback.onPermissionGrantError("获取DEVICE_ID失败!");
                            }
                        } else {
                            callback.onPermissionGrantError("需要读取设备状态的权限！");
                        }
                    }
                }, Manifest.permission.READ_PHONE_STATE);
    }


    /**
     * 请求权限，异步回调
     *
     * @param callback
     * @param permissions
     */
    /**
     * 请求权限，异步回调
     *
     * @param callback
     * @param permissions
     */
    public void requestPermission(@NonNull final PermissionGrantCallback callback, String... permissions) {
        if (permissions == null) {
            callback.onPermissionGrant(true);
            return;
        }

        requestPermission(
                new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        callback.onPermissionGrant(granted);
                    }
                }, permissions);
    }


    private void requestPermission(Action1<Boolean> action, String... permissions) {
        RxPermissions
                .getInstance(applicationContext)
                .request(permissions)
                .subscribe(action);
    }

    /**
     * app退出，释放资源
     */
    public void release() {
        applicationContext = null;
    }
}
