package lanou.foodpie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import lanou.foodpie.R;
import lanou.foodpie.abs.AbsBaseActivity;


public class KnowledgeActivity extends AbsBaseActivity implements View.OnClickListener {
    private String link;
    private WebView webView;
    private TextView linkTv;


    @Override
    protected int getLayout() {
        return R.layout.activity_eatdetails;
    }

    @Override
    protected void intiViews() {
        webView = bindView(R.id.linkWv);
        linkTv = bindView(R.id.linkTv);
        setClick(this,linkTv);
        Intent intent = getIntent();
        link = intent.getStringExtra("link");
        Log.d("EvaDetailsActivity", link);
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

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
