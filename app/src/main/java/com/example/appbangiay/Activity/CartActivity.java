package com.example.appbangiay.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.appbangiay.Adapter.CartAdater;
import com.example.appbangiay.Model.Cart;
import com.example.appbangiay.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Button btnThanhToan;
    public static TextView tongTien;
    public static CartAdater adater;
    private ListView listView;
    private ArrayList<Cart> listCart;
   // public static long tongtien=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_layout);
        AnhXa();
        CheckData();
        tinhTongTien();
        thanhToan();

    }

    private void thanhToan() {
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this,CustomerActivity.class);
                startActivity(intent);
            }
        });
    }

    public static void resetData(){
        adater.notifyDataSetChanged();
    }
    public static double tinhTongTien() {
        double tongtien=0;
        resetData();
        for(int i=0;i<MainActivity.listCart.size();i++){
            tongtien += MainActivity.listCart.get(i).getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongTien.setText("Giá : " + decimalFormat.format(tongtien)+"Đ");
        return tongtien;

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
        bottomNavigationView = findViewById(R.id.botton_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(nav);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener nav =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment = null;
                    //AppCompatActivity appCompatActivity = null;
                    switch (menuItem.getItemId()){
                        case R.id.nav_Home:
                            Intent intent = new Intent(CartActivity.this,MainActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.nav_Products:
                            Intent intent1 = new Intent(CartActivity.this,ListProductActivity.class);
                            intent1.putExtra("abc",1);
                            startActivity(intent1);
                            break;

                        case R.id.nav_Categorys:
                            Intent intent2 = new Intent(CartActivity.this,CategoryActivity.class);
                            startActivity(intent2);
                            break;

                        case R.id.nav_Cart:
                            Intent intent3 = new Intent(CartActivity.this,CartActivity.class);
                            startActivity(intent3);
                            break;
                        case R.id.nav_Account:
                            Intent intent4 = new Intent(CartActivity.this, AdminActivity.class);
                            startActivity(intent4);
                            break;
                    }
                    return false;
                }
            };


}
