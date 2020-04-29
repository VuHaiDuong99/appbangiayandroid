package com.example.appbangiay.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbangiay.Model.Category;
import com.example.appbangiay.Model.Products;
import com.example.appbangiay.R;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {
    private ArrayList<Category> listCategory;
    private Context context;
    public void setData(ArrayList<Category> data){
        this.listCategory = data;
    }
    public CategoryAdapter(ArrayList<Category> listCategory, Context context) {
        this.listCategory = listCategory;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listCategory.size();
    }

    @Override
    public Object getItem(int position) {
        return listCategory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class  ViewHolder{
        public TextView txtNameCategory;
        public ImageView imageView;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoryAdapter.ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new CategoryAdapter.ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.dong_category_layout,null);
            viewHolder.txtNameCategory = convertView.findViewById(R.id.txtNameCategory);
            viewHolder.imageView = convertView.findViewById(R.id.imageViewCategory);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (CategoryAdapter.ViewHolder) convertView.getTag();

        }
        Category category = (Category) getItem(position);
        viewHolder.txtNameCategory.setText(category.getName());
        byte[] image = category.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        viewHolder.imageView.setImageBitmap(bitmap);
        return convertView;
    }
}
