package com.example.talesandcups;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.talesandcups.Domain.Foods;
import com.example.talesandcups.Helper.ManagmentCart;
import com.example.talesandcups.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity {
    ActivityDetailBinding binding;
    private Foods object;
    private int num=1;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(Color.parseColor("#472a00"));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getIntentExtra();
        setVariable();
    }

    private void setVariable() {
        managmentCart = new ManagmentCart(this);

        binding.btnBack.setOnClickListener(v -> finish());
        Glide.with(DetailActivity.this)
                .load(object.getImagePath())
                .into(binding.imgProduct);
        binding.txtTitle.setText(object.getTitle());
        binding.txtPrice.setText("$" + object.getPrice());
        binding.txtDescription.setText(object.getDescription());
        binding.txtRate.setText(object.getStar() + " Rating");
        binding.ratingBar.setRating((float) object.getStar());
        binding.txtTotal.setText((num*object.getPrice() + "$"));

        binding.btnPlus.setOnClickListener(view -> {
            num++;
            binding.txtNumber.setText(num+"");
            binding.txtTotal.setText("$" + (num*object.getPrice()));
        });
        binding.btnMinus.setOnClickListener(view -> {
            if (num > 1) {
                num--;
                binding.txtNumber.setText(num + "");
                binding.txtTotal.setText("$" + (num * object.getPrice()));
            }
        });
        binding.btnAdd.setOnClickListener(view -> {
            object.setNumberInCart(num);
            managmentCart.insertFood(object);
        });
    }

    private void getIntentExtra() {
        object = (Foods) getIntent().getSerializableExtra("object");
    }
}