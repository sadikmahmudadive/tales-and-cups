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
import com.example.talesandcups.Domain.Category;
import com.example.talesandcups.Domain.Foods;
import com.example.talesandcups.ListFoodActivity;
import com.example.talesandcups.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {

    ArrayList<Category> items;
    Context context;

    public CategoryAdapter(ArrayList<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewHolder holder, int position) {
        holder.titleText.setText(items.get(position).getName());
        switch(position) {
            case 0: {
                holder.categoryImage.setBackgroundResource(R.drawable.friction);
                break;
            }
            case 1: {
                holder.categoryImage.setBackgroundResource(R.drawable.non_friction);
                break;
            }
            case 2: {
                holder.categoryImage.setBackgroundResource(R.drawable.mistry);
                break;
            }
            case 3: {
                holder.categoryImage.setBackgroundResource(R.drawable.science_friction);
                break;
            }
            case 4: {
                holder.categoryImage.setBackgroundResource(R.drawable.fantasy);
                break;
            }
            case 5: {
                holder.categoryImage.setBackgroundResource(R.drawable.biography);
                break;
            }
            case 6: {
                holder.categoryImage.setBackgroundResource(R.drawable.self_help);
                break;
            }
            case 7: {
                holder.categoryImage.setBackgroundResource(R.drawable.romance);
                break;
            }
        }
        int drawableResourceId = context.getResources().getIdentifier(items.get(position).getImagePath(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.categoryImage);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ListFoodActivity.class);
            intent.putExtra("CategoryId", items.get(position).getId());
            intent.putExtra("CategoryName", items.get(position).getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        ImageView categoryImage;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.category_name_text);
            categoryImage = itemView.findViewById(R.id.imageCategory);
        }
    }
}
