package com.adaptorsapp.recyclerviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    Context context;
    List<DetailsModelClass> list;
    public ItemAdapter(Context context, List<DetailsModelClass> list){
        this.context = context;
        this.list = list;
    }

    //Custom Adapter
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false);
       LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
       View view = layoutInflater.inflate(R.layout.ui_view_one, parent, false);
       ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageview_one.setImageResource(list.get(position).img);
        holder.textview_one.setText(list.get(position).name);
        holder.textview_two.setText(list.get(position).course);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageview_one;
        TextView textview_one, textview_two;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageview_one = itemView.findViewById(R.id.imageview_one);
            textview_one = itemView.findViewById(R.id.textview_one);
            textview_two = itemView.findViewById(R.id.textview_two);
        }
    }

}
