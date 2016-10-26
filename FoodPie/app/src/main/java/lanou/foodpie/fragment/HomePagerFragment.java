package lanou.foodpie.fragment;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.ListView;

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
import lanou.foodpie.bean.HomePagerBean;

/**
 * Created by ZhangRui on 16/10/25.
 * 首页fragment
 */
public class HomePagerFragment extends BaseFragment {
    private PullToRefreshListView pullHomePager;
    private HomePagerAdapter homePagerAdapter;
    private ArrayList<HomePagerBean> arrayList;
    private String uri;

    @Override
    protected void initData() {
        pullHomePager.setMode(PullToRefreshBase.Mode.BOTH);
        pullHomePager.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                arrayList.clear();
                HomePagerAsyncTask homePagerAsyncTask = new HomePagerAsyncTask();
                homePagerAsyncTask.execute(uri);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                HomePagerAsyncTask homePagerAsyncTask = new HomePagerAsyncTask();
                homePagerAsyncTask.execute("http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=1&per=10");

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
        HomePagerAsyncTask homePagerAsyncTask = new HomePagerAsyncTask();
        uri = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=1&per=10";
        homePagerAsyncTask.execute(uri);

    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_homepager;
    }
    class HomePagerAsyncTask extends AsyncTask<String,Integer,ArrayList<HomePagerBean>> {
        @Override
        protected ArrayList<HomePagerBean> doInBackground(String... params) {
            //解析
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStream is = connection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(is);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String line = "";
                    String result = new String();
                    while((line =bufferedReader.readLine())!= null){
                        result += line;
                    }
                    JSONObject object = new JSONObject(result);
                    //抓接口网址 JSON 解析出来的一条
                    if (object.has("T1348647909107")){
                        JSONArray array = object.getJSONArray("T1348647909107");
                        //把每一条拿出来解析 放到个集合中
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object1 = (JSONObject) array.get(i);
                            HomePagerBean bean = new HomePagerBean();
                            if (object1.has("title")){
                                bean.setTitle(object1.getString("title"));
                            }
                            if (object1.has("imgsrc")){
                                bean.setImgUrl(object1.getString("imgsrc"));
                            }
                            arrayList.add(bean);

                        }
                    }
                    is.close();
                    reader.close();
                    bufferedReader.close();
                    connection.disconnect();
                }



            } catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return arrayList;
        }

        //拿到集合
        //加网络权限


        @Override
        protected void onPostExecute(ArrayList<HomePagerBean> headBeen) {
            homePagerAdapter.setArrayList(headBeen);
            homePagerAdapter.notifyDataSetChanged();
            pullHomePager.onRefreshComplete();

        }
    }
}
