package lanou.foodpie.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import lanou.foodpie.R;
import lanou.foodpie.abs.BaseFragment;
import lanou.foodpie.abs.EndLessOnScrollListener;
import lanou.foodpie.adpter.KnowledgeAdapter;
import lanou.foodpie.bean.KnowledgeDataBean;
import lanou.foodpie.web.GsonRequest;
import lanou.foodpie.constant.UrlWeb;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/25.
 * 知识fragment
 */
public class KnowledgeFragment extends BaseFragment {
    private SwipeRefreshLayout knowSr;
    private RecyclerView knowRc;
    private int page = 1;
    private String url = UrlWeb.urlKnow;
    private String urlPage = UrlWeb.urlKnowPage;
    private String urlPages = UrlWeb.urlKnowPages;
    private KnowledgeAdapter knowledgeAdapter;
    private StaggeredGridLayoutManager manager;
    private EndLessOnScrollListener endLessOnScrollListener;
    @Override
    protected void initData() {
//        arrayList = new ArrayList<>();
        knowledgeAdapter = new KnowledgeAdapter(getContext());
        getGsonRequest(url,true);
        manager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        knowRc.setLayoutManager(manager);
        knowRc.setAdapter(knowledgeAdapter);
        knowRc.addOnScrollListener(endLessOnScrollListener = new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                knowSr.setRefreshing(true);
                getGsonRequest(urlPage+page+urlPages,false);
                page++;
                knowSr.setRefreshing(false);
            }
        });

        knowSr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                arrayList.clear();
                getGsonRequest(url,true);
                knowSr.setRefreshing(false);
            }
        });

    }

    @Override
    protected void initView() {
        knowSr = bindView(R.id.knowSr);
        knowRc = bindView(R.id.knowRc);


    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_knowledge;
    }
    protected void getGsonRequest(String uri,final boolean isRefresh) {
        GsonRequest<KnowledgeDataBean> gsonRequest = new
                GsonRequest<KnowledgeDataBean>(KnowledgeDataBean.class, uri, new
                Response.Listener<KnowledgeDataBean>() {
            @Override
            public void onResponse(KnowledgeDataBean response) {
                knowledgeAdapter.setArrayList(response.getFeeds(),isRefresh);
                endLessOnScrollListener.resetPreviousTotal();
                knowSr.setRefreshing(false);
                }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}
