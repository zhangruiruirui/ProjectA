package lanou.foodpie.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import lanou.foodpie.R;
import lanou.foodpie.abs.BaseFragment;
import lanou.foodpie.abs.EndLessOnScrollListener;
import lanou.foodpie.activity.HomePagerDetailsActivity;
import lanou.foodpie.activity.HomePagerLinkActivity;
import lanou.foodpie.adpter.HomePagerAdapter;
import lanou.foodpie.bean.HomeDataBean;
import lanou.foodpie.onclickItemlistener.OnClickHomePagerDetails;
import lanou.foodpie.web.GsonRequest;
import lanou.foodpie.constant.UrlWeb;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/25.
 * 首页fragment
 */
public class HomePagerFragment extends BaseFragment implements OnClickHomePagerDetails {
    private RecyclerView rcHomePager;
    private SwipeRefreshLayout srHome;
    private String url = UrlWeb.urlHomePager;
    private String urlPage = UrlWeb.urlHomePagerPage;
    private String urlPages = UrlWeb.urlHomePagerPages;
    private StaggeredGridLayoutManager manager;
    private int page = 2;
    private HomePagerAdapter homePagerAdapter;
    private EndLessOnScrollListener endLessOnScrollListener;

    @Override
    protected void initData() {
//        arrayList = new ArrayList<>();
        homePagerAdapter = new HomePagerAdapter(getContext());
        getGsonRequest(url, true);
        manager = new StaggeredGridLayoutManager
                (2, StaggeredGridLayoutManager.VERTICAL);
        rcHomePager.setLayoutManager(manager);
        rcHomePager.setAdapter(homePagerAdapter);
        rcHomePager.addOnScrollListener(endLessOnScrollListener = new
                EndLessOnScrollListener(manager) {
                    @Override
                    protected void onLoadMore(int curentPage) {
                        srHome.setRefreshing(true);
                        getGsonRequest(urlPage + page + urlPages, false);
                        page++;
                        srHome.setRefreshing(false);
                    }
                });

        srHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                arrayList.clear();
                getGsonRequest(url, true);
            }
        });
        Log.d("HomePagerFragment", "homePagerAdapter:" + homePagerAdapter);
        homePagerAdapter.setOnClickHomePagerDetails(this);
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

    protected void getGsonRequest(String url, final boolean isRefresh) {
        GsonRequest<HomeDataBean> gsonRequest = new
                GsonRequest<HomeDataBean>(HomeDataBean.class, url, new
                Response.Listener<HomeDataBean>() {
                    @Override
                    public void onResponse(HomeDataBean response) {
                        Log.d("HomePagerFragment", "response:" + response);

//                Log.d("HomePagerFragment", "arrayList:" + arrayList);
                        homePagerAdapter.setArrayList(response.getFeeds(), isRefresh);
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

    @Override
    public void onClickHomePager(String Image, String name, String headIcon, int Like_ct) {
        Intent intent = new Intent(getActivity(), HomePagerDetailsActivity.class);
        intent.putExtra("image", Image);
        intent.putExtra("name", name);
        intent.putExtra("headIcon", headIcon);
        intent.putExtra("Like_ct", Like_ct);
        startActivity(intent);
    }

    @Override
    public void onPictureClick(String link) {
        Intent intent = new Intent(getActivity(), HomePagerLinkActivity.class);
        intent.putExtra("Link", link);
        Log.d("HomePagerFragment", link);

        startActivity(intent);

    }
}
