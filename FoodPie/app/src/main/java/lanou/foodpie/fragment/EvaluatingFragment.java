package lanou.foodpie.fragment;

import android.app.DownloadManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Downloader;

import java.util.ArrayList;

import lanou.foodpie.R;
import lanou.foodpie.abs.BaseFragment;
import lanou.foodpie.abs.EndLessOnScrollListener;
import lanou.foodpie.adpter.EvaluatingAdapter;
import lanou.foodpie.bean.EvaluatingBean;
import lanou.foodpie.bean.EvaluatingDataBean;
import lanou.foodpie.web.GsonRequest;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/25.
 * 评测fragment
 */
public class EvaluatingFragment extends BaseFragment {
    private SwipeRefreshLayout evaluatingSr;
    private RecyclerView evaluatingRv;
    private String url = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=2&per=10";
    private ArrayList<EvaluatingDataBean> arraylist;
    private EvaluatingAdapter adapter;
    private LinearLayoutManager manager;
    private int page = 2;


    @Override
    protected void initData() {
        arraylist = new ArrayList<>();
        adapter = new EvaluatingAdapter(getContext());
        getGsonRequest(url);
        evaluatingRv.setAdapter(adapter);
        manager = new LinearLayoutManager(getContext());
        evaluatingRv.setLayoutManager(manager);
        evaluatingRv.addOnScrollListener(new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                evaluatingSr.setRefreshing(true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                getGsonRequest("http://food.boohee.com/fb/v1/feeds/category_feed?page="+page+"&category=2&per=10");
                page++;
                evaluatingSr.setRefreshing(false);
            }
        });




        evaluatingSr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arraylist.clear();
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
                for (int i = 0; i < response.getFeeds().size(); i++) {
                    EvaluatingDataBean bean = new EvaluatingDataBean();
                    bean.setBackground(response.getFeeds().get(i).getBackground());
                    bean.setTitle(response.getFeeds().get(i).getTitle());
                    bean.setSource(response.getFeeds().get(i).getSource());
                    bean.setTail(response.getFeeds().get(i).getTail());
                    arraylist.add(bean);


                }
                adapter.setArrayList(arraylist);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);


    }
}

