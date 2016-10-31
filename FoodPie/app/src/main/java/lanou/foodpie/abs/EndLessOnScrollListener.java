package lanou.foodpie.abs;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by dllo on 16/10/31.
 */
public  abstract  class EndLessOnScrollListener extends RecyclerView.OnScrollListener{
    
    private LinearLayoutManager linearLayoutManager;
    private  int curentPage = 0;
    
    //已经加载的Item数量
    private int totallItemCount;
    
    //主要用来 存储上一个totallItemCount
    private int previousTotal = 0;
    
    //在欧屏上可见的item数量
    private int visibleItemCount;
    
    //在屏幕上可见的额item中的第一个
    private int firstVisibleItem;
    
    //是否上拉数据
    private boolean loading  = true;

    public EndLessOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totallItemCount = linearLayoutManager.getItemCount();
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
        
        if (loading){
            if (totallItemCount > previousTotal){
                //说明加载结束
                loading = false;
                previousTotal = totallItemCount;
                
            }
        }
        if (!loading && totallItemCount - visibleItemCount <= firstVisibleItem){
            curentPage ++;
            onLoadMore(curentPage);
            loading = true;
        }
        
        
        
        
        
        
        
    }

    protected abstract void onLoadMore(int curentPage);
}
