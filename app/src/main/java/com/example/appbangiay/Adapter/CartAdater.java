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

import com.example.appbangiay.Activity.MainActivity;
import com.example.appbangiay.Model.Cart;
import com.example.appbangiay.Model.Products;
import com.example.appbangiay.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.appbangiay.Activity.CartActivity.resetData;
import static com.example.appbangiay.Activity.CartActivity.tinhTongTien;

public class CartAdater extends BaseAdapter {
    Context context;
    private ArrayList<Cart> listCart;

    public CartAdater(Context context, ArrayList<Cart> listCart) {
        this.context = context;
        this.listCart = listCart;
    }

    @Override
    public int getCount() {
        return listCart.size();
    }

    @Override
    public Object getItem(int position) {
        return listCart.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class  ViewHolder{
        public ImageButton imageDelete;
        public ImageView imageView;
        public TextView txtCount,txtName,txtPrice;
        public Button btnCong,btnTru;

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        CartAdater.ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new CartAdater.ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.dong_cart_layout,null);
            viewHolder.txtName = convertView.findViewById(R.id.txtNameProductCart);
            viewHolder.txtPrice = convertView.findViewById(R.id.txtPriceProductCart);
            viewHolder.imageView = convertView.findViewById(R.id.imageViewProductCart);
            viewHolder.btnCong = convertView.findViewById(R.id.btnCong);
            viewHolder.btnTru = convertView.findViewById(R.id.btnTru);
            viewHolder.imageDelete = convertView.findViewById(R.id.imageDelete);
            viewHolder.txtCount = convertView.findViewById(R.id.txtCount);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (CartAdater.ViewHolder) convertView.getTag();

        }
        // gan gia tri
        final Cart cart = (Cart) getItem(position);

        viewHolder.txtName.setText(cart.getName());
        viewHolder.txtCount.setText(Integer.toString(cart.getCountProduct()));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtPrice.setText("Giá : " + decimalFormat.format(cart.getPrice())+"Đ");
        // image
        // chuyen byte[] sang bitmap
        byte[] image = cart.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        viewHolder.imageView.setImageBitmap(bitmap);
        //final ViewHolder finalViewHolder = viewHolder;
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int slmoi = Integer.parseInt(finalViewHolder.txtCount.getText().toString())+1;
               int slhientai = Integer.parseInt(finalViewHolder.txtCount.getText().toString());
               cart.setCountProduct(slmoi);
               MainActivity.listCart.get(position).setPrice((cart.getPrice()*slmoi)/slhientai);
               resetData();
               tinhTongTien();

            }
        });
        int sl = Integer.parseInt(finalViewHolder.txtCount.getText().toString());
        if(sl<=1){
            finalViewHolder.btnTru.setVisibility(View.INVISIBLE);
        }
        if(sl>=2){
            finalViewHolder.btnTru.setVisibility(View.VISIBLE);
        }
        viewHolder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoi = Integer.parseInt(finalViewHolder.txtCount.getText().toString())-1;
                int slhientai = Integer.parseInt(finalViewHolder.txtCount.getText().toString());
                cart.setCountProduct(slmoi);
                MainActivity.listCart.get(position).setPrice((cart.getPrice()*slmoi)/slhientai);
                resetData();
                tinhTongTien();
                if(slmoi<=1){
                    finalViewHolder.btnTru.setVisibility(View.INVISIBLE);
                }
                if(slmoi>=2){
                    finalViewHolder.btnTru.setVisibility(View.VISIBLE);
                }
            }
        });
        viewHolder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.listCart.remove(cart);
                resetData();
                tinhTongTien();
            }
        });
    return convertView;

    }
}
