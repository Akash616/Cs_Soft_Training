package com.firstproject.recyclerinfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapetr extends RecyclerView.Adapter<MyAdapetr.ViewHolder> {

    ArrayList<DataModel> dataholder;

    public MyAdapetr(ArrayList<DataModel> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.img.setImageResource(dataholder.get(position).getImage());
        holder.header.setText(dataholder.get(position).getHeader());
        holder.desc.setText(dataholder.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView header, desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img1);
            header = itemView.findViewById(R.id.t1);
            desc = itemView.findViewById(R.id.t2);
        }
    }
}
