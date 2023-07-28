package com.sharedprefapp.adapterexample.recyclerviewexample;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sharedprefapp.adapterexample.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    Context context; //global variable
    List<StudentModalClass> studentModalClasses;

    public ItemAdapter(Context context, List<StudentModalClass> studentModalClasses) {
        this.context = context;
        this.studentModalClasses = studentModalClasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.ui_view_one, parent, false);
//        ViewHolder viewHolder = new ViewHolder(view);

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.ui_view_one, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageview_one.setImageResource(studentModalClasses.get(position).getImage());
        holder.textview_one.setText(studentModalClasses.get(position).getName());
        holder.textview_two.setText(studentModalClasses.get(position).getCourse());
    }

    @Override
    public int getItemCount() {
        return studentModalClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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
