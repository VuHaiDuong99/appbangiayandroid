package com.example.appbangiay.Adapter.ManagerAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbangiay.Adapter.ListProductAdapter;
import com.example.appbangiay.Model.Products;
import com.example.appbangiay.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductManAdapter extends BaseAdapter {
    private ArrayList<Products> data;
    private Context context;

    public ProductManAdapter(ArrayList<Products> listProduct, Context context) {
        this.data = listProduct;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    // ViewHoder khi co du lieu thi no se load lai , chung ta ko can load lai nhieu lan
    public class ViewHolder{
        public TextView txtName, txtPrice;
        public ImageView imageView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductManAdapter.ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ProductManAdapter.ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.dong_product_manager_layout,null);
            viewHolder.txtName = convertView.findViewById(R.id.txtNameProductMan);
            viewHolder.txtPrice = convertView.findViewById(R.id.txtPriceProductMan);
            viewHolder.imageView = convertView.findViewById(R.id.imageViewProductMan);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ProductManAdapter.ViewHolder) convertView.getTag();

        }
        // gan gia tri
        Products product = (Products) getItem(position);
        viewHolder.txtName.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtPrice.setText("Giá : " + decimalFormat.format(product.getPrice())+"Đ");
        // image
        // chuyen byte[] sang bitmap
        byte[] image = product.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        viewHolder.imageView.setImageBitmap(bitmap);
        return convertView;
    }
}
