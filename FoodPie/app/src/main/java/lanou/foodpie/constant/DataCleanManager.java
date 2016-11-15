package lanou.foodpie.constant;

/**
 * Created by ZhangRui on 16/11/11.
 */

import android.content.Context;
import android.content.DialogInterface;

import java.io.File;

/** * 本应用数据清除管理器 */
public class DataCleanManager {
    /**
     * * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * *
     *
     * @param context
     */
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }
    /**
     * * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * *
     *
     * @param directory
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

    public static DataCleanManager cleanInternalCache(DialogInterface.OnClickListener onClickListener) {

        return null;
    }
}
