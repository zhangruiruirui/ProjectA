package lanou.action;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements ActionBar.OnNavigationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        // 设置是否显示action
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        // 为actionbar设置arrayadpter
        actionBar.setListNavigationCallbacks(new ArrayAdapter<String>(MainActivity.this,R.layout.mytextview,R.id.text1,new String[]{"tab1","tab2","tab3"}),this);
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("key",itemPosition + 1);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = this.getFragmentManager().beginTransaction();
        transaction.replace(R.id.content,fragment);
        transaction.commit();
        return true;
    }
}
