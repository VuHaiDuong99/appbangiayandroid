package com.example.appbangiay.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbangiay.Model.Products;
import com.example.appbangiay.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ItemHoder> {
    //private ImageView  imageViewProduct;
   // private TextView txtPrice, txtName;
    private Context context;
    private ArrayList<Products> listProduct = new ArrayList<>();


    public ProductsAdapter(Context context, ArrayList<Products> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
        //this.onItemClickedListener = onItemClickedListener;
    }

    @NonNull
    @Override
    public ItemHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,null);
        ItemHoder itemHoder = new ItemHoder(v);

        return itemHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHoder holder, int position) {
        Products products = listProduct.get(position);
        holder.txtNameProduct.setText(products.getName());
        //holder.txtPriceProudcts.setText(products.getDescription());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPriceProudcts.setText("Giá : " + decimalFormat.format(products.getPrice())+"Đ");

        // image
        // chuyen byte[] sang bitmap
        byte[] image = products.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        holder.imageProducts.setImageBitmap(bitmap);
        // bắt sự kiện khi click

    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ItemHoder extends RecyclerView.ViewHolder {
        public ImageView imageProducts;
        public TextView txtNameProduct, txtPriceProudcts;

        public ItemHoder(@NonNull View itemView) {
            super(itemView);
            imageProducts =itemView.findViewById(R.id.imageViewProduct);
            txtNameProduct = itemView.findViewById(R.id.nameProduct);
            txtPriceProudcts = itemView.findViewById(R.id.priceProduct);


        }


    }



}
