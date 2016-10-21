package lanou.foodpie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ZhangRui on 16/10/21.
 * MainActivity的适配器
 */
public class MyAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments;
    ArrayList<String> title = new ArrayList<>();
    String [] titles = {"食物百科","狂吃","我的"};

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
        for (int i = 0; i < titles.length; i++) {
            title.add(titles[i]);
        }
    }

    public MyAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public MyAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }
}
