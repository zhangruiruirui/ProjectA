package lanou.foodpie.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import lanou.foodpie.R;
import lanou.foodpie.abs.BaseFragment;
import lanou.foodpie.abs.EndLessOnScrollListener;
import lanou.foodpie.adpter.DeliciousFoodAdapter;
import lanou.foodpie.adpter.KnowledgeAdapter;
import lanou.foodpie.bean.DeliciousFoodBean;
import lanou.foodpie.bean.DeliciousFoodDataBean;
import lanou.foodpie.bean.KnowledgeBean;
import lanou.foodpie.bean.KnowledgeDataBean;
import lanou.foodpie.web.GsonRequest;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/25.
 * 美食fragment
 */
public class DeliciousFoodFragment extends BaseFragment {
    private SwipeRefreshLayout deliciousSr;
    private RecyclerView deliciousRv;
    private int page = 1;
    private String url = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=4&per=10";
    private DeliciousFoodAdapter deliciousFoodAdapter;
    private ArrayList<DeliciousFoodBean> arrayList;
    private StaggeredGridLayoutManager manager;
    private EndLessOnScrollListener endLessOnScrollListener;

    @Override
    protected void initData() {
        arrayList = new ArrayList<>();
        deliciousFoodAdapter = new DeliciousFoodAdapter(getContext());
        getGsonRequest(url);
        manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        deliciousRv.setLayoutManager(manager);
        deliciousRv.setAdapter(deliciousFoodAdapter);
        deliciousRv.addOnScrollListener(endLessOnScrollListener = new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                deliciousSr.setRefreshing(true);
                getGsonRequest("http://food.boohee.com/fb/v1/feeds/category_feed?page=" + page + "&category=4&per=10");
                page++;
                deliciousSr.setRefreshing(false);
            }
        });

        deliciousSr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayList.clear();
                getGsonRequest(url);

            }
        });

    }

    @Override
    protected void initView() {
        deliciousRv = bindView(R.id.deliciousRv);
        deliciousSr = bindView(R.id.deliciousSr);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_deliciousfood;
    }

    protected void getGsonRequest(String uri) {
        GsonRequest<DeliciousFoodDataBean> gsonRequest = new GsonRequest<DeliciousFoodDataBean>(DeliciousFoodDataBean.class, uri, new Response.Listener<DeliciousFoodDataBean>() {
            @Override
            public void onResponse(DeliciousFoodDataBean response) {

                for (int i = 0; i < response.getFeeds().size(); i++) {
                    DeliciousFoodBean bean = new DeliciousFoodBean();
                    bean.setImages(response.getFeeds().get(i).getImages());
                    bean.setTitle(response.getFeeds().get(i).getTitle());
                    bean.setType(response.getFeeds().get(i).getType());
                    bean.setTail(response.getFeeds().get(i).getTail());
                    bean.setSource(response.getFeeds().get(i).getSource());
                    arrayList.add(bean);


                }
                deliciousFoodAdapter.setArrayList(arrayList);
                endLessOnScrollListener.resetPreviousTotal();
                deliciousSr.setRefreshing(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}
