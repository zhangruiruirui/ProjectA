package lanou.foodpie.adpter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import lanou.foodpie.R;
import lanou.foodpie.bean.HomeDataBean;
import lanou.foodpie.bean.HomePagerBean;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/26.
 * // 首页适配器
 */
public class HomePagerAdapter extends RecyclerView.Adapter<HomePagerAdapter.ViewHolder> {
    Context context;
    ArrayList<HomePagerBean> arrayList;

    public HomePagerAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<HomePagerBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate
                (R.layout.item_homepager,parent,false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        VolleySingleton.getInstance().getImage
                (arrayList.get(position).getCard_image(),holder.ivHomeData);

        holder.tvItemHome.setText(arrayList.get(position).getTitle());
//        Log.d("response", arrayList.get(position).getTitle());


    }



    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        private  TextView tvItemHome;
        private  ImageView ivItemHome;
        private  ImageView IvHomeDatas;
        private  ImageView ivHomeData;
        public ViewHolder(View itemView) {
            super(itemView);
            tvItemHome = (TextView) itemView.findViewById(R.id.tvItemHome);
            ivItemHome = (ImageView) itemView.findViewById(R.id.ivItemHome);
            IvHomeDatas = (ImageView) itemView.findViewById(R.id.homeDatasIv);
            ivHomeData = (ImageView) itemView.findViewById(R.id.homeDataIv);
        }
    }
}
