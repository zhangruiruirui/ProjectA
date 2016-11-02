package lanou.foodpie.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.animation.Animation;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import lanou.foodpie.R;
import lanou.foodpie.abs.BaseFragment;
import lanou.foodpie.abs.EndLessOnScrollListener;
import lanou.foodpie.adpter.HomePagerAdapter;
import lanou.foodpie.bean.HomeDataBean;
import lanou.foodpie.bean.HomePagerBean;
import lanou.foodpie.web.GsonRequest;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/25.
 * 首页fragment
 */
public class HomePagerFragment extends BaseFragment {
    private RecyclerView rcHomePager;
    private SwipeRefreshLayout srHome;
    private String uri = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=1&per=10";
    private ArrayList<HomePagerBean> arrayList;
    private StaggeredGridLayoutManager manager;
    private int page = 2;
    private HomePagerAdapter homePagerAdapter;
    private EndLessOnScrollListener endLessOnScrollListener;
    @Override
    protected void initData() {
        arrayList = new ArrayList<>();
        homePagerAdapter = new HomePagerAdapter(getContext());
        getGsonRequest(uri);
        manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rcHomePager.setLayoutManager(manager);
        rcHomePager.setAdapter(homePagerAdapter);
        rcHomePager.addOnScrollListener(endLessOnScrollListener = new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                srHome.setRefreshing(true);
                getGsonRequest("http://food.boohee.com/fb/v1/feeds/category_feed?page="+page+"&category=1&per=10");
                page++;
                srHome.setRefreshing(false);
            }
        });

        srHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayList.clear();
                getGsonRequest(uri);

            }
        });
    }

    @Override
    protected void initView() {
        rcHomePager = bindView(R.id.homePageRc);
        srHome = bindView(R.id.homeSr);

    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_homepager;
    }
    protected void getGsonRequest(String uri) {
        GsonRequest<HomeDataBean> gsonRequest = new GsonRequest<HomeDataBean>(HomeDataBean.class, uri, new Response.Listener<HomeDataBean>() {
            @Override
            public void onResponse(HomeDataBean response) {

                for (int i = 0; i < response.getFeeds().size(); i++) {
                    HomePagerBean bean = new HomePagerBean();
                    bean.setCard_image(response.getFeeds().get(i).getCard_image());
                    bean.setTitle(response.getFeeds().get(i).getTitle());
                    bean.setType(response.getFeeds().get(i).getType());
                    bean.setDescription(response.getFeeds().get(i).getDescription());
                    arrayList.add(bean);
                }
                homePagerAdapter.setArrayList(arrayList);
                endLessOnScrollListener.resetPreviousTotal();
                srHome.setRefreshing(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
    }
