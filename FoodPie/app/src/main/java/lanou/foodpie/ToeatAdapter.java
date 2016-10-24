package lanou.foodpie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class ToeatAdapter extends FragmentPagerAdapter {
     ArrayList<Fragment> arrayList;

    public ToeatAdapter(FragmentManager fm, ArrayList<Fragment> arrayList) {
        super(fm);
        this.arrayList = arrayList;
    }

    public ToeatAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }
}
