package lanou.foodpie.adpter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import lanou.foodpie.R;
import lanou.foodpie.bean.FoodSortBean;
import lanou.foodpie.constant.CircleDrawable;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/11/5.
 */
public class FoodSorAdapter extends BaseAdapter {
    private List<FoodSortBean.FoodsBean> foodsBeen;
    private Context context;

    public FoodSorAdapter(Context context) {
        this.context = context;
    }

    public void setFoodsBeen(List<FoodSortBean.FoodsBean> foodsBeen) {
        this.foodsBeen = foodsBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return foodsBeen == null ? 0 : foodsBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return foodsBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder = null;
        if (convertView == null ){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_food,parent,false);
            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        viewHolder.typeTv.setText(foodsBeen.get(position).getCalory());
        viewHolder.nameTv.setText(foodsBeen.get(position).getName());
        VolleySingleton.getInstance().getImage(foodsBeen.
                get(position).getThumb_image_url(),viewHolder.iconIv);
        return convertView;
    }

    private class MyViewHolder {

        private  ImageView iconIv;
        private  TextView typeTv;
        private  TextView nameTv;

        public MyViewHolder(View convertView) {
            iconIv = (ImageView) convertView.findViewById(R.id.iconIv);
            typeTv = (TextView) convertView.findViewById(R.id.typeTv);
            nameTv = (TextView) convertView.findViewById(R.id.nameTv);


        }
    }
}
