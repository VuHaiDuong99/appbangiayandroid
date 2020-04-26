package com.example.appbangiay.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbangiay.DataBase.CustomerDB;
import com.example.appbangiay.DataBase.ProductsDB;
import com.example.appbangiay.Model.Customer;
import com.example.appbangiay.R;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class CustomerActivity extends AppCompatActivity {
    private EditText txtName,txtAddress,txtPhone,txtEmail;
    private Button btnXacNhan;
    private CustomerDB customerDB;
    private ArrayList<Customer> listCustomer;
    private Customer customer;
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
                    customerDB.addCustomer(txtName.getText().toString(),
                            txtAddress.getText().toString(),
                            txtPhone.getText().toString(),
                            txtEmail.getText().toString()
                    );
                    Toast.makeText(CustomerActivity.this,"Thêm Thành Công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CustomerActivity.this,"Không có sản phẩm ",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void AnhXa() {
        txtName = findViewById(R.id.txtName);
        txtAddress = findViewById(R.id.txtAddress);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        customerDB = new CustomerDB(this,"CustomerDB",null,1);
        customer = new Customer();
    }

}
