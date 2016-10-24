package lanou.jiaxin;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 创建添加快捷方式intent
        Intent addIntent = new Intent("com.android."+"launcher.action.INSTALL_SHORTCUT");
        String title = getResources().getString(R.string.app_name);
        // 加载快捷方式图标
        Parcelable icon = Intent.ShortcutIconResource.fromContext(MainActivity.this,R.mipmap.ic_launcher);
        // 创建点击跨界方式后对应的Intent
        Intent myIntent = new Intent(MainActivity.this,MainActivity.class);
        // 设置快捷方式的标题
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,title);
        // 设置图标快捷方式
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,icon);
        // 设置快捷方式的Intent
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,myIntent);
        // 发送广播添加快捷方式
        sendBroadcast(addIntent);
    }
}
