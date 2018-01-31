# APP框架说明

---

## 前言

从零开始搭建的项目，所以比较用心，各种工具类也用心的挑选和封装，后面正式写项目，也会注意多些备注和说明，提高代码的可读性。

## 准备工作

- [x] 网络请求
- [x] 图片加载
- [x] RxJava2/RxJava生命周期管理
- [x] Log日志管理
- [x] 图片选择/拍照
- [x] 常规UI/浸入式
- [x] 权限管理
- [x] View注解
- [x] mvc封装
- [x] mvp封装
- [x] QR Qode工具
- [x] Base类封装
- [x] 数据库

## 内容介绍

### 1. 网络请求

网络请求封装的[Retrofit2+RxJava2][1]，具体封装备注在blog中已经很清楚，就不再详说。

#### 1.1 使用方法

1、 在API.class中添加请求接口
```
    @FormUrlEncoded
    @POST("api/login.json")
    Observable<BasicResponse<LoginModel>> login(@Field("loginName") String name,
                                                @Field("password") String psd,
                                                @Field("deviceId") String deviceId,
                                                @Field("registrationId") String registerId);
```
2、 需要访问的地方执行方法
```
 NetApply.getAPI()
                .login("xuke352", "zydh123", "123456", "meiyouregisterid")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BasicResponse<LoginModel>>(getActivity()) {
                    @Override
                    public void onSuccess(BasicResponse<LoginModel> response) {
                        String name = response.getData().getLoginName();
                        LogUtils.e(name);
                    }
                });
```

#### 1.2 注意：
**将网络请求放置在子线程（工作线程`.subscribeOn(Schedulers.newThread())`），内容返回如果涉及到UI操作，一定要将线程改为主线程（UI线程`.observeOn(AndroidSchedulers.mainThread())`）**

### 2. 图片加载

图片加载选择[Glide][2]框架，非常强大的图片加载库，在项目中，有对这个框架进行简单的封装`ImageLoader`，使用方法不做另外阐述。

### 3. RxJava2/RxJava生命周期管理

RxJava2 目前火热程度依然没有降温，以我浅薄的知识实在没法介绍什么，只能做简单的使用：
```
    /**
     * 计时功能
     *
     * @param count 计时秒数
     */
    public static Observable<Long> countDown(int count) {
        return Observable.interval(1000, TimeUnit.MILLISECONDS)
                .take(count)
                .subscribeOn(AndroidSchedulers.mainThread());
    }

```

### 4. Log日志管理
使用[Logger][3]框架，在MyApplication中已经进行初始化。
```
    /**
     * Logger 初始化
     */
    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(1)         // (Optional) How many method line to show. Default 2
                .tag("martin tag")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    //使用
    Logger.e("message ");
```

### 5. 图片选择/拍照

[Matisse][4]，知乎开源的一款美观好用的图片选择库，依据它的源码，提取出拍照功能，封装在`PhotoUtil`。
后续有时间，会写一篇惯有Matisse的配置与使用教程。

```
    //选择照片
    PhotoUtil.pickPhoto(getActivity(), REQUEST_PHOTO_PICK, 1, false);

    //只拍照
    mediaStoreCompat = PhotoUtil.takePhoto(getActivity(), REQUEST_PHOTO_TAKE);

/*
*  选择/拍照 返回内容获取
*/
@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PHOTO_PICK: // 选择图片数据获取
                    List<Uri> uris = Matisse.obtainResult(data);
                    break;
                case REQUEST_PHOTO_TAKE:// 拍照数据获取
                    Uri uri = mediaStoreCompat.getCurrentPhotoPath();
                    break;
            }
        }
    }

```

### 6. 常规UI/浸入式

[QMUI][5] 很强大，集成了很多的功能。
在项目中，目前只讲QMUI的TipDialog进行的简单封装:`TipDialogUtil`，使用方法不进行阐述。

### 7. 权限管理

[rxpermissions][6]

```
new RxPermissions(getActivity())
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe();
```

### 8. View注解

[ButterKnife][7]

虽然注解中的反射机制有损程序的性能，但是思来想去还是讲注解加入进来，因为前期开发日期太短，大量的手动代码也会带来一些问题。

#### 8.1 点击Activity或Fragment中的布局资源Id，选择Generate

 ![ButterKnife第一步][8]

#### 8.2 点击Generate ButterKnife Injections（最后一个）

![ButterKnife第二步][9]

#### 8.3 勾选需要注解的View，也可以勾选实现点击事件监听
![ButterKnife第三步][10]

### 9. mvc/mvp封装

#### 9.1 MVC的封装，只是简单的Base类封装。

#### 9.2 MVP的封装:

1. 需要Activity与Fragment来继承BaseMVPxxxxx
2. 创建xxxView、xxxPresenter两个组合类。
3. 在Activity/Fragment的上面使用注解`@CreatePresenter(xxxPresenter.class)`

### 10. QR Qode工具
zxing

#### 10.1 QRCodeUtil
    根据字符串，生成二维码图片
    
#### 10.2 ScanManager
    扫描二维码功能封装
    
**注意：需要在生命周期里面添加控制，即时释放内存**


### 11. 数据库

[GreenDao][11]

GreenDao的配置方法，以及安全升级注意[在这里][12]




  [1]: http://blog.csdn.net/qq_20521573
  [2]: https://github.com/bumptech/glide
  [3]: https://github.com/orhanobut/logger
  [4]: https://github.com/zhihu/Matisse
  [5]: http://qmuiteam.com/android/page/index.html
  [6]: https://github.com/tbruyelle/RxPermissions
  [7]: https://github.com/JakeWharton/butterknife
  [8]: http://upload-images.jianshu.io/upload_images/4025850-82ac48cb62dc8673.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240
  [9]: http://upload-images.jianshu.io/upload_images/4025850-c2ec1c6e674eee47.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240
  [10]: http://upload-images.jianshu.io/upload_images/4025850-d327baddf20f6f3d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240
  [11]: https://github.com/greenrobot/greenDAO
  [12]: https://www.zybuluo.com/martin0207/note/1029588
