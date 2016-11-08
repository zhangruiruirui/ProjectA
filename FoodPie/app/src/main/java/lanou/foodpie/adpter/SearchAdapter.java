package lanou.foodpie.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lanou.foodpie.R;
import lanou.foodpie.bean.SearchBean;

/**
 * Created by ZhangRui on 16/11/8.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<SearchBean.TypesBean> beanList;

    public void setBeanList(List<SearchBean.TypesBean> beanList) {
        this.beanList = beanList;
    }

    public SearchAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, int position) {
        holder.nameTv.setText(beanList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  TextView nameTv;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.nameTv);
        }
    }
}
