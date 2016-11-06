package lanou.foodpie.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import lanou.foodpie.R;
import lanou.foodpie.abs.AbsBaseActivity;
import lanou.foodpie.constant.Eliminate;

/**
 * Created by ZhangRui on 16/11/4.
 */
public class SetActivity extends AbsBaseActivity implements View.OnClickListener {
    private LinearLayout setLl;
    private ImageButton setBtn;

    @Override
    protected int getLayout() {
        return R.layout.activity_set;
    }

    @Override
    protected void intiViews() {
        setLl = bindView(R.id.setLl);
        setBtn = bindView(R.id.setBtn);
        setLl.setOnClickListener(this);
        setBtn.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setLl:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("确定清除本地缓存图片吗?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
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
        }
    }
}
