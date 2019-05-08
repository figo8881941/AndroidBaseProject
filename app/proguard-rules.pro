# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/chenjingmian/Documents/soft/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#保留debug信息
-renamesourcefileattribute SourceFile

-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable

# 指定代码的压缩级别0-7
-optimizationpasses 7

-dontpreverify

-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

# 保持这些类不被混淆
-keep public class * extends android.view
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.pm
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference

-dontwarn com.google.**
-keep class android.security {*; }

# 保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# 不混淆注解
-keepattributes *Annotation*
-keep class * extends java.lang.annotation.Annotation {*;}

# 保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# 保持 Serializable 不被混淆
-keep class * implements java.io.Serializable {*; }


# v4混淆配置
-dontwarn android.support.v4.**
-keep class android.support.4.** {*; }

# v7混淆配置
-dontwarn android.support.v7.**
-keep class android.support.v7.** {*; }

# AndPermission
-dontwarn com.yanzhenjie.permission.**

# EventBus
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# for DexGuard only
# -keepresourcexmlelements manifest/application/meta-data@value=GlideModule

# banner 的混淆代码
-keep class com.youth.banner.** {
    *;
}

# droidsonroids.gif
-keep public class pl.droidsonroids.gif.GifIOException{<init>(int);}
-keep class pl.droidsonroids.gif.GifInfoHandle{<init>(long,int,int,int);}

# ARouter
-dontwarn com.alibaba.android.arouter.**
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}
# If you use the byType method to obtain Service, add the following rules to protect the interface:
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider
# If single-type injection is used, that is, no interface is defined to implement IProvider, the following rules need to be added to protect the implementation
# -keep class * implements com.alibaba.android.arouter.facade.template.IProvider

# Weex
-keep class com.taobao.weex.** { *; }
-keep class com.taobao.weex.bridge.** { *; }
-keep class com.taobao.weex.layout.** { *; }
-keep class com.taobao.weex.WXSDKEngine { *; }
-keep class com.taobao.weex.base.SystemMessageHandler { *; }
-dontwarn com.taobao.weex.bridge.**


# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod
# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**
# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit
# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# okhttp
-dontwarn okhttp3.**
-keep class okhttp3.** { *;}
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

# fastjson
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }
-keepattributes Signature

# KeepNotProguard注解
-keep @com.duoduo.commonbase.proguard.annotation.KeepNotProguard class * {*;}
-keep class * {
    @com.duoduo.commonbase.proguard.annotation.KeepNotProguard <fields>;
}
-keepclassmembers class * {
    @com.duoduo.commonbase.proguard.annotation.KeepNotProguard <methods>;
}

#Keep AnnotationPermission
-keep @com.duoduo.annotationpermission.library.annotation.AnnotationPermission class * {*;}
-keepclassmembers class * {
    @com.duoduo.annotationpermission.library.annotation.NeedPermission <methods>;
    @com.duoduo.annotationpermission.library.annotation.OnGrantedPermission <methods>;
    @com.duoduo.annotationpermission.library.annotation.OnDeniedPermission <methods>;
    @com.duoduo.annotationpermission.library.annotation.OnAlwaysDeniedPermission <methods>;
    @com.duoduo.annotationpermission.library.annotation.OnShowRationable <methods>;
}
