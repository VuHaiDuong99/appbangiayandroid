package com.example.appbangiay.Adapter.ManagerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appbangiay.DataBase.ChiTietDonHangDB;
import com.example.appbangiay.DataBase.DonHangDB;
import com.example.appbangiay.Model.ChiTietDonHang;
import com.example.appbangiay.Model.Customer;
import com.example.appbangiay.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DetailCarAdapter extends BaseAdapter {
    Context context;
    private ArrayList<ChiTietDonHang> listCT;
    public DetailCarAdapter(Context context, ArrayList<ChiTietDonHang> listCT) {
        this.context = context;
        this.listCT = listCT;
    }
    @Override
    public int getCount() {
        return listCT.size();
    }
    @Override
    public Object getItem(int position) {
        return listCT.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public class  ViewHolder{
        public TextView txtName,txtSoLuong,txtGiaTien;
    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        DetailCarAdapter.ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new DetailCarAdapter.ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.dong_detail_cart_manager,null);
            viewHolder.txtName = convertView.findViewById(R.id.txtNameHd);
            viewHolder.txtSoLuong = convertView.findViewById(R.id.txtSoLuongHD);
            viewHolder.txtGiaTien = convertView.findViewById(R.id.txtGiaHd);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (DetailCarAdapter.ViewHolder) convertView.getTag();
        }
        // gan gia tri
        final ChiTietDonHang ctDonHang = (ChiTietDonHang) getItem(position);
        viewHolder.txtName.setText(ctDonHang.getNameProduct());
        viewHolder.txtSoLuong.setText(Integer.toString(ctDonHang.getSoluong()));
      //  viewHolder.txtGiaTien.setText(Double.toString(ctDonHang.getGiaTien()));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaTien.setText("Giá : " + decimalFormat.format(ctDonHang.getGiaTien())+"Đ");
        return convertView;
}}
