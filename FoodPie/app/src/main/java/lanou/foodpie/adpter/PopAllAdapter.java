package lanou.foodpie.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lanou.foodpie.R;
import lanou.foodpie.bean.FoodDataBean;

/**
 * Created by ZhangRui on 16/11/9.
 */
public class PopAllAdapter extends RecyclerView.Adapter<PopAllAdapter.ViewHolder>{
    private Context context;
    private List<FoodDataBean.GroupBean.CategoriesBean.SubCategoriesBean> beanList;

    public void setBeanList(List<FoodDataBean.GroupBean.CategoriesBean.SubCategoriesBean> beanList) {
        this.beanList = beanList;
        notifyDataSetChanged();
    }

    public PopAllAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_popall,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.popAllItemTv.setText(beanList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  TextView popAllItemTv;

        public ViewHolder(View itemView) {
            super(itemView);
            popAllItemTv = (TextView) itemView.findViewById(R.id.popAllItemTv);
        }
    }
}
