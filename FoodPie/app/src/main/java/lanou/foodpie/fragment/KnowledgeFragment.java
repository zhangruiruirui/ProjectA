package lanou.foodpie.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import lanou.foodpie.R;
import lanou.foodpie.abs.BaseFragment;
import lanou.foodpie.abs.EndLessOnScrollListener;
import lanou.foodpie.adpter.KnowledgeAdapter;
import lanou.foodpie.bean.KnowledgeBean;
import lanou.foodpie.bean.KnowledgeDataBean;
import lanou.foodpie.web.GsonRequest;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/25.
 * 知识fragment
 */
public class KnowledgeFragment extends BaseFragment {
    private SwipeRefreshLayout knowSr;
    private RecyclerView knowRc;
    private int page = 1;
    private String url = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=3&per=10";
    private KnowledgeAdapter knowledgeAdapter;
    private ArrayList<KnowledgeBean> arrayList;
    private StaggeredGridLayoutManager manager;
    private EndLessOnScrollListener endLessOnScrollListener;
    @Override
    protected void initData() {
        arrayList = new ArrayList<>();
        knowledgeAdapter = new KnowledgeAdapter(getContext());
        getGsonRequest(url);
        manager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        knowRc.setLayoutManager(manager);
        knowRc.setAdapter(knowledgeAdapter);
        knowRc.addOnScrollListener(endLessOnScrollListener = new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                knowSr.setRefreshing(true);
                getGsonRequest("http://food.boohee.com/fb/v1/feeds/category_feed?page="+page+"&category=3&per=10");
                page++;
                knowSr.setRefreshing(false);
            }
        });

        knowSr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayList.clear();
                getGsonRequest(url);
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
    protected void getGsonRequest(String uri) {
        GsonRequest<KnowledgeDataBean> gsonRequest = new GsonRequest<KnowledgeDataBean>(KnowledgeDataBean.class, uri, new Response.Listener<KnowledgeDataBean>() {
            @Override
            public void onResponse(KnowledgeDataBean response) {
                for (int i = 0; i < response.getFeeds().size(); i++) {
                    KnowledgeBean bean = new KnowledgeBean();
                    bean.setImages(response.getFeeds().get(i).getImages());
                    bean.setTitle(response.getFeeds().get(i).getTitle());
                    bean.setType(response.getFeeds().get(i).getType());
                    bean.setTail(response.getFeeds().get(i).getTail());
                    bean.setSource(response.getFeeds().get(i).getSource());
                    arrayList.add(bean);
                }
                knowledgeAdapter.setArrayList(arrayList);
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
