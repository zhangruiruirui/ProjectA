package lanou.foodpie.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import lanou.foodpie.R;
import lanou.foodpie.abs.BaseFragment;
import lanou.foodpie.abs.EndLessOnScrollListener;
import lanou.foodpie.adpter.DeliciousFoodAdapter;
import lanou.foodpie.bean.DeliciousFoodDataBean;
import lanou.foodpie.web.GsonRequest;
import lanou.foodpie.constant.UrlWeb;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/25.
 * 美食fragment
 */
public class DeliciousFoodFragment extends BaseFragment {
    private SwipeRefreshLayout deliciousSr;
    private RecyclerView deliciousRv;
    private int page = 1;
    private String url = UrlWeb.urlDelicious;
    private String urlPage = UrlWeb.urlDeliciousPage;
    private String urlPages = UrlWeb.urlDeliciousPages;
    private DeliciousFoodAdapter deliciousFoodAdapter;
    private StaggeredGridLayoutManager manager;
    private EndLessOnScrollListener endLessOnScrollListener;

    @Override
    protected void initData() {
//        arrayList = new ArrayList<>();
        deliciousFoodAdapter = new DeliciousFoodAdapter(getContext());
        getGsonRequest(url,true);
        manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        deliciousRv.setLayoutManager(manager);
        deliciousRv.setAdapter(deliciousFoodAdapter);
        deliciousRv.addOnScrollListener(endLessOnScrollListener = new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                deliciousSr.setRefreshing(true);
                getGsonRequest(urlPage+page+urlPages,false);
                page++;
                deliciousSr.setRefreshing(false);
            }
        });

        deliciousSr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                arrayList.clear();
                getGsonRequest(url,true);

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

    protected void getGsonRequest(String url, final boolean isRefresh) {
        GsonRequest<DeliciousFoodDataBean> gsonRequest = new
                GsonRequest<DeliciousFoodDataBean>(DeliciousFoodDataBean.class, url, new
                Response.Listener<DeliciousFoodDataBean>() {
            @Override
            public void onResponse(DeliciousFoodDataBean response) {
//                arrayList = response.getFeeds();
                deliciousFoodAdapter.setArrayList(response.getFeeds(),isRefresh);
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
