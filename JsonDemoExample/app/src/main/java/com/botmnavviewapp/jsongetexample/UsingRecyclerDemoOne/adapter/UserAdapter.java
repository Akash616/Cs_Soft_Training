package com.botmnavviewapp.jsongetexample.UsingRecyclerDemoOne.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.botmnavviewapp.jsondemoexample.R;
import com.botmnavviewapp.jsongetexample.UsingRecyclerDemoOne.interfaceRecycler.RecyclerViewInterface;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.botmnavviewapp.jsongetexample.UsingRecyclerDemoOne.modalclass.UserResponse;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private UserResponse response;
    //----------------------------------------------------------------
    //Define a variable to hold our recyclerview interface
    private RecyclerViewInterface recyclerViewInterface;

    //-------------------------------------------------------------
    public UserAdapter(Context context, UserResponse response, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.response = response;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_users, parent, false);
        ViewHolder viewHolder = new ViewHolder(view,recyclerViewInterface);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //---------------------------------------------------------------------------------------------------------------
        //--Passing response to next screen
//        final UsersDataList temp = response.getData().get(position); //position click -> object stored in temp
//        holder.idCvOne.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, SecondActivityOne.class);
//                intent.putExtra("Img", temp.getAvatar());
//                intent.putExtra("Name", temp.getFirst_name()+" "+temp.getLast_name());
//                intent.putExtra("Email", temp.getEmail());
//
//                //Inner class say next activity call karna hai, the use setFlags(),
//                //otherwise it through an error.
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                context.startActivity(intent);
//            }
//        });
        //------------------------------------------------------------------------------------------------------------------

        holder.idTvOne.setText(response.getData().get(position).getFirst_name() + "\n" +
                response.getData().get(position).getLast_name() + "\n" +
                response.getData().get(position).getEmail());

        // Load image into ImageView using Picasso
        Picasso.get().load(response.getData().get(position).getAvatar())
                .placeholder(R.drawable.man_5).into(holder.idImageView);

        holder.idCvOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewInterface.onItemClick(response.getData().get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return response.getData().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView idTvOne;
        ImageView idImageView;
        CardView idCvOne;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            idTvOne = itemView.findViewById(R.id.idTvOne);
            idImageView = itemView.findViewById(R.id.idImageView);
            idCvOne = itemView.findViewById(R.id.idCvOne);

        }
    }

}
