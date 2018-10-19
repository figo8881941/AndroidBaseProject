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
-keep class android.support.7.** {*; }

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
-keepresourcexmlelements manifest/application/meta-data@value=GlideModule