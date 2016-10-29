package lanou.foodpie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import lanou.foodpie.abs.BaseFragment;
import lanou.foodpie.R;
import lanou.foodpie.adpter.FoodEncyclopediasAdapter;
import lanou.foodpie.bean.FoodDataBean;
import lanou.foodpie.bean.FoodEncyclopediasBean;
import lanou.foodpie.gson.GsonRequest;
import lanou.foodpie.gson.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/21.
 * 食物百科Fragment
 */
public class FoodEncyclopediasFragment extends BaseFragment {
    private String uri = "http://food.boohee.com/fb/v1/categories/list";
    ArrayList<FoodEncyclopediasBean> arrayList;
    private FoodEncyclopediasAdapter adapter = new FoodEncyclopediasAdapter();
    private FoodEncyclopediasAdapter mAdapter = new FoodEncyclopediasAdapter();
    private FoodEncyclopediasAdapter nAdapter = new FoodEncyclopediasAdapter();

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
        arrayList = new ArrayList<>();
        adapter = new FoodEncyclopediasAdapter();
        mAdapter = new FoodEncyclopediasAdapter();
        nAdapter = new FoodEncyclopediasAdapter();

    }

    @Override
    protected void initData() {
        GsonRequest<FoodDataBean> gsonrequest = new GsonRequest<FoodDataBean>(FoodDataBean.class, uri,
                new Response.Listener<FoodDataBean>() {
                    @Override
                    public void onResponse(FoodDataBean response) {
                        for (int j = 0; j < 3; j++) {
                            ArrayList<FoodEncyclopediasBean> foodTextBeen = new ArrayList<>();

                            for (int i = 0; i < response.getGroup().get(j).getCategories().size(); i++) {
                                FoodEncyclopediasBean bean = new FoodEncyclopediasBean();

                                bean.setName(response.getGroup().get(j).getCategories().get(i).getName());
                                bean.setImageUrl(response.getGroup().get(j).getCategories().get(i).getImage_url());
                                foodTextBeen.add(bean);
                            }
                            switch (j) {
                                case 0:
                                    adapter.setArrayList(foodTextBeen);
                                    gvChain.setAdapter(adapter);
                                    break;
                                case 1:
                                    mAdapter.setArrayList(foodTextBeen);
                                    gvHot.setAdapter(mAdapter);
                                    break;
                                case 2:
                                    nAdapter.setArrayList(foodTextBeen);
                                    gvPacks.setAdapter(nAdapter);
                                    break;
                            }
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonrequest);

    }
}