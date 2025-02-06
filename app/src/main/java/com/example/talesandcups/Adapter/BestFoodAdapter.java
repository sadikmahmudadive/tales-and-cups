package com.example.talesandcups.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.talesandcups.DetailActivity;
import com.example.talesandcups.Domain.Foods;
import com.example.talesandcups.R;

import java.util.ArrayList;

public class BestFoodAdapter extends RecyclerView.Adapter<BestFoodAdapter.viewHolder> {

    ArrayList<Foods> items;
    Context context;

    public BestFoodAdapter(ArrayList<Foods> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BestFoodAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_best_deal, parent, false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BestFoodAdapter.viewHolder holder, int position) {
        holder.titleText.setText(items.get(position).getTitle());
        holder.priceText.setText("$" + items.get(position).getPrice());
        holder.starText.setText("" + items.get(position).getStar());
        holder.timeText.setText(items.get(position).getTimeValue()+ " minutes");

        Glide.with(context).load(items.get(position).getImagePath()).transform(new CenterCrop(), new RoundedCorners(30)).into(holder.productImage);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("object", items.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView titleText, priceText, starText, timeText;
        ImageView productImage;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            priceText = itemView.findViewById(R.id.priceText);
            starText = itemView.findViewById(R.id.starText);
            timeText = itemView.findViewById(R.id.timeText);
            productImage = itemView.findViewById(R.id.imageProduct);
        }
    }
}
