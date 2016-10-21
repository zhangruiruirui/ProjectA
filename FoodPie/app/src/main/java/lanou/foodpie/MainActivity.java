package lanou.foodpie;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

// 食物百科 逛吃 我的TabLayout ViewPager显示的Activity
public class MainActivity extends AbsBaseActivity {
    private TabLayout ;

    @Override
    protected int getLayout() {
        // 绑定布局
        return R.layout.activity_main;
    }
    // 利用基类找到布局id
    @Override
    protected void intiViews() {
        tbMain = bindView(R.id.tbMain);

        // 添加Fragment集合
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new FoodEncyclopediasFragment());
        list.add(new ToEatFragment());
        list.add(new MyFragment());
        // 创建适配器
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        adapter.setFragments(list);


    }

    @Override
    protected void initData() {

    }
}
