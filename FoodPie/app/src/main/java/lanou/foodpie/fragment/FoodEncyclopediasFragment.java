package lanou.foodpie.fragment;

import android.content.Intent;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.io.Serializable;
import java.util.List;

import lanou.foodpie.abs.BaseFragment;
import lanou.foodpie.R;
import lanou.foodpie.activity.FoodEncyclopediasActivity;
import lanou.foodpie.adpter.FoodEncyclopediasAdapter;
import lanou.foodpie.bean.FoodDataBean;
import lanou.foodpie.onclickItemlistener.OnClickFoodSor;
import lanou.foodpie.web.GsonRequest;
import lanou.foodpie.constant.UrlWeb;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/21.
 * 食物百科Fragment
 */
public class FoodEncyclopediasFragment extends BaseFragment implements OnClickFoodSor {
    private String uri = UrlWeb.urlFood;
    private FoodEncyclopediasAdapter packsAdapter = new FoodEncyclopediasAdapter();
    private FoodEncyclopediasAdapter hotAdapter = new FoodEncyclopediasAdapter();
    private FoodEncyclopediasAdapter chainAdapter = new FoodEncyclopediasAdapter();
    //    private List<FoodDataBean.GroupBean.CategoriesBean> beanList;
    private GridView gvPacks;
    private GridView gvHot;
    private GridView gvChain;

    @Override
    protected int getLayout() {
        return R.layout.fragment_foodencylopedias;
    }

    @Override
    protected void initView() {
        gvChain = bindView(R.id.packsGv);
        gvHot = bindView(R.id.hotGv);
        gvPacks = bindView(R.id.chainGv);

        packsAdapter = new FoodEncyclopediasAdapter();
        hotAdapter = new FoodEncyclopediasAdapter();
        chainAdapter = new FoodEncyclopediasAdapter();

        packsAdapter.setonClickFoodSor(this);
        hotAdapter.setonClickFoodSor(this);
        chainAdapter.setonClickFoodSor(this);
    }
    @Override
    protected void initData() {
        GsonRequest<FoodDataBean> gsonRequest = new GsonRequest<FoodDataBean>(FoodDataBean.class, uri,
                new Response.Listener<FoodDataBean>() {
                    @Override
                    public void onResponse(FoodDataBean response) {
                        for (int j = 0; j < 3; j++) {
//                            beanList = response.getGroup().get(j).getCategories();
                            switch (j) {
                                case 0:
                                    packsAdapter.setFoodDataBean(0, response);
                                    gvChain.setAdapter(packsAdapter);
                                    break;
                                case 1:
                                    hotAdapter.setFoodDataBean(1, response);
                                    gvHot.setAdapter(hotAdapter);
                                    break;
                                case 2:
                                    chainAdapter.setFoodDataBean(2, response);
                                    gvPacks.setAdapter(chainAdapter);
                                    break;
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }

    @Override
    public void onClickFood(String kind, int id, String name,List categories) {
        Intent intent = new Intent(getActivity(), FoodEncyclopediasActivity.class);
        intent.putExtra("kind", kind);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("categories",(Serializable) categories);
        startActivity(intent);
    }
}