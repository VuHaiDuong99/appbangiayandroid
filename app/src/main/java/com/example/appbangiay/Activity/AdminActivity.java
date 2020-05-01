package com.example.appbangiay.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.appbangiay.Activity.Manager.AddProductActivity;
import com.example.appbangiay.Activity.Manager.ProductManager;
import com.example.appbangiay.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private EditText txtName,txtPass;
    private Button btnLogin;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        AnhXa();
        buttonLogin();
    }

    private void buttonLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtName.getText().toString().equals("admin") == true && txtPass.getText().toString().equals("123456") == true){
                    Toast.makeText(AdminActivity.this,"Đăng Nhập Thành Công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminActivity.this, ProductManager.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AdminActivity.this,"Đăng Nhập Thất Bại".toString(),Toast.LENGTH_SHORT).show();
                    txtName.setText("");
                    txtPass.setText("");
                }
            }
        });
    }
    private void AnhXa() {
        txtName = findViewById(R.id.txtNameLogin);
        txtPass = findViewById(R.id.txtPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
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
                            Intent intent = new Intent(AdminActivity.this,MainActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.nav_Products:
                            Intent intent1 = new Intent(AdminActivity.this,ListProductActivity.class);
                            intent1.putExtra("abc",1);
                            startActivity(intent1);
                            break;
                        case R.id.nav_Categorys:
                            Intent intent2 = new Intent(AdminActivity.this,CategoryActivity.class);
                            startActivity(intent2);
                            break;
                        case R.id.nav_Cart:
                            Intent intent3 = new Intent(AdminActivity.this,CartActivity.class);
                            startActivity(intent3);
                            break;
                        case R.id.nav_Account:
                            Intent intent4 = new Intent(AdminActivity.this, AdminActivity.class);
                            startActivity(intent4);
                            break;
                    }
                    return false;
                }
            };
}
