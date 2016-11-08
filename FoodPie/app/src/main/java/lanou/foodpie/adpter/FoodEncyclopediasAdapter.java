package lanou.foodpie.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import lanou.foodpie.R;
import lanou.foodpie.bean.FoodDataBean;
import lanou.foodpie.abs.MyApp;
import lanou.foodpie.onclickItemlistener.OnClickFoodSor;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/28.
 */
public class FoodEncyclopediasAdapter extends BaseAdapter {

    private FoodDataBean foodDataBean;
    private int kind = 2;
    private OnClickFoodSor onClickFoodSor;

    public void setFoodDataBean(int kind, FoodDataBean foodDataBean) {
        this.foodDataBean = foodDataBean;
        this.kind = kind;
    }

    public void setonClickFoodSor(OnClickFoodSor OnClickFoodSor) {
        this.onClickFoodSor = OnClickFoodSor;
    }

    @Override
    public int getCount() {
        return foodDataBean.getGroup().get(kind).getCategories() == null? 0 :foodDataBean.getGroup().get(kind).getCategories().size();

    }

    @Override
    public Object getItem(int position) {
        return foodDataBean.getGroup().get(kind).getCategories();

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder = null;
        if (convertView == null ){
            convertView = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.item_foodencyclopedias,parent,false);
            viewHolder = new MyViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        viewHolder.foodItemTv.setText(foodDataBean.
                getGroup().get(kind).getCategories().get(position).getName());
        VolleySingleton.getInstance().getImage(foodDataBean.
                getGroup().get(kind).getCategories().get(position).getImage_url(),viewHolder.foodItemIv);
        viewHolder.foodLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickFoodSor.onClickFood(foodDataBean.getGroup().get(kind).getKind(),
                foodDataBean.getGroup().get(kind).getCategories().get(position).getId(),
                foodDataBean.getGroup().get(kind).getCategories().get(position).getName());
            }
        });
        return convertView;
    }


    private class MyViewHolder {
        private  ImageView foodItemIv;
        private  TextView foodItemTv;
        private  LinearLayout foodLl;

        public MyViewHolder(View convertView) {

            foodItemIv = (ImageView) convertView.findViewById(R.id.foodItemIv);
            foodItemTv = (TextView) convertView.findViewById(R.id.foodItemTv);
            foodLl = (LinearLayout) convertView.findViewById(R.id.foodLl);

        }
    }
}
