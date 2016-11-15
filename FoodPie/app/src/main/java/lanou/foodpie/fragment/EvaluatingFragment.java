package lanou.foodpie.fragment;

import android.content.Intent;
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
import lanou.foodpie.abs.EndLessOnScrollListener;
import lanou.foodpie.activity.EvaDetailsActivity;
import lanou.foodpie.adpter.EvaluatingAdapter;
import lanou.foodpie.bean.EvaluatingBean;
import lanou.foodpie.onclickItemlistener.OnClickEva;
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
    private String urlPage = UrlWeb.urlEvaPage;
    private String urlPages = UrlWeb.urlEvaPages;
    //    private List<EvaluatingBean.FeedsBean> feeds;
    private EvaluatingAdapter adapter;
    private LinearLayoutManager manager;
    private int page = 1;
    private EndLessOnScrollListener endLessOnScrollListener;

    @Override
    protected void initData() {
//        feeds = new ArrayList<>();
        adapter = new EvaluatingAdapter(getContext());
        getGsonRequest(url, true);
        evaluatingRv.setAdapter(adapter);
        manager = new LinearLayoutManager(getContext());
        evaluatingRv.setLayoutManager(manager);
        evaluatingRv.addOnScrollListener(endLessOnScrollListener = new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                evaluatingSr.setRefreshing(true);
                getGsonRequest(urlPage + page + urlPages, false);
                page++;
                evaluatingSr.setRefreshing(false);
            }
        });
        evaluatingSr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                feeds.clear();
                getGsonRequest(url, true);
                evaluatingSr.setRefreshing(false);
            }
        });
        adapter.setOnClickEva(new OnClickEva() {
            @Override
            public void onClickEva(String link) {
                Intent intent = new Intent(getActivity(), EvaDetailsActivity.class);
                intent.putExtra("link",link);
                startActivity(intent);

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

    protected void getGsonRequest(String url, final boolean isRefresh) {
        GsonRequest<EvaluatingBean> gsonRequest = new
                GsonRequest<EvaluatingBean>(EvaluatingBean.class, url, new
                Response.Listener<EvaluatingBean>() {
                    @Override
                    public void onResponse(EvaluatingBean response) {
//               response.getFeeds();
                        adapter.setArrayList(response.getFeeds(), isRefresh);
                        endLessOnScrollListener.resetPreviousTotal();
                        evaluatingSr.setRefreshing(false);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);


    }

}