package lanou.foodpie.fragment;

import android.content.Intent;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import lanou.foodpie.abs.BaseFragment;
import lanou.foodpie.R;
import lanou.foodpie.activity.SetActivity;

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
    private ImageView myIb;
    private ImageButton setBtn;


    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {
        LlMyPhoto = bindView(R.id.myPhotoLl);
        LlMyCollection = bindView(R.id.myCollectionLl);
        LlFoodData =bindView(R.id.foodDataLl);
        LlMyOrderLl = bindView(R.id.myOrderLl);
        btnLogIn = bindView(R.id.myLogInBtn);
        myIb = bindView(R.id.myIb);
        setBtn = bindView(R.id.setBtn);

        LlMyPhoto.setOnClickListener(this);
        LlMyCollection.setOnClickListener(this);
        LlFoodData.setOnClickListener(this);
        LlMyOrderLl.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);
        myIb.setOnClickListener(this);
        setBtn.setOnClickListener(this);
        // 请求头像图片
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_analyse_default);
        myIb.setImageBitmap(toRoundCorner(b,100));
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
                break;
            case R.id.myIb:
                break;
            case R.id.setBtn:
                Intent intent = new Intent(getActivity(),SetActivity.class);
                startActivity(intent);
                break;

        }
    }
    // 圆形头像方法
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels){
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
}
