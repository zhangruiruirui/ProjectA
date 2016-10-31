package lanou.foodpie.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.foodpie.R;
import lanou.foodpie.bean.FoodEncyclopediasBean;
import lanou.foodpie.abs.MyApp;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/28.
 */
public class FoodEncyclopediasAdapter extends BaseAdapter {
    ArrayList<FoodEncyclopediasBean> arrayList;

    public void setArrayList(ArrayList<FoodEncyclopediasBean> arrayList) {
        this.arrayList = arrayList;
         notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int count = arrayList == null? 0 :arrayList.size();
        //   Log.d("GridViewAdapter", "count:" + count);
        return arrayList == null? 0 :arrayList.size();

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
        MyyViewHolder viewHolder = null;
        if (convertView == null ){
            convertView = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.item_foodencyclopedias,parent,false);
            viewHolder = new MyyViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (MyyViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(arrayList.get(position).getName());
        VolleySingleton.getInstance().getImage(arrayList.get(position).getImageUrl(),viewHolder.imageView);



        return convertView;
    }


    private class MyyViewHolder {


        private final ImageView imageView;
        private final TextView textView;

        public MyyViewHolder(View convertView) {

            imageView = (ImageView) convertView.findViewById(R.id.foodItemIv);
            textView = (TextView) convertView.findViewById(R.id.foodItemTv);

        }
    }
}
