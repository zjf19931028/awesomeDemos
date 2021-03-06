package com.awesome.arouterdemo;

import com.alibaba.android.arouter.BuildConfig;
import com.alibaba.android.arouter.launcher.ARouter;
import com.awesome.sdk.BaseApplication;
import com.awesome.sdk.util.ShowLogUtil;

/**
 * Author: JfangZ
 * Email: zhangjingfang@jeejio.com
 * Date: 2021/4/16 16:17
 * Description:
 */
public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
        }
        // ARouter初始化
        ARouter.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ShowLogUtil.info("onTerminate");
        ARouter.getInstance().destroy();
    }
}
