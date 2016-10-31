package lanou.foodpie.web;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by ChenFengYao on 2016/10/24.
 * ImageLoader 使用的内存缓存
 */

public class MemoryCache
        implements ImageLoader.ImageCache {
    //实现了最近最少使用算法
    //用法和hashMap类似
    private LruCache<String, Bitmap> mCache;

    public MemoryCache() {
        int maxSize = //缓存的上限是 内存的1/8
                (int) (Runtime.getRuntime()
                        .maxMemory() / 8);
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //返回每一个元素占用的大小,单位需要和maxSize保持一致
                return value.getByteCount();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }
}
