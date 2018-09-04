package cn.yui.mydiaryapp.third.permission;


/**
 * 权限授予异步回调，用于RxPermission异步回调
 *
 * @param <T>
 */
public interface PermissionGrantCallback<T> {
    /**
     * 权限授予状态
     *
     * @param granted
     */
    void onPermissionGrant(boolean granted);
}