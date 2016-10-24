package lanou.foodpie;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

//
public class MainActivity extends AbsBaseActivity implements View.OnClickListener {
    private RadioButton foodRb;
    private RadioButton eatRb;
    private RadioButton myRb;
    private FragmentTransaction transaction;
    private FragmentManager manager;

    @Override
    protected int getLayout() {
        // 绑定布局
        return R.layout.activity_main;
    }
    // 利用基类找到布局id
    @Override
    protected void intiViews() {
        foodRb = bindView(R.id.btnFood);
        eatRb = bindView(R.id.btnToEat);
        myRb = bindView(R.id.btnMy);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.rgMain,new FoodEncyclopediasFragment());
        transaction.commit();
        setClick(this,foodRb);
        setClick(this,eatRb);
        setClick(this,myRb);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.btnFood:
                transaction.replace(R.id.rgMain,new FoodEncyclopediasFragment());
                break;
            case R.id.btnToEat:
                transaction.replace(R.id.rgMain,new ToEatFragment());
                break;
            case R.id.btnMy:
                transaction.replace(R.id.rgMain,new MyFragment());
                break;

        }
        transaction.commit();

    }
}
