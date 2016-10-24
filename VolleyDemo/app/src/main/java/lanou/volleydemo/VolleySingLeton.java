package lanou.volleydemo;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by dllo on 16/10/24.
 */
public class VolleySingLeton {
    private static VolleySingLeton volleySingLeton;
    private ImageLoader imageLoader;// 用来请求图片的
    private  RequestQueue mRequestQueue;
    private VolleySingLeton() {
        mRequestQueue = Volley.newRequestQueue(MyApp.getContext());
        imageLoader = new ImageLoader(mRequestQueue,new MemoryCache());
        mRequestQueue = Volley.newRequestQueue(MyApp.getContext());
    }
    public static VolleySingLeton getInstance() {
        if (volleySingLeton == null) {
            synchronized (VolleySingLeton.class) {
                if (volleySingLeton == null) {
                    volleySingLeton = new VolleySingLeton();
                }
            }

        }
        return volleySingLeton;
    }
    // 请求图片
    public void getImage(String url , ImageView imageView) {
        imageLoader.get(url,imageLoader.getImageListener(imageView,R.mipmap.ic_launcher,R.mipmap.ic_launcher));
    }
    // 获得RequestQueue 方法
    public RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }
    public <T> void addRequest(Request<T> request) {
        mRequestQueue.add(request);
    }
    }

