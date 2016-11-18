package lanou.foodpie.abs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by ZhangRui on 16/10/21.
 * Activity的基类
 * 在这个Activity的基类中: 定制一些Activity的代码书写流程,
 * 精简一下findViewById和Intent的, 将手机电量信号状态栏去掉等等的应用共同属性
 */
public abstract class AbsBaseActivity extends AppCompatActivity {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // 去掉手机电量状态栏
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            // 绑定布局
            setContentView(getLayout());
            // 初始化组件
            intiViews();
            // 初始化数据
            initData();

        }

    protected abstract int getLayout();
    // 初始化组件, 各种findViewById
    protected abstract void intiViews();
    // 初始化数据, 基本上就是 拉去网络数据
    protected abstract void initData();
    // 简化findViewById 省去强转的过程
    protected <T extends View> T bindView (int id) {
        return (T) findViewById(id);

    }
    protected void setClick(View.OnClickListener clickListener,
                            View ... views) {
        for (View view : views) {
            view.setOnClickListener(clickListener);
        }
    }


    // 单例
    /**
     * If there is no bug, then it is created by ChenFengYao on 2016/11/18,
     * otherwise, I do not know who create it either.
     */
//    public class MySingle {
//        private static final AtomicReference<MySingle> INSTANCE = new AtomicReference<MySingle>();
//
//        public static MySingle getInstance() {
//            for (; ; ) {
//                MySingle current = INSTANCE.get();
//                if (current != null) {
//                    return current;
//                }
//                current = new MySingle();
//                if (INSTANCE.compareAndSet(null, current)) {
//                    return current;
//                }
//            }
//        }
//
//        private MySingle() {
//        }
//    }


}