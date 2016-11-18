package lanou.foodpie.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import lanou.foodpie.abs.AbsBaseActivity;
import lanou.foodpie.fragment.FoodEncyclopediasFragment;
import lanou.foodpie.fragment.MyFragment;
import lanou.foodpie.R;
import lanou.foodpie.fragment.ToEatFragment;

// MainRadioButton界面
public class MainActivity extends AbsBaseActivity {
    private RadioButton foodRb;
    private RadioButton eatRb;
    private RadioButton myRb;
    private RadioButton change;
    private RadioGroup rgMain;
    private FrameLayout mainFram;
    private FragmentManager manager;
    private FoodEncyclopediasFragment foodFragment;
    private ToEatFragment toeatFragment;
    private MyFragment myFragment;

    @Override
    protected int getLayout() {
        // 绑定布局
        return R.layout.activity_main;
    }
    // 利用基类找到布局id
    @Override
    protected void intiViews() {
        foodRb = bindView(R.id.rbFood);
        eatRb = bindView(R.id.rbEat);
        myRb = bindView(R.id.rbMy);
        rgMain = bindView(R.id.rgMain);
        mainFram = bindView(R.id.frameMain);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void initData() {
        myFragment = new MyFragment();
        toeatFragment = new ToEatFragment();
        foodFragment = new FoodEncyclopediasFragment();
        manager = getSupportFragmentManager();
        foodRb.setSelected(true);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameMain, foodFragment);
        transaction.commit();

        //RadioButton的点击切换
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = manager.beginTransaction();

                switch (checkedId) {
                    case R.id.rbFood:
                        foodRb.setSelected(true);
                        transaction.replace(R.id.frameMain, foodFragment);
                        break;
                    case R.id.rbEat:
                        eatRb.setSelected(true);
                        transaction.replace(R.id.frameMain, toeatFragment);
                        break;
                    case R.id.rbMy:
                        myRb.setSelected(true);
                        transaction.replace(R.id.frameMain, myFragment);
                        break;
                }
                transaction.commit();
            }
        });


    }



}
