package lanou.foodpie.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
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
import lanou.foodpie.adpter.SearchAdapter;
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

    @Override
    protected int getLayout() {
        return R.layout.foodencyclopedias_activity;
    }

    @Override
    protected void intiViews() {
        foodLv = bindView(R.id.foodLv);
        nameTv = bindView(R.id.nameTv);
        popTv = bindView(R.id.popTv);
        setClick(this,popTv);

        foodSorAdapter = new FoodSorAdapter(this);
        Intent intent = getIntent();
        kind = intent.getStringExtra("kind");
        name = intent.getStringExtra("name");
        nameTv.setText(name);
        id = String.valueOf(intent.getIntExtra("id", 0));


    }

    private void initSearch() {
        final SearchAdapter searchAdapter = new SearchAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        popRv.setLayoutManager(manager);
        GsonRequest<SearchBean> gsonRequest = new
                GsonRequest<SearchBean>(SearchBean.class, url, new Response.Listener<SearchBean>() {
            @Override
            public void onResponse(SearchBean response) {
                searchAdapter.setBeanList(response.getTypes());
                popRv.setAdapter(searchAdapter);

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
        // 获取自定义布局文件的视图
        View popupWindow_view = getLayoutInflater().inflate(R.layout.foodencyclopedias_popwindow, null,false);
        popRv = (RecyclerView)popupWindow_view.findViewById(R.id.popRv);
        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
        popupWindow = new PopupWindow(popupWindow_view, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        // 这里是位置显示方式
        popupWindow.showAsDropDown(popTv, 0, 0);

        // 点击其他地方消失
        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
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
    }

}

