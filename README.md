#一、加密算法

##对称加密

又称私钥加密

##非对称加密


##单向加密

#二、开源项目

## BottomBarTab

###1、使用
实例化BottomBar
```
MainActivity.java
mBottomBar = BottomBar.attach(this, savedInstanceState);
```


底部栏：

```
bottombar_menu.xml

<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/tab_home"
        android:icon="@drawable/bottom01"
        android:title="@string/tab_home_title" />

    <item
        android:id="@+id/tab_invest"
        android:icon="@drawable/bottom03"
        android:title="@string/tab_invest_title" />

    <item
        android:id="@+id/tab_me"
        android:icon="@drawable/bottom05"
        android:title="@string/tab_me_title" />

    <item
        android:id="@+id/tab_more"
        android:icon="@drawable/bottom07"
        android:title="@string/tab_more_title" />
</menu>
```


#三、沉浸式状态栏


```
android:fitsSystemWindows:主要是通过调整当前设置这个属性的view的padding去为我们的status_bar留下空间。
```

让Activity变成translucen模式:
方法一、
```
  <item name="android:windowTranslucentStatus">true</item>
  <item name="android:windowTranslucentNavigation">true</item>
```

方法二、继承*.TranslucentDecor 主题中的一种。

方法三、在activity中设置FLAG_TRANSLUCENT_NAVIGATION 和 FLAG_TRANSLUCENT_STATUS。是方法一的java代码。


##SystemBarTint的使用

```
compile 'com.readystatesoftware.systembartint:systembartint:1.0.3'
```

###第一步，在顶层布局加入下面两行代码

```
android:fitsSystemWindows="true"
android:clipToPadding="false"
```

上面的第一行代码fitsSystemWindows的作用是： 
让view可以根据系统窗口(如status bar)来调整自己的布局，如果值为true,就会调整view的paingding属性来给system windows留出空间。

第二行代码clipToPadding 值控件的绘制区域是否在padding里面的，默认是true，设置成false就表示可已在控件外面显示。

###第二部、在java中初始化

```
 // 4.4及以上版本开启=====>在onCreate()方法
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    setTranslucentStatus(true);
}

SystemBarTintManager tintManager = new SystemBarTintManager(this);
tintManager.setStatusBarTintEnabled(true);
tintManager.setNavigationBarTintEnabled(true);

// 自定义颜色
tintManager.setTintColor(Color.parseColor("#24b7a4"));


 @TargetApi(19)
private void setTranslucentStatus(boolean on) {
    Window win = getWindow();
    WindowManager.LayoutParams winParams = win.getAttributes();
    final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
    if (on) {
        winParams.flags |= bits;
    } else {
        winParams.flags &= ~bits;
    }
    win.setAttributes(winParams);
}
````

