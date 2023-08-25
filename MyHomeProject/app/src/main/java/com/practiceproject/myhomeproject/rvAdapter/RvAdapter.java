package com.practiceproject.myhomeproject.rvAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practiceproject.myhomeproject.R;
import com.practiceproject.myhomeproject.modalClass.RoomsModal;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.Viewholder> {

    ArrayList<RoomsModal> list;
    Context context;

    public RvAdapter(Context context, ArrayList<RoomsModal> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.home_rv_layout, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.iv_home.setImageResource(list.get(position).getImg());
        holder.tv_home_location.setText(list.get(position).getLocation());
        holder.tv_home_bedroom.setText(list.get(position).getBedrooms());
        holder.tv_home_rent.setText(list.get(position).getRent());
        holder.tv_home_bathroom.setText(list.get(position).getBathrooms());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView iv_home;
        TextView tv_home_location, tv_home_bedroom, tv_home_rent, tv_home_bathroom;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            iv_home = itemView.findViewById(R.id.iv_home);
            tv_home_location = itemView.findViewById(R.id.tv_home_location);
            tv_home_bedroom = itemView.findViewById(R.id.tv_home_bedroom);
            tv_home_rent = itemView.findViewById(R.id.tv_home_rent);
            tv_home_bathroom = itemView.findViewById(R.id.tv_home_bathroom);

        }
    }
}
