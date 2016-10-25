package lanou.foodpie.abs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by ZhangRui on 16/10/21.
 * Activity的基类
 * 在这个Activity的基类中: 定制一些Activity的代码书写流程, 精简一下findViewById和Intent的, 将手机电量信号状态栏去掉等等的应用共同属性
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
    // 简化findViewById 省去墙砖的过程
    protected <T extends View> T bindView (int id) {
        return (T) findViewById(id);

    }
    protected void setClick(View.OnClickListener clickListener,
                            View ... views) {
        for (View view : views) {
            view.setOnClickListener(clickListener);
        }
    }


}