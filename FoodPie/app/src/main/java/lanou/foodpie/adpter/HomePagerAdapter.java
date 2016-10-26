package lanou.foodpie.adpter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import lanou.foodpie.R;
import lanou.foodpie.bean.HomePagerBean;

/**
 * Created by ZhangRui on 16/10/26.
 * // 首页适配器
 */
public class HomePagerAdapter extends BaseAdapter {
    // 获取上下文构造和数据类setter方法
    Context context;
    ArrayList<HomePagerBean> arrayList;

    public void setArrayList(ArrayList<HomePagerBean> arrayList) {
        this.arrayList = arrayList;
    }

    public HomePagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_homepager,null);
            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else  {
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        viewHolder.tvItemHome.setText(arrayList.get(position).getTitle());
        Picasso.with(context).load(arrayList.get(position).getImgUrl()).into(viewHolder.ivItemHome);

        return convertView;
    }

    private class MyViewHolder {

        private final TextView tvItemHome;
        private final ImageView ivItemHome;

        public MyViewHolder(View convertView) {
            tvItemHome = (TextView) convertView.findViewById(R.id.tvItemHome);
            ivItemHome = (ImageView) convertView.findViewById(R.id.ivItemHome);
        }
    }
}
