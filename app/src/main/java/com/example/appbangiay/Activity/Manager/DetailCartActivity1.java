package com.example.appbangiay.Activity.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbangiay.Adapter.ManagerAdapter.DetailCarAdapter;
import com.example.appbangiay.DataBase.ChiTietDonHangDB;
import com.example.appbangiay.DataBase.DonHangDB;
import com.example.appbangiay.Model.ChiTietDonHang;
import com.example.appbangiay.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DetailCartActivity1 extends AppCompatActivity {
    public DonHangDB donHangDB;
    public ChiTietDonHangDB chiTietDonHangDB;
    public ImageButton btnBack;
    public ListView listView;
    public TextView tongtien;
    public int idDonHang,IdCustomer;
    public ArrayList<ChiTietDonHang> list;
    public DetailCarAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_cart_customer_layout);
        AnhXa();
        getData();
        getTongHD();
        onClickBack();
    }

    private void getTongHD() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtien.setText("Giá : " + decimalFormat.format(donHangDB.getTongTien(IdCustomer))+"Đ");
    }

    private void onClickBack() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ListCustomer.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        list = chiTietDonHangDB.getAllDonHangById(idDonHang);
        adapter = new DetailCarAdapter(getApplicationContext(),list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void AnhXa() {
        btnBack = findViewById(R.id.idBackDonHang);
        listView = findViewById(R.id.listViewCTDonHang);
        tongtien  = findViewById(R.id.txtTongTienCartCustomer);
        list = new ArrayList<ChiTietDonHang>();
        chiTietDonHangDB = new ChiTietDonHangDB(this,"ChiTietDonHangDB1",null,1);
        donHangDB = new DonHangDB(this,"DonHangDB1",null,1);
        IdCustomer = getIntent().getIntExtra("idCutomer", -1);
        idDonHang  = getIntent().getIntExtra("idDonHang",-1);

    }
}
