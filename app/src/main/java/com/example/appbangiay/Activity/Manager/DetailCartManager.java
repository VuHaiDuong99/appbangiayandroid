package com.example.appbangiay.Activity.Manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appbangiay.Adapter.ManagerAdapter.DetailCarAdapter;
import com.example.appbangiay.DataBase.ChiTietDonHangDB;
import com.example.appbangiay.DataBase.DonHangDB;
import com.example.appbangiay.Model.ChiTietDonHang;
import com.example.appbangiay.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DetailCartManager extends AppCompatActivity {
    public ImageButton btnBack;
    private DonHangDB donHangDB;
    private ChiTietDonHangDB chiTietDonHangDB;
    private ArrayList<ChiTietDonHang> listChiTiet;
    private DetailCarAdapter adapter;
    private int idDonHang;
    private ListView listView;
    private TextView tongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_cart_customer);
        AnhXa();
        getData();
        getTongTien();
        onClickBack();
    }

    private void onClickBack() {
        Intent intent = new Intent(DetailCartManager.this,ListCustomer.class);
        startActivity(intent);
    }

    private void getTongTien() {
        int idCustomer = getIntent().getIntExtra("idCutomer", -1);
        //tongTien.setText(Double.toString(donHangDB.getTongTien(idCustomer)));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongTien.setText("Giá : " + decimalFormat.format(donHangDB.getTongTien(idCustomer))+"Đ");
    }

    private void getData() {
        idDonHang  = getIntent().getIntExtra("idDonHang",-1);
        Log.e("DonHang",Integer.toString(idDonHang));
        listChiTiet = chiTietDonHangDB.getAllDonHangById(idDonHang);
        Log.e("size",Integer.toString(listChiTiet.size()));
        adapter = new DetailCarAdapter(this,listChiTiet);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void AnhXa() {
        btnBack = findViewById(R.id.idBackDonHang);
        tongTien = findViewById(R.id.tongTienCartMan);
        listView = findViewById(R.id.lisViewCartDetail);
        chiTietDonHangDB = new ChiTietDonHangDB(this,"ChiTietDonHangDB1",null,1);
        donHangDB = new DonHangDB(this,"DonHangDB1",null,1);
        listChiTiet = new ArrayList<ChiTietDonHang>();
    }
}
