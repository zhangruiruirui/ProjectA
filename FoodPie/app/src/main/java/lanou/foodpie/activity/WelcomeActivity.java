package lanou.foodpie.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.bmob.v3.Bmob;
import lanou.foodpie.R;
import lanou.foodpie.abs.AbsBaseActivity;

/**
 * Created by ZhangRui on 16/10/21.
 * // 欢迎界面
 */
public class WelcomeActivity extends AbsBaseActivity implements View.OnClickListener {
    private TextView welcomeTv;
    private ImageView welcomeIv;
    private boolean timerisclosed = false;
    private CountDownTimer timer;

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void intiViews() {
        welcomeTv = bindView(R.id.welcomeTv);
        welcomeIv = bindView(R.id.welcomeIv);
        setClick(this, welcomeIv);


    }

    @Override
    protected void initData() {
        timer = new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                welcomeTv.setText((millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                if (!timerisclosed) {
                    finishThis();
                }
            }
        };
        timer.start();


    }

    private void finishThis() {
        Intent intent = new Intent();
        intent.setClass(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        timer.cancel();
        finish();
    }

    @Override
    public void onClick(View v) {
        finishThis();
        timerisclosed = !timerisclosed;

    }


}



