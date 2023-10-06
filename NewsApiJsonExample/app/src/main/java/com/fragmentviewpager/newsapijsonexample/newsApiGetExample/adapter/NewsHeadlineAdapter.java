package com.fragmentviewpager.newsapijsonexample.newsApiGetExample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fragmentviewpager.newsapijsonexample.R;
import com.fragmentviewpager.newsapijsonexample.newsApiGetExample.modal.NewsRightNowModal;
import com.squareup.picasso.Picasso;

public class NewsHeadlineAdapter extends RecyclerView.Adapter<NewsHeadlineAdapter.Viewholder> {

    Context context;
    NewsRightNowModal res;

    public NewsHeadlineAdapter(Context context, NewsRightNowModal res) {
        this.context = context;
        this.res = res;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //LayoutInflater layoutInflater = LayoutInflater.from(context);
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.news_layout, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // Load image into ImageView using Picasso
        Picasso.get().load(res.getArticles().get(position).getUrlToImage())
                .placeholder(R.drawable.ic_launcher_background).into(holder.iv_news_one);

        holder.tv_title_one.setText(res.getArticles().get(position).getTitle());
        holder.tv_id_one.setText(res.getArticles().get(position).getSource().getId());

        //Next Screen
        holder.cv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return res.getArticles().size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        ImageView iv_news_one;
        TextView tv_title_one, tv_id_one;
        CardView cv_one;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            iv_news_one = itemView.findViewById(R.id.iv_news_one);
            tv_title_one = itemView.findViewById(R.id.tv_title_one);
            tv_id_one = itemView.findViewById(R.id.tv_id_one);
            cv_one = itemView.findViewById(R.id.cv_one);

        }
    }
}
