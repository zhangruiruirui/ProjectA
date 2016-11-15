package lanou.foodpie.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import lanou.foodpie.R;
import lanou.foodpie.abs.AbsBaseActivity;
import lanou.foodpie.adpter.FoodSorAdapter;
import lanou.foodpie.adpter.PopAdapter;
import lanou.foodpie.adpter.PopAllAdapter;
import lanou.foodpie.bean.FoodDataBean;
import lanou.foodpie.bean.FoodSortBean;
import lanou.foodpie.bean.SearchBean;
import lanou.foodpie.constant.UrlWeb;
import lanou.foodpie.web.GsonRequest;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/11/5.
 */
public class FoodEncyclopediasActivity extends AbsBaseActivity implements View.OnClickListener {
    private ListView foodLv;
    private String kind;
    private String id;
    private String name;
    private TextView nameTv;
    private String url = UrlWeb.urlSearch;
    private List<FoodSortBean.FoodsBean> foods;
    private FoodSorAdapter foodSorAdapter;
    private TextView popTv;
    private PopupWindow popupWindow;
    private RecyclerView popRv;
    private TextView popAllTv;
    private List<FoodDataBean.GroupBean.CategoriesBean.SubCategoriesBean> categories;

    @Override
    protected int getLayout() {
        return R.layout.foodencyclopedias_activity;
    }

    @Override
    protected void intiViews() {
        foodLv = bindView(R.id.foodLv);
        nameTv = bindView(R.id.nameTv);
        popTv = bindView(R.id.popTv);
        popAllTv = bindView(R.id.popAllTv);
        setClick(this, popTv, popAllTv);
        foodSorAdapter = new FoodSorAdapter(this);
        Intent intent = getIntent();
        kind = intent.getStringExtra("kind");
        name = intent.getStringExtra("name");
        nameTv.setText(name);
        id = String.valueOf(intent.getIntExtra("id", 0));
        nameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void initSearch() {
        final PopAdapter popAdapter = new PopAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        popRv.setLayoutManager(manager);
        GsonRequest<SearchBean> gsonRequest = new
                GsonRequest<SearchBean>(SearchBean.class, url, new
                Response.Listener<SearchBean>() {
                    @Override
                    public void onResponse(SearchBean response) {
                        popAdapter.setBeanList(response.getTypes());
                        popRv.setAdapter(popAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }

    @Override
    protected void initData() {
        String url = UrlWeb.urlFoodSorBefore + kind + "&value=" + id + UrlWeb.urlFoodSorAfter;

        GsonRequest<FoodSortBean> gsonRequest = new
                GsonRequest<FoodSortBean>(FoodSortBean.class,
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.popTv:
                Log.d("FoodEncyclopediasActivi", "aa");
                // 获取自定义布局文件的视图
                View popupWindowView = getLayoutInflater().inflate
                        (R.layout.foodencyclopedias_popwindow, null, false);
                popRv = (RecyclerView) popupWindowView.findViewById(R.id.popRv);
                // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
                popupWindow = new PopupWindow
                        (popupWindowView, RelativeLayout.LayoutParams.MATCH_PARENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT, true);
                // 这里是位置显示方式方向
                popupWindow.showAsDropDown(v, 0, 0);

                // 点击其他地方消失
                popupWindowView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (popupWindow != null && popupWindow.isShowing()) {
                            popupWindow.dismiss();
                            popupWindow = null;

                        }
                        return false;
                    }
                });
                initSearch();
                break;
            case R.id.popAllTv:
                Log.d("FoodEncyclopediasActivi", "aa");

                // 获取自定义布局文件的视图
                View popupWindowViewAll = getLayoutInflater().inflate
                        (R.layout.foodencyclopedias_popwindowall, null, false);
                RecyclerView popAllRv = (RecyclerView) popupWindowViewAll.findViewById(R.id.popAllRv);
                PopAllAdapter popAllAdapter = new PopAllAdapter(this);
                popAllRv.setAdapter(popAllAdapter);
                LinearLayoutManager manager = new LinearLayoutManager(this);
                popAllRv.setLayoutManager(manager);
                categories = (List<FoodDataBean.GroupBean.CategoriesBean.SubCategoriesBean>)
                        this.getIntent().getSerializableExtra("categories");
                Log.d("FoodEncyclopediasActivi", "categories:" + categories);
                popAllAdapter.setBeanList(categories);
                // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
                popupWindow = new PopupWindow
                        (popupWindowViewAll, RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT, true);
                // 这里是位置显示方式方向
                popupWindow.showAsDropDown(popAllTv, 0, 0);
                // 点击其他地方消失
                popupWindowViewAll.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (popupWindow != null && popupWindow.isShowing()) {
                            popupWindow.dismiss();
                            popupWindow = null;
                        }
                        return false;
                    }

                });
                break;
        }
    }

}

