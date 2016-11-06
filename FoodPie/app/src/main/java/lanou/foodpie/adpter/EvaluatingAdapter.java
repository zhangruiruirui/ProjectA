package lanou.foodpie.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import lanou.foodpie.R;
import lanou.foodpie.bean.EvaluatingBean;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/26.
 */
public class EvaluatingAdapter extends RecyclerView.Adapter<EvaluatingAdapter.ViewHolder> {
    Context context;
    private List<EvaluatingBean.FeedsBean> arrayList;

    public void setArrayList(List<EvaluatingBean.FeedsBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public EvaluatingAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_evaluating, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VolleySingleton.getInstance().getImage(arrayList.get(position).getBackground(), holder.backIv);
        holder.sourceTv.setText(arrayList.get(position).getSource());
        holder.titleTv.setText(arrayList.get(position).getTitle());
        holder.tailTv.setText(arrayList.get(position).getTail());

    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView backIv;
        private final TextView sourceTv;
        private final TextView titleTv;
        private final TextView tailTv;

        public ViewHolder(View itemView) {
            super(itemView);
            backIv = (ImageView) itemView.findViewById(R.id.backIv);
            sourceTv = (TextView) itemView.findViewById(R.id.sourceTv);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            tailTv = (TextView) itemView.findViewById(R.id.tailTv);

        }
    }
}
