package lanou.foodpie.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lanou.foodpie.R;
import lanou.foodpie.bean.DeliciousFoodBean;
import lanou.foodpie.bean.DeliciousFoodDataBean;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/26.
 */
public class DeliciousFoodAdapter extends RecyclerView.Adapter<DeliciousFoodAdapter.ViewHolder> {
    Context context;
    List<DeliciousFoodDataBean.FeedsBean> arrayList;

    public void setArrayList(List<DeliciousFoodDataBean.FeedsBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public DeliciousFoodAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_delicious, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.sourceTv.setText(arrayList.get(position).getSource());
        holder.tailTv.setText(arrayList.get(position).getTail());
        holder.titleTv.setText(arrayList.get(position).getTitle());
        holder.typeTv.setText(arrayList.get(position).getType());
        if (arrayList.get(position).getImages().size() > 0) {
            VolleySingleton.getInstance().getImage((arrayList.get(position).getImages().get(0)), holder.imagesIv);

        }else {
            holder.imagesIv.setImageResource(R.mipmap.img_default_food_thumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView typeTv;
        private TextView sourceTv;
        private TextView titleTv;
        private TextView tailTv;
        private ImageView imagesIv;

        public ViewHolder(View itemView) {
            super(itemView);
            typeTv = (TextView) itemView.findViewById(R.id.typeTv);
            sourceTv = (TextView) itemView.findViewById(R.id.sourceTv);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            tailTv = (TextView) itemView.findViewById(R.id.tailTv);
            imagesIv = (ImageView) itemView.findViewById(R.id.imagesIv);
        }
    }
}
