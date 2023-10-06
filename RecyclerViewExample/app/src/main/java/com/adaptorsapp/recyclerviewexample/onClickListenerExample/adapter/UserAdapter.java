package com.adaptorsapp.recyclerviewexample.onClickListenerExample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.adaptorsapp.recyclerviewexample.R;
import com.adaptorsapp.recyclerviewexample.onClickListenerExample.modalClass.ModalClass;
import com.adaptorsapp.recyclerviewexample.onClickListenerExample.myInterface.MyClickListener;

import java.util.List;
//Custom Adapter
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    Context context;
    List<ModalClass> list;
    MyClickListener listener;//Object for our interface class

    public UserAdapter(Context context, List<ModalClass> list, MyClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.ui_view_two, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView_ONE.setImageResource(list.get(position).getImage());
        holder.textView_ONE.setText(list.get(position).getName());
        holder.textView_TWO.setText(list.get(position).getCourse());

        holder.cardView_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on click event for our card view we will call our listener(MyClickListener interface) method
                listener.onItemClick(list.get(position));//pass the my model object
                //Implement this interface(MyClickListener) inside our Main Activity
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_ONE;
        TextView textView_ONE, textView_TWO;
        CardView cardView_one;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView_ONE = itemView.findViewById(R.id.imageView_ONE);
            textView_ONE = itemView.findViewById(R.id.textView_ONE);
            textView_TWO = itemView.findViewById(R.id.textView_TWO);
            cardView_one = itemView.findViewById(R.id.cardView_one);

        }
    }
}
