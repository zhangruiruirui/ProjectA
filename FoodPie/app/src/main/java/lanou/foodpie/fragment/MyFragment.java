package lanou.foodpie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import lanou.foodpie.abs.BaseFragment;
import lanou.foodpie.R;

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

        LlMyPhoto.setOnClickListener(this);
        LlMyCollection.setOnClickListener(this);
        LlFoodData.setOnClickListener(this);
        LlMyOrderLl.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);
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

        }
    }
}
