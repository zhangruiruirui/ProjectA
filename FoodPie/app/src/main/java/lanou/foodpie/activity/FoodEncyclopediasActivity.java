package lanou.foodpie.activity;

import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import lanou.foodpie.R;
import lanou.foodpie.abs.AbsBaseActivity;
import lanou.foodpie.adpter.FoodSorAdapter;
import lanou.foodpie.bean.FoodSortBean;
import lanou.foodpie.constant.UrlWeb;
import lanou.foodpie.web.GsonRequest;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/11/5.
 */
public class FoodEncyclopediasActivity extends AbsBaseActivity{
    private ListView foodLv;
    private String kind;
    private String id;
    private String name;
    private TextView nameTv;
    private List<FoodSortBean.FoodsBean> foods;
    private FoodSorAdapter foodSorAdapter;

    @Override
    protected int getLayout() {
        return R.layout.foodencyclopedias_activity;
    }

    @Override
    protected void intiViews() {
        foodLv = bindView(R.id.foodLv);
        nameTv = bindView(R.id.nameTv);
        foodSorAdapter = new FoodSorAdapter(this);
        Intent intent = getIntent();
        kind = intent.getStringExtra("kind");
        name = intent.getStringExtra("name");
        nameTv.setText(name);
        id = String.valueOf(intent.getIntExtra("id", 0));

    }

    @Override
    protected void initData() {
        String url = UrlWeb.urlFoodSorBefore + kind + "&value=" + id + UrlWeb.urlFoodSorAfter;

        GsonRequest<FoodSortBean> gsonRequest = new GsonRequest<FoodSortBean>(FoodSortBean.class,
                url, new Response.Listener<FoodSortBean>() {
            @Override
            public void onResponse(FoodSortBean response) {
                foods = response.getFoods();
                foodSorAdapter.setFoodsBeen(foods);
                foodLv.setAdapter(foodSorAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);

    }

    }

