package lanou.foodpie.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import lanou.foodpie.R;
import lanou.foodpie.abs.AbsBaseActivity;

/**
 * Created by ZhangRui on 16/11/7.
 */
public class HomePagerLinkActivity extends AbsBaseActivity {
    private String link;
    private WebView webView;
    private TextView linkTv;

    @Override
    protected int getLayout() {
        return R.layout.activity_homepagerlink;
    }

    @Override
    protected void intiViews() {
        webView = bindView(R.id.linkWv);
        linkTv = bindView(R.id.linkTv);
        linkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        link = intent.getStringExtra("Link");
        Log.d("HomePagerLinkActivity", "link");
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                webView.loadUrl(link);
                return true;
            }
        });
    }

    @Override
    protected void initData() {

    }
}
