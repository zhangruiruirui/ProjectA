package lanou.volleydemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/10/24.
 * !!!!!!写完 MyApp一定要注册
 */
public class MyApp extends Application {
    // 所有和界面无关的都可以用这个全局的Context
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
    public static Context getContext() {
        return context;
    }
}
