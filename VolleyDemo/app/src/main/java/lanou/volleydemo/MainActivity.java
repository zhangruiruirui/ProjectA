package lanou.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private String url = "https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=all&pagingAction=up";
    private String imgUrl = "http://img.hb.aicdn.com/df07ed49425e3c3ec9815230fffcf1a4ace4c0fa2217f-1VeCex_fw658";
    private ImageView mainIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainIv = (ImageView) findViewById(R.id.main_iv);
        // volley的使用分为三步
        // 第一步 创建请求队列
//        RequestQueue requestQueue
//                //= Volley.newRequestQueue(this);
//        = VolleySingLeton.getInstance().getmRequestQueue();
        // 第二部 创建请求
        Gsonrequest<TestBean> gsonrequest = new Gsonrequest<TestBean>(TestBean.class,
                url, new Response.Listener<TestBean>() {

            @Override
            public void onResponse(TestBean response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
//        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                // 请求成功
//                Log.d("MainActivity", response);
//                // 解析
//                Gson gson = new Gson();
//                TestBean bean = gson.fromJson(response,TestBean.class);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // 请求失败
//                Log.d("MainActivity", error.getMessage());
//            }
//        });
        // 第三部 把请求放入请求队列
//        requestQueue.add(stringRequest);
        VolleySingLeton.getInstance().addRequest(gsonrequest);
        // 请求图片
        VolleySingLeton.getInstance().getImage(imgUrl,mainIv);
//        ImageLoader imageLoader = new ImageLoader(VolleySingLeton.getInstance().getmRequestQueue(),new MemoryCache());
//        imageLoader.get(imgUrl,imageLoader.getImageListener(mainIv,R.mipmap.ic_launcher,R.mipmap.ic_launcher));
    }
}
