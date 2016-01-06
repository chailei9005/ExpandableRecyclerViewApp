package chailei.com.expandablerecyclerviewapp.adapters;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;

import chailei.com.expandablerecyclerviewapp.R;
import chailei.com.expandablerecyclerviewapp.entitys.Tree;

/**
 * Created by Administrator on 16-1-5.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Tree<?>> list;
    private RecyclerView recyclerView;

    public RecyclerAdapter(Context context, List<Tree<?>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        Log.d("viewType","viewType"+viewType);
        switch (viewType){
            case 0:
                 view = LayoutInflater.from(context).inflate(R.layout.first_level_item, parent, false);
                break;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.second_level_item,parent,false);
                break;
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.three_level_item,parent,false);
                break;
            default:
                view = LayoutInflater.from(context).inflate(R.layout.first_level_item, parent, false);
                break;
        }
        view.setOnClickListener(this);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Log.d("onBindViewHolder","position"+position);
        Tree<?> tree = list.get(position);
        switch (tree.getLevel()){
            case 0:
                holder.view_text.setText((CharSequence) list.get(position).getData());
               holder.view_cb.setChecked(tree.isExpand());//切记Checked
                break;
            case 1:
                holder.second_text.setText((CharSequence) list.get(position).getData());
                holder.second_cb.setChecked(tree.isExpand());
                break;
            case 2:
                holder.three_text.setText((CharSequence) list.get(position).getData());
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        int level = list.get(position).getLevel();
        Log.d("level","----------------"+level);
        return level;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(1000);
        recyclerView.setItemAnimator(animator);
    }

    @Override
    public void onClick(View v) {

        Log.d("onClick", "onClick");
        int position = recyclerView.getChildAdapterPosition(v);
        Tree<?> tree = list.get(position);
        if(!tree.isExpand()){
            addAll(position + 1, tree.getList());
        }else{
            removeAll(position + 1, tree.getList());

        }

       tree.setIsExpand(!tree.isExpand());
        notifyItemChanged(position);

    }
    private void removeAll(int position, Collection<Tree<?>> collection) {
        list.removeAll(collection);
        notifyItemRangeRemoved(position,collection.size());
    }

    public void addAll(int position ,Collection<? extends  Tree<?>> collection) {
        list.addAll(position, collection);
        notifyItemRangeInserted(position, collection.size());
    }
    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        private final CheckBox view_cb;
        private final TextView view_text;
        private final TextView second_text;
        private final EditText three_text;
        private final CheckBox second_cb;

        public ItemViewHolder(View itemView) {
            super(itemView);
            view_cb = (CheckBox) itemView.findViewById(R.id.first_item_cb);
            view_text = (TextView) itemView.findViewById(R.id.first_item_text);
            second_text = (TextView) itemView.findViewById(R.id.second_item);
            three_text = (EditText) itemView.findViewById(R.id.three_item);
            second_cb = (CheckBox) itemView.findViewById(R.id.second_cb);
        }
    }
}
