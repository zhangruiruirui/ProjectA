package lanou.foodpie.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import lanou.foodpie.R;
import lanou.foodpie.abs.BaseFragment;
import lanou.foodpie.adpter.ToEatAdapter;

/**
 * Created by ZhangRui on 16/10/21.
 * 逛吃Fragment
 */
public class ToEatFragment extends BaseFragment {

    private TabLayout toEatTb;
    private ViewPager toEatVp;

    @Override
    protected int getLayout() {
        return R.layout.fragment_toeat;
    }

    @Override
    protected void initView() {
        toEatTb = bindView(R.id.tbToEat);
        toEatVp = bindView(R.id.vpToEat);
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new HomePagerFragment());
        arrayList.add(new EvaluatingFragment());
        arrayList.add(new KnowledgeFragment());
        arrayList.add(new DeliciousFoodFragment());

        ToEatAdapter adapter = new ToEatAdapter(getChildFragmentManager());
        toEatVp.setAdapter(adapter);
        adapter.setFragments(arrayList);
        adapter.notifyDataSetChanged();
        toEatTb.setupWithViewPager(toEatVp);
    }
}
