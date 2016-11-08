package lanou.foodpie.activity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import lanou.foodpie.R;
import lanou.foodpie.abs.AbsBaseActivity;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/11/7.
 */
public class HomePagerDetailsActivity extends AbsBaseActivity {
    private ImageView iconIv;
    private TextView backTv;
    private TextView shareTv;
    private TextView nameTv;
    private TextView timeTv;
    private ImageView imagesIv;
    private ImageView likesIv;
    private TextView likesTv;
    private String name;
    private String likes;
    private String icon;
    private String images;
    @Override
    protected int getLayout() {
        return R.layout.activity_homepagerdetails;
    }

    @Override
    protected void intiViews() {
        iconIv = bindView(R.id.iconIv);
        backTv = bindView(R.id.backTv);
        shareTv = bindView(R.id.shareTv);
        nameTv = bindView(R.id.nameTv);
        timeTv = bindView(R.id.timeTv);
        imagesIv = bindView(R.id.imagesIv);
        likesIv = bindView(R.id.imagesIv);
        likesTv = bindView(R.id.likesTv);
        Intent intent = getIntent();
        intent.getStringExtra("image");
        intent.getStringExtra("headIcon");
        intent.getStringExtra("name");
        intent.getStringExtra("Like_ct");

        nameTv.setText(name);
        likesTv.setText(likes);
        VolleySingleton.getInstance().getImage(images,imagesIv);
        VolleySingleton.getInstance().getImage(icon,iconIv);



    }

    @Override
    protected void initData() {

    }
}
