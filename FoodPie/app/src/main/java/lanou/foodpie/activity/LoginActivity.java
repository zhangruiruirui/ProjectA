package lanou.foodpie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import lanou.foodpie.R;
import lanou.foodpie.abs.AbsBaseActivity;

public class LoginActivity extends AbsBaseActivity implements View.OnClickListener {


    private TextView qqTv;
    private TextView wxTv;
    public static final int RESULT = 0;
    private LinearLayout loginLl;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void intiViews() {
        qqTv = bindView(R.id.qqTv);
        wxTv = bindView(R.id.wxTv);
        loginLl = bindView(R.id.loginLl);
        setClick(this, qqTv, wxTv, loginLl);

    }

    @Override
    protected void initData() {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qqTv:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.authorize();
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        PlatformDb platformDb = platform.getDb();
                        String name = platformDb.getUserName();
                        Log.d("LoginActivity", name);
                        String icon = platformDb.getUserIcon();
                        Intent intent = new Intent();
                        intent.putExtra("name", name);
                        intent.putExtra("icon", icon);
                        setResult(RESULT, intent);
                        finish();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                break;
            case R.id.loginLl:
                onBackPressed();
                break;


        }

    }


}
