package lanou.foodpie.fragment;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import lanou.foodpie.R;
import lanou.foodpie.abs.BaseFragment;
import lanou.foodpie.adpter.HomePagerAdapter;
import lanou.foodpie.bean.HomeDataBean;
import lanou.foodpie.bean.HomePagerBean;
import lanou.foodpie.gson.GsonRequest;
import lanou.foodpie.gson.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/25.
 * 首页fragment
 */
public class HomePagerFragment extends BaseFragment {
    private PullToRefreshListView pullHomePager;
    private HomePagerAdapter homePagerAdapter;
    private ArrayList<HomePagerBean> arrayList;
    private String uri = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=1&per=10";

    @Override
    protected void initData() {
        pullHomePager.setMode(PullToRefreshBase.Mode.BOTH);
        pullHomePager.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                GsonRequest<HomeDataBean> gsonRequest = new GsonRequest<HomeDataBean>(HomeDataBean.class, uri, new Response.Listener<HomeDataBean>() {
                    @Override
                    public void onResponse(HomeDataBean response) {
                        for (int i = 0; i < response.getFeeds().size(); i++) {
                            HomePagerBean bean = new HomePagerBean();
                            bean.setImgUrl(response.getFeeds().get(i).getCard_image());
                            bean.setTitle(response.getFeeds().get(i).getTitle());
                            bean.setTitle(response.getFeeds().get(i).getPublisher());
                            arrayList.add(bean);

                        }
                        homePagerAdapter.setArrayList(arrayList);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequest);
                pullHomePager.setAdapter(homePagerAdapter);


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                GsonRequest<HomeDataBean> gsonRequest = new GsonRequest<HomeDataBean>(HomeDataBean.class, uri, new Response.Listener<HomeDataBean>() {
                    @Override
                    public void onResponse(HomeDataBean response) {
                        for (int i = 0; i < response.getFeeds().size(); i++) {
                            HomePagerBean bean = new HomePagerBean();
                            bean.setImgUrl(response.getFeeds().get(i).getCard_image());
                            bean.setTitle(response.getFeeds().get(i).getTitle());
                            bean.setTitle(response.getFeeds().get(i).getPublisher());
                            arrayList.add(bean);

                        }
                        homePagerAdapter.setArrayList(arrayList);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequest);
                pullHomePager.setAdapter(homePagerAdapter);


            }
        });

    }

    @Override
    protected void initView() {
        pullHomePager = bindView(R.id.pullHomePager);
        // 绑定适配器
        homePagerAdapter = new HomePagerAdapter(getContext());
        pullHomePager.setAdapter(homePagerAdapter);
        arrayList = new ArrayList<>();




    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_homepager;
    }






    }

