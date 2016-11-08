package lanou.foodpie.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by ZhangRui on 16/10/24.
 * 逛吃适配器
 */
public class ToEatAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private String titles[] = {"首页", "测评", "知识", "美食"};

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    public ToEatAdapter(FragmentManager fm) {
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

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
