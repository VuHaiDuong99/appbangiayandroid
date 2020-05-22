package com.example.appbangiay.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbangiay.DataBase.ChiTietDonHangDB;
import com.example.appbangiay.DataBase.CustomerDB;
import com.example.appbangiay.DataBase.DonHangDB;
import com.example.appbangiay.DataBase.ProductsDB;
import com.example.appbangiay.Model.Customer;
import com.example.appbangiay.Model.DonHang;
import com.example.appbangiay.R;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class CustomerActivity extends AppCompatActivity {
    private EditText txtName,txtAddress,txtPhone,txtEmail,txtId;
    private Button btnXacNhan;
    public static CustomerDB customerDB;
    public static DonHangDB donHangDB;
    private ChiTietDonHangDB chiTietDonHangDB;
    private ArrayList<Customer> listCustomer;
    public static Customer customer;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_layout);
        AnhXa();
        insertData();
    }
    private void insertData() {
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.listCart.size()>0){
                    int idCustomer = Integer.parseInt(txtId.getText().toString());
                    customerDB.addCustomer1(new Customer(Integer.parseInt(txtId.getText().toString()),txtName.getText().toString(),
                            txtAddress.getText().toString(),
                            txtPhone.getText().toString(),
                            txtEmail.getText().toString())
                    );
                    donHangDB.addDonHang(idCustomer,CartActivity.tinhTongTien());
                    int idDonHang = donHangDB.getByIdDonHang(idCustomer);
                    Log.e("idDonHang",Integer.toString(idDonHang));
                    for(int i =0 ;i< MainActivity.listCart.size();i++){
                        Log.e("idDonHang",Integer.toString(idDonHang));
                        chiTietDonHangDB.addCTDonHang(idDonHang,MainActivity.listCart.get(i).getPrice(),
                                MainActivity.listCart.get(i).getName(),MainActivity.listCart.get(i).getCountProduct(),
                                MainActivity.listCart.get(i).getId()
                                );
                    }
                    Toast.makeText(CustomerActivity.this,"Thêm Thành Công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CustomerActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(CustomerActivity.this,"Không có sản phẩm ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void AnhXa() {
        btnXacNhan = findViewById(R.id.btnXacNhan);
        txtId = findViewById(R.id.txtIdCustomer);
        txtName = findViewById(R.id.txtName);
        txtAddress = findViewById(R.id.txtAddress);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        customerDB = new CustomerDB(this,"CustomerDB2",null,1);
        donHangDB = new DonHangDB(this,"DonHangDB1",null,1);
        chiTietDonHangDB = new ChiTietDonHangDB(this,"ChiTietDonHangDB1",null,1);


    }

}
