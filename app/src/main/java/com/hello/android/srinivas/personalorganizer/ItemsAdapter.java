package com.hello.android.srinivas.personalorganizer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by srinivas on 2/2/17.
 */
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Information> data  = Collections.emptyList();

    public ItemsAdapter (Context context, List<Information> data) {
        inflater  = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder  = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information current = data.get(position);
        holder.listItem.setText(current.itemName);
        holder.listPriority.setText(current.priorityName);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                // here I need to open detailed activity and do whatever I want
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * This MyViewHolder class is very helpful,
     *
     * because of this we would be reducing a lot of overhead of creating each row in a
     * view (eg. list view) by calling findViewById for each element for particular row.
     *
     * instead we use this as a chunk together and cache it, which can/will be reused
     * during loading more elements (eg. 500 elements) per view (eg. list view)
     *
     * This is taken care by recycler view of creating it again and again
     */
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listItem;
        TextView listPriority;
        ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {

            super(itemView);

            listItem = (TextView) itemView.findViewById(R.id.tvItem);
            listPriority = (TextView) itemView.findViewById(R.id.tvPriority);

        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(getLayoutPosition());
        }
    }
}
