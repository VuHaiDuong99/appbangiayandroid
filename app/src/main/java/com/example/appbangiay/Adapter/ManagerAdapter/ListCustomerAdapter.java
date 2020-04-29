package com.example.appbangiay.Adapter.ManagerAdapter;

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

import com.example.appbangiay.Activity.CustomerActivity;
import com.example.appbangiay.Activity.MainActivity;
import com.example.appbangiay.Adapter.CartAdater;
import com.example.appbangiay.DataBase.DonHangDB;
import com.example.appbangiay.Model.Cart;
import com.example.appbangiay.Model.Customer;
import com.example.appbangiay.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.appbangiay.Activity.CartActivity.resetData;
import static com.example.appbangiay.Activity.CartActivity.tinhTongTien;

public class ListCustomerAdapter extends BaseAdapter {
    Context context;
    private DonHangDB donHangDB;
    private ArrayList<Customer> listCustomer;

    public ListCustomerAdapter(Context context, ArrayList<Customer> listCustomer) {
        this.context = context;
        this.listCustomer = listCustomer;
    }

    @Override
    public int getCount() {
        return listCustomer.size();
    }

    @Override
    public Object getItem(int position) {
        return listCustomer.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class  ViewHolder{
       public TextView txtName;
       public TextView txtTongTien;

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        donHangDB = new DonHangDB(context,"DonHangDB1",null,1);
        ListCustomerAdapter.ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ListCustomerAdapter.ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.dong_list_customer,null);
            viewHolder.txtName = convertView.findViewById(R.id.txtNameCustomer);
            viewHolder.txtTongTien = convertView.findViewById(R.id.txtTongTienCustomer);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ListCustomerAdapter.ViewHolder) convertView.getTag();

        }
        // gan gia tri
        final Customer customer = (Customer) getItem(position);
        viewHolder.txtName.setText(customer.getName());
        //viewHolder.txtTongTien.setText(customer.getAddress());
       /* int id = customer.getId();
        Double tongtien = donHangDB.getTongTien(id);

        viewHolder.txtTongTien.setText(Double.toString(tongtien));*/
        /*DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtTongTien.setText("Giá : " + decimalFormat.format(Double.toString(tongtien))+"Đ");*/
        // image
        // chuyen byte[] sang bitmap

        return convertView;
}}
