package lanou.foodpie.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import lanou.foodpie.abs.BaseFragment;
import lanou.foodpie.R;
import lanou.foodpie.activity.LoginActivity;
import lanou.foodpie.activity.SetActivity;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/21.
 * 我的Fragment
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout LlMyPhoto;
    private LinearLayout LlMyCollection;
    private LinearLayout LlFoodData;
    private LinearLayout LlMyOrderLl;
    private Button btnLogIn;
    private ImageView myIv;
    private ImageButton setBtn;
    private TextView maNameTv;
    private Button myDataBtn;
    private String name;
    private String icon;


    @Override
    protected void initData() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        try {

            PlatformDb platformDb = qq.getDb();
            name = platformDb.getUserName();
            icon = platformDb.getUserIcon();

            if (!TextUtils.isEmpty(name)) {
                btnLogIn.setVisibility(View.GONE);
                maNameTv.setVisibility(View.VISIBLE);
                myDataBtn.setVisibility(View.VISIBLE);
                maNameTv.setText(name);
                VolleySingleton.getInstance().getImage(icon, myIv);
            }
        } catch (NullPointerException e) {

        }
    }

    @Override
    protected void initView() {
        LlMyPhoto = bindView(R.id.myPhotoLl);
        LlMyCollection = bindView(R.id.myCollectionLl);
        LlFoodData = bindView(R.id.foodDataLl);
        LlMyOrderLl = bindView(R.id.myOrderLl);
        btnLogIn = bindView(R.id.myLogInBtn);
        myIv = bindView(R.id.myIv);
        maNameTv = bindView(R.id.myNameTv);
        setBtn = bindView(R.id.setBtn);
        myDataBtn = bindView(R.id.myDataBtn);

        LlMyPhoto.setOnClickListener(this);
        LlMyCollection.setOnClickListener(this);
        LlFoodData.setOnClickListener(this);
        LlMyOrderLl.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);
        myIv.setOnClickListener(this);
        setBtn.setOnClickListener(this);
        // 请求头像图片
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_analyse_default);
        myIv.setImageBitmap(toRoundCorner(b, 100));
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myPhotoLl:

                break;
            case R.id.myCollectionLl:
                break;
            case R.id.foodDataLl:
                break;
            case R.id.myOrderLl:
                break;
            case R.id.myLogInBtn:
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent1, 1);

                break;
            case R.id.myIv:
                break;
            case R.id.setBtn:
                Intent intent = new Intent(getActivity(), SetActivity.class);
                startActivityForResult(intent,1);
                break;

        }
    }

    // 圆形头像方法
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        Log.d("MyFragment", "resultCode:" + resultCode);
        if (requestCode == 1 && LoginActivity.RESULT == resultCode) {

            Log.d("MyFragment123", "yubxibgfdsfnl");
            name = data.getStringExtra("name");
            Log.d("MyFragment123", name);
            icon = data.getStringExtra("icon");
            Log.d("MyFragment123", icon);
            btnLogIn.setVisibility(View.GONE);
            maNameTv.setVisibility(View.VISIBLE);
            myDataBtn.setVisibility(View.VISIBLE);
            maNameTv.setText(name);
            VolleySingleton.getInstance().getImage(icon, myIv);

        }

    }

}
