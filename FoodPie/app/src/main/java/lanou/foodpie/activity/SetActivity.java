package lanou.foodpie.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.io.File;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import lanou.foodpie.R;
import lanou.foodpie.abs.AbsBaseActivity;
import lanou.foodpie.constant.DataCleanManager;
import lanou.foodpie.constant.Eliminate;

/**
 * Created by ZhangRui on 16/11/4.
 */
public class SetActivity extends AbsBaseActivity implements View.OnClickListener {
    private LinearLayout setLl;
    private ImageButton setBtn;
    private LinearLayout proposalLl;
    private Button backBtn;
    private PlatformActionListener platformActionListener;

    @Override
    protected int getLayout() {
        return R.layout.activity_set;
    }

    @Override
    protected void intiViews() {
        setLl = bindView(R.id.setLl);
        setBtn = bindView(R.id.setBtn);
        backBtn = bindView(R.id.backBtn);
        proposalLl = bindView(R.id.proposalLl);
        setLl.setOnClickListener(this);
        setBtn.setOnClickListener(this);
        proposalLl.setOnClickListener(this);
        backBtn.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        platformActionListener = new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                platform.getDb().exportData();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setLl:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("是与否只在一点之间请慎重选择啊");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataCleanManager.cleanInternalCache(this);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
            case R.id.setBtn:
                onBackPressed();
                break;
            case R.id.proposalLl:
                Uri uri = Uri.parse("mailto:food_library@boohee.com");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
                break;
            case R.id.backBtn:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                if (qq.isAuthValid()) {
                    qq.removeAccount(true);
                }
                qq.setPlatformActionListener(platformActionListener);
//authorize与showUser单独调用一个即可
                qq.authorize();//单独授权，OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息
//isValid和removeAccount不开启线程，会直接返回。
//                qq.removeAccount(true);
                finish();
                break;
        }
    }

}
