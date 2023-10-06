package com.botmnavviewapp.jsongetexample.UsingRecyclerDemoThree.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.botmnavviewapp.jsondemoexample.R;
import com.botmnavviewapp.jsongetexample.UsingRecyclerDemoThree.modelclass.ColorResource;

import java.util.Locale;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.Viewholder> {

    Context context;
    ColorResource colorResource;

    public ColorAdapter(Context context, ColorResource colorResource) {
        this.context = context;
        this.colorResource = colorResource;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*In Android, jab bhi apko Layout say View generate karwana hai/ya View Inflate karwana hai,
        hum use karta hai, LayoutInflater Class.*/
        View view = LayoutInflater.from(context).inflate(R.layout.item_colors, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.idTvThree.setText("page : "+colorResource.getPage()+
                "\nper_page : "+colorResource.getPer_page()+
                "\ntotal : "+colorResource.getTotal()+
                "\ntotal_pages : "+colorResource.getTotal_pages()+
                "\n\nid : "+colorResource.getData().get(position).getId()+
                "\nname : "+colorResource.getData().get(position).getName()+
                "\nyear : "+colorResource.getData().get(position).getYear()+
                "\ncolor : "+colorResource.getData().get(position).getColor()+
                "\npantone_value : "+colorResource.getData().get(position).getPantone_value()+
                "\n\nsupport : "+colorResource.getSupport().getUrl().toUpperCase(Locale.ROOT)+
                "\ntext : "+colorResource.getSupport().getText());
    }

    @Override
    public int getItemCount() {
        return colorResource.getData().size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView idTvThree;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            idTvThree = itemView.findViewById(R.id.idTvThree);

        }
    }
}
