package chailei.com.expandablerecyclerviewapp;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by Administrator on 16-1-5.
 */
public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {

    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView recyclerView = new RecyclerView(context,attrs);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        //?????????????????????????????????????
        recyclerView.setId(R.id.ptr_recycler);
        return recyclerView;
    }

    @Override
    protected boolean isReadyForPullStart() {
        RecyclerView recycler = getRefreshableView();
        View child = recycler.getChildAt(0);
        int position = recycler.getChildAdapterPosition(child);
        if(position == 0){
            return child.getTop() == 0;
        }
        return false;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        return false;
    }

}
