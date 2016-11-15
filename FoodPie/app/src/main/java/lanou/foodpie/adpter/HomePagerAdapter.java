package lanou.foodpie.adpter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import lanou.foodpie.R;
import lanou.foodpie.bean.HomeDataBean;
import lanou.foodpie.onclickItemlistener.OnClickHomePagerDetails;
import lanou.foodpie.web.VolleySingleton;

/**
 * Created by ZhangRui on 16/10/26.
 * // 首页适配器
 */
public class HomePagerAdapter extends RecyclerView.Adapter<HomePagerAdapter.ViewHolder> {
    private Context context;
    private static final int TYPE_LINK_IMG = 6;
    private static final int TYPE_FOOD_IMG = 5;
    private List<HomeDataBean.FeedsBean> arrayList;
    private OnClickHomePagerDetails onClickHomePagerDetails;

    public void setOnClickHomePagerDetails(OnClickHomePagerDetails onClickHomePagerDetails) {
        this.onClickHomePagerDetails = onClickHomePagerDetails;
    }


    public HomePagerAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(List<HomeDataBean.FeedsBean> arrayList, boolean isRefresh) {
        if (isRefresh || arrayList == null) {
            this.arrayList = arrayList;
        } else {
            this.arrayList.addAll(arrayList);
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_FOOD_IMG:
                View homePagerView = LayoutInflater.from(context).inflate
                        (R.layout.item_homepager, parent, false);
                ViewHolder myViewHolder = new ViewHolder(homePagerView);
                return myViewHolder;
            case TYPE_LINK_IMG:
                View detailsView = LayoutInflater.from(context).inflate
                        (R.layout.item_homepagerdetails, parent, false);
                ViewHolder viewHolder = new ViewHolder(detailsView);
                return viewHolder;

        }
        return null;
    }


    @Override
    public int getItemViewType(int position) {
        Log.d("HomePagerAdapter", "aa" + arrayList.get(position).getContent_type());
        return arrayList.get(position).getContent_type();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int Content_type = getItemViewType(position);
        Log.d("HomePagerAdapter", "type:" + Content_type);
        switch (Content_type) {
            case TYPE_FOOD_IMG:
                VolleySingleton.getInstance().getImage(arrayList.get(position).getCard_image(), holder.cardIv);
                VolleySingleton.getInstance().getImage(arrayList.get(position).getPublisher_avatar(), holder.publisher_avatarIv);
                holder.titleTv.setText(arrayList.get(position).getTitle());
                holder.publisher.setText(arrayList.get(position).getPublisher());
                holder.likeTv.setText(String.valueOf(arrayList.get(position).getLike_ct()));
                holder.descriptionTv.setText(arrayList.get(position).getDescription());

                holder.homepageRl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String image = arrayList.get(position).getCard_image();
                        String name = arrayList.get(position).getTitle();
                        int like = arrayList.get(position).getLike_ct();
                        String head = arrayList.get(position).getPublisher();
                        onClickHomePagerDetails.onClickHomePager(image, name, head, like);
                    }
                });
                break;
            case TYPE_LINK_IMG:
                VolleySingleton.getInstance().getImage(arrayList.get(position).getCard_image(), holder.linkIv);
                holder.ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String link = arrayList.get(position).getLink();
                        onClickHomePagerDetails.onPictureClick(link);

                    }
                });
                break;

        }


    }


    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView cardIv;
        private TextView titleTv;
        private TextView typeTv;
        private TextView publisher;
        private LinearLayout ll;
        private RelativeLayout homepageRl;
        private ImageView publisher_avatarIv;
        private ImageView linkIv;
        private TextView descriptionTv;
        private TextView likeTv;

        public ViewHolder(View itemView) {
            super(itemView);
            cardIv = (ImageView) itemView.findViewById(R.id.cardIv);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            typeTv = (TextView) itemView.findViewById(R.id.typeTv);
            publisher_avatarIv = (ImageView) itemView.findViewById(R.id.publisher_avatarIv);
            publisher = (TextView) itemView.findViewById(R.id.publisher);
            linkIv = (ImageView) itemView.findViewById(R.id.linkIv);
            likeTv = (TextView) itemView.findViewById(R.id.likeTv);
            descriptionTv = (TextView) itemView.findViewById(R.id.descriptionTv);

            homepageRl = (RelativeLayout) itemView.findViewById(R.id.homepageRl);
            ll = (LinearLayout) itemView.findViewById(R.id.ll_homepage);
        }
    }
}
