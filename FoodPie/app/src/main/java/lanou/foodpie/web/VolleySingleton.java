package lanou.foodpie.web;

import android.graphics.Bitmap;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import lanou.foodpie.R;
import lanou.foodpie.abs.MyApp;

/**
 * Created by ChenFengYao on 2016/10/24.
 */

public class VolleySingleton {

    private static VolleySingleton volleySingleton;
    private RequestQueue mRequestQueue;

    private ImageLoader mImageLoader;//用来请求图片的

    private VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(MyApp.getContext());
        mImageLoader //初始化ImageLoader
                = new ImageLoader(mRequestQueue,new MemoryCache());
    }

    public static VolleySingleton getInstance() {
        if (volleySingleton == null) {
            synchronized (VolleySingleton.class) {
                if (volleySingleton == null) {
                    volleySingleton = new VolleySingleton();
                }
            }
        }
        return volleySingleton;
    }

    //请求图片
    public void getImage(String url, ImageView imageView){

//        mImageLoader.get(url,ImageLoader.getImageListener(
//                imageView,R.mipmap.ic_launcher
//                ,R.mipmap.ic_launcher
//        ));
        mImageLoader.get(url,new AnimImageListener(imageView));
    }

    class AnimImageListener implements ImageLoader.ImageListener{
        private ImageView mImageView;

        public AnimImageListener(ImageView imageView) {
            mImageView = imageView;
        }

        @Override
        public void onResponse(ImageLoader.ImageContainer response
                , boolean isImmediate) {
            Bitmap bitmap = response.getBitmap();
            if (bitmap == null){
                //图片还在请求中
                mImageView.setImageResource(R.mipmap.img_home_evaluating);
            }else {
                //图片请求成功
                mImageView.setImageBitmap(bitmap);
//                //添加动画效果
//                AlphaAnimation alphaAnimation =
//                        new AlphaAnimation(0,1f);
//                alphaAnimation.setDuration(5000);
//                mImageView.setAnimation(alphaAnimation);
//
//                alphaAnimation.start();
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            mImageView.setImageResource(R.mipmap.img_default_food_thumbnail);
        }
    }


    //获得RequestQueue
    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    public <T> void addRequest(Request<T> request){
        mRequestQueue.add(request);
    }
}
