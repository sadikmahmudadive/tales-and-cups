package com.example.talesandcups.Adapter;

import android.content.Context;
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
import com.example.talesandcups.Domain.Foods;
import com.example.talesandcups.Helper.ChangeNumberItemsListener;
import com.example.talesandcups.Helper.ManagmentCart;
import com.example.talesandcups.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewHolder> {
    ArrayList<Foods> list;
    private ManagmentCart managmentCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdapter(ArrayList<Foods> list, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.list = list;
        managmentCart = new ManagmentCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new viewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.viewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.feeEachItem.setText("$"+ list.get(position).getPrice());
        holder.totalEachItem.setText(list.get(position).getNumberInCart() + "*$" +(
                list.get(position).getNumberInCart()*list.get(position).getPrice()));
        holder.num.setText(list.get(position).getNumberInCart() + "");

        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.productImg);

        holder.plusItem.setOnClickListener(v -> managmentCart.plusNumberItem(list, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));

        holder.minusItem.setOnClickListener(v -> managmentCart.minusNumberItem(list, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem, plusItem, minusItem;
        ImageView productImg;
        TextView totalEachItem, num;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textTitle);
            productImg = itemView.findViewById(R.id.imgProduct);
            feeEachItem = itemView.findViewById(R.id.textTotalEach);
            plusItem = itemView.findViewById(R.id.btnCartPlus);
            minusItem = itemView.findViewById(R.id.btnCartMinus);
            totalEachItem = itemView.findViewById(R.id.textFee);
            num = itemView.findViewById(R.id.textNumberItem);
        }
    }
}
