package lanou.foodpie.abs;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by ChenFengYao on 2016/10/24.
 * !!!!!!写完 MyApp 一定 一定 一定 要注册!!!!
 *
 */

public class MyApp extends Application {
    //所有跟界面无关的 都可以用这个全局的Context
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        //第一：默认初始化
        Bmob.initialize(this, "e147211defaeeba1e36874ca529491a3");
        ShareSDK.initSDK(this,"190464913977a");
    }

    public static Context getContext(){
        return sContext;
    }
}
