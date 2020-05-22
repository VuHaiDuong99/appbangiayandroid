package com.example.appbangiay.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ViewUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbangiay.Activity.ListProductActivity;
import com.example.appbangiay.Activity.ProductDetailActivity;
import com.example.appbangiay.Model.Products;
import com.example.appbangiay.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ItemHoder>
        implements Filterable
{

    private Context context;
    private ArrayList<Products> listProduct;
    private ArrayList<Products> dataBackUp;


    public ProductsAdapter(Context context, ArrayList<Products> listProduct) {
        this.context = context;
        this.listProduct = listProduct;

    }
    public ArrayList<Products> getDataBackUp(){
        return dataBackUp;
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    Products products = listProduct.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", products.getId());
                    intent.putExtras(bundle);
                    // goij activity moi
                   context.startActivity(intent);
                }
            });
        }
    }
    @Override
    public Filter getFilter() {
        Filter f= new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults fr = new FilterResults();
                if(dataBackUp==null){
                    dataBackUp = new ArrayList<>(listProduct);
                }
                if(constraint == null || constraint.length()==0){
                    fr.values = dataBackUp;
                    fr.count = dataBackUp.size();
                }
                else {
                    ArrayList<Products> newData = new ArrayList<>();
                    for(Products products : listProduct){
                        if(products.getName().toLowerCase().contains(constraint.toString().toLowerCase())){
                            newData.add(products);
                            fr.count= newData.size();
                            fr.values = newData;
                        }
                    }
                }
                return fr;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                ArrayList<Products> tmp = (ArrayList<Products>)results.values;
                listProduct.clear();
                    for(Products products: tmp){
                    listProduct.add(products);
                    notifyDataSetChanged();
                }
            };

        };
        return f;
    }
}
