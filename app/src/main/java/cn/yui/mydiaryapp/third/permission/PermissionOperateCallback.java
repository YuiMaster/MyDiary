package cn.yui.mydiaryapp.third.permission;


/**
 * 权限授予操作回调，用于RxPermission异步回调
 *
 * @param <T>
 */
public interface PermissionOperateCallback<T> {

    /**
     * 权限授予操作结果
     *
     * @param result
     */
    void onPermissionGrantResult(T result);

    /**
     * 权限操作授予出错
     *
     * @param errorMsg
     */
    void onPermissionGrantError(T errorMsg);
}