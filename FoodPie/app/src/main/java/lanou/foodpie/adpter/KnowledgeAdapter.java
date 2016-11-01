package lanou.foodpie.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.foodpie.R;
import lanou.foodpie.bean.KnowledgeBean;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by dllo on 16/10/26.
 */
public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeAdapter.ViewHolder>{
    Context context;
    ArrayList<KnowledgeBean> arrayList;

    public void setArrayList(ArrayList<KnowledgeBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public KnowledgeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_know,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VolleySingleton.getInstance().getImage((arrayList.get(position).getImages().get(0)),holder.imagesIv);
        holder.sourceTv.setText(arrayList.get(position).getSource());
        holder.tailTv.setText(arrayList.get(position).getTail());
        holder.titleTv.setText(arrayList.get(position).getTitle());
        holder.typeTv.setText(arrayList.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView typeTv;
        private final TextView sourceTv;
        private final TextView titleTv;
        private final TextView tailTv;
        private final ImageView imagesIv;

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
