package lanou.foodpie.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import lanou.foodpie.R;
import lanou.foodpie.abs.BaseFragment;
import lanou.foodpie.abs.EndLess;
import lanou.foodpie.adpter.EvaluatingAdapter;
import lanou.foodpie.bean.EvaluatingBean;
import lanou.foodpie.web.GsonRequest;
import lanou.foodpie.constant.UrlWeb;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/25.
 * 评测fragment
 */
public class EvaluatingFragment extends BaseFragment {
    private SwipeRefreshLayout evaluatingSr;
    private RecyclerView evaluatingRv;
    private String url = UrlWeb.urlEva;
    private List<EvaluatingBean.FeedsBean> feeds;
    private EvaluatingAdapter adapter;
    private LinearLayoutManager manager;
    private int page = 2;
//    private EndLessOnScrollListener endLessOnScrollListener

    @Override
    protected void initData() {
        feeds = new ArrayList<>();
        adapter = new EvaluatingAdapter(getContext());
        getGsonRequest(url);
        evaluatingRv.setAdapter(adapter);
        manager = new LinearLayoutManager(getContext());
        evaluatingRv.setLayoutManager(manager);
        evaluatingRv.addOnScrollListener( new EndLess(manager) {
            @Override
            protected void onLoadMores(int curentPage) {
                evaluatingSr.setRefreshing(true);
                getGsonRequest("http://food.boohee.com/fb/v1/feeds/category_feed?page="+page+"&category=2&per=10");
                page++;
                evaluatingSr.setRefreshing(false);
            }
        });
        evaluatingSr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
             feeds.clear();
                getGsonRequest(url);
                evaluatingSr.setRefreshing(false);
            }
        });

    }
    @Override
    protected void initView() {
        evaluatingSr = bindView(R.id.evaluatingSr);
        evaluatingRv = bindView(R.id.evaluatingRv);

    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_evaluating;
    }
    protected void getGsonRequest(String url) {
        GsonRequest<EvaluatingBean> gsonRequest = new GsonRequest<EvaluatingBean>(EvaluatingBean.class, url, new Response.Listener<EvaluatingBean>() {
            @Override
            public void onResponse(EvaluatingBean response) {
                feeds = response.getFeeds();
                adapter.setArrayList(feeds);
//                endLessOnScrollListener.resetPreviousTotal();
//                evaluatingSr.setRefreshing(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);


    }
}

