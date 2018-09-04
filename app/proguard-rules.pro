# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile
#优化  不优化输入的类文件
#表示不进行优化，建议使用此选项，因为根据proguard-android-optimize.txt中的描述，
#优化可能会造成一些潜在风险，不能保证在所有版本的Dalvik上都正常运行。
-dontoptimize
#指定代码的压缩级别
-optimizationpasses 5
# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#包名大小写不混淆
-dontusemixedcaseclassnames
#第三方jar不混淆
-dontskipnonpubliclibraryclasses
#不做预校验
#-dontpreverify
#混淆时是否记录日志
-verbose
-dontwarn

#保护注解 ｛
-keepattributes Exceptions, Signature, InnerClasses,LineNumberTable
-keepattributes *Annotation*
#保护注解 ｝

#############################################
#
# Android开发中一些需要保留的公共部分
#
#############################################

# 保留我们使用的四大组件，自定义的Application等等这些类不被混淆
# 因为这些子类都有可能被外部调用
-keep public class * extends android.android.support.v4.app.Activity
-keep public class * extends android.android.support.v4.app.Appliction
-keep public class * extends android.android.support.v4.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.android.support.v4.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class * extends android.view.ViewGroup
-keep public class com.android.vending.licensing.ILicensingService

#adapter也不能混淆
-keep public class * extends android.widget.AdapterView {*;}
# 保留support下的所有类及其内部类
-keep class android.support.** {*;}
# 保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep class android.support.v7.widget.RecyclerView {*;}
-keep public class * extends android.support.annotation.**
#保留R下面的资源 {
-keepclassmembers class **.R$* {
    public static <fields>;
}
-keep class **.R$* {
    *;
}
#保留R下面的资源 }

#自定义控件类 {
#保留本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.android.support.v4.app.Activity {
   public void *(android.view.View);
}
#自定义控件类 }


#枚举变量 ｛
-keepclassmembers enum  * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#枚举变量 ｝


# Parcelable {
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
# Parcelable }


#Serializable {
-keepnames class * implements java.io.Serializable{*;}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#Serializable }


#网络 ｛
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keep public class android.net.http.SslError
-keep public class android.webkit.WebViewClient

-dontwarn android.webkit.WebView
-dontwarn android.net.http.SslError
-dontwarn android.webkit.WebViewClient
#网络 ｝

#############################################
#
# 第三方通用库使用
#
#############################################

# Okio {
-dontwarn com.squareup.**
-dontwarn okio.**
-keep public class org.codehaus.* { *; }
-keep public class java.nio.* { *; }
# Okio }


# OkHttp3 {
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**
# OkHttp3 }


# Retrofit {
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
# Retrolambda
-dontwarn java.lang.invoke.*
# Retrofit }


# Gson {
-keep class com.google.gson.** { *; }
-keepattributes EnclosingMethod
# Gson }


#Glide  {
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
#Glide  }

#EventBus {
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}
#EventBus }


#RXJave {
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
#RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
# RxPermissions
-keep class com.tbruyelle.rxpermissions2.** { *; }
-keep interface com.tbruyelle.rxpermissions2.** { *; }
#RXJave }



#############################################
#
# 第三方库使用
#
#############################################

#数据类 {
-keep class cn.yui.mydiaryapp.data.**{*;}
#数据类 }