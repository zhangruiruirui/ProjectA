package lanou.foodpie.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

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
    private HomePagerAdapter homePagerAdapter;
    private SwipeRefreshLayout srHome;
    private String uri = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=1&per=10";
    private ArrayList<HomeDataBean> arrayList;
    private HomeDataBean bean1;
    private ArrayList<HomePagerBean> arrayList1;
    private LinearLayoutManager manager;

    @Override
    protected void initData() {
//        bean1 = new HomeDataBean();
//        arrayList = new ArrayList<>();
//        arrayList.add(bean1);
        initView();


        rcHomePager.addOnScrollListener(new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
//                arrayList.add(bean1);
                homePagerAdapter.notifyDataSetChanged();


            }
        });
        srHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                arrayList.add(bean1);
                homePagerAdapter.notifyDataSetChanged();
                srHome.setRefreshing(false);
            }
        });
    }

    @Override
    protected void initView() {
        arrayList1 = new ArrayList<>();
        rcHomePager = bindView(R.id.homePageRc);
        srHome = bindView(R.id.homeSr);
        manager = new LinearLayoutManager(getContext());
        rcHomePager.setLayoutManager(manager);
        // 绑定适配器
        homePagerAdapter = new HomePagerAdapter(getContext());
        rcHomePager.setAdapter(homePagerAdapter);
        srHome.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        srHome.setColorSchemeColors(0xFF00FFFF);
        GsonRequest<HomeDataBean> gsonRequest = new GsonRequest<HomeDataBean>(HomeDataBean.class, uri, new Response.Listener<HomeDataBean>() {
            @Override
            public void onResponse(HomeDataBean response) {

                for (int i = 0; i < response.getFeeds().size(); i++) {
                    HomePagerBean bean = new HomePagerBean();
                    bean.setImgUrl(response.getFeeds().get(i).getCard_image());
//                    bean.setTitle(response.getFeeds().get(i).getLink());
                    arrayList1.add(bean);
                    homePagerAdapter.setArrayList(arrayList1);
                    rcHomePager.setAdapter(homePagerAdapter);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);

    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_homepager;
    }
    }
