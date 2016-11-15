package lanou.foodpie.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import lanou.foodpie.R;
import lanou.foodpie.bean.KnowledgeDataBean;
import lanou.foodpie.onclickItemlistener.OnClickKnow;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/26.
 */
public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeAdapter.ViewHolder> {
    private Context context;
    private List<KnowledgeDataBean.FeedsBean> arrayList;
    private OnClickKnow onClickKnow;

    public void setOnClickKnow(OnClickKnow onClickKnow) {
        this.onClickKnow = onClickKnow;
    }

    public void setArrayList(List<KnowledgeDataBean.FeedsBean> arrayList, boolean isRefresh) {
        if (isRefresh || arrayList == null) {
            this.arrayList = arrayList;
        }else {
            this.arrayList.addAll(arrayList);
        }
        notifyDataSetChanged();
    }

    public KnowledgeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_know, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.sourceTv.setText(arrayList.get(position).getSource());
        holder.tailTv.setText(arrayList.get(position).getTail());
        holder.titleTv.setText(arrayList.get(position).getTitle());
        if (arrayList.get(position).getImages().size() > 0) {
            VolleySingleton.getInstance().getImage((arrayList.get(position).getImages().get(0)), holder.imagesIv);

        }else {
            holder.imagesIv.setImageResource(R.mipmap.img_default_food_thumbnail);
        }
        holder.rL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = arrayList.get(position).getLink();
                onClickKnow.onClickKnow(link);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView sourceTv;
        private TextView titleTv;
        private TextView tailTv;
        private ImageView imagesIv;
        private RelativeLayout rL;

        public ViewHolder(View itemView) {
            super(itemView);
            rL = (RelativeLayout) itemView.findViewById(R.id.rL);
            sourceTv = (TextView) itemView.findViewById(R.id.sourceTv);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            tailTv = (TextView) itemView.findViewById(R.id.tailTv);
            imagesIv = (ImageView) itemView.findViewById(R.id.imagesIv);
        }
    }
}
