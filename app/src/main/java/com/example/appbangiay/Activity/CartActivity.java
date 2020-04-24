package com.example.appbangiay.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbangiay.Adapter.CartAdater;
import com.example.appbangiay.Model.Cart;
import com.example.appbangiay.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private Button btnThanhToan;
    public static TextView tongTien;
    public static CartAdater adater;
    private ListView listView;
    private ArrayList<Cart> listCart;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_layout);
        AnhXa();
        CheckData();
        tinhTongTien();

    }
    public static void resetData(){
        adater.notifyDataSetChanged();
    }
    public static void tinhTongTien() {
        long tongtien=0;
        for(int i=0;i<MainActivity.listCart.size();i++){
            tongtien += MainActivity.listCart.get(i).getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongTien.setText("Giá : " + decimalFormat.format(tongtien)+"Đ");

    }

    private void CheckData() {
        if(MainActivity.listCart.size()<0){
            Toast.makeText(CartActivity.this,"Khong Co San Pham Nao",Toast.LENGTH_SHORT).show();
        }
        else{
            adater.notifyDataSetChanged();
        }
    }

    private void AnhXa() {
        btnThanhToan = findViewById(R.id.btnThanhToans);
        tongTien = findViewById(R.id.tongTienCart);
        listView = findViewById(R.id.listViewCart);
        adater = new CartAdater(this,MainActivity.listCart);
        listView.setAdapter(adater);
    }

}
