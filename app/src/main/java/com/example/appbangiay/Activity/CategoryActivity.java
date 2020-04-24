package com.example.appbangiay.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbangiay.Adapter.ProductsAdapter;
import com.example.appbangiay.DataBase.ProductsDB;
import com.example.appbangiay.Model.Products;
import com.example.appbangiay.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    private ProductsDB productsDB;
    private ProductsAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Products> listProducts;

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_layout);
        productsDB = new ProductsDB(this,"ProductsDB",null,1);
        listProducts = new ArrayList<Products>();
        //productsDB.addProduct(new Products(1,"Vu hai duong","Vu hai duong",3000,null,1));
        listProducts = productsDB.getAllProducts();
        adapter = new ProductsAdapter(getApplicationContext(),listProducts);
        adapter.notifyDataSetChanged();
        recyclerView = findViewById(R.id.id_test);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(adapter);
        AnhXa();
        bottomNavigationView.setOnNavigationItemSelectedListener(nav);
        // getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new HomeActivity().onAttachFragment()).commit();
    }
    public void AnhXa(){
        bottomNavigationView = findViewById(R.id.botton_nav);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener nav =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment = null;
                    //AppCompatActivity appCompatActivity = null;
                    switch (menuItem.getItemId()){
                        case R.id.nav_Home:
                            Intent intent = new Intent(CategoryActivity.this,HomeActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.nav_Account:
                            Intent intent1 = new Intent(CategoryActivity.this,HomeActivity.class);
                            startActivity(intent1);
                            break;
                        case R.id.nav_Search:
                            Intent intent2 = new Intent(CategoryActivity.this,HomeActivity.class);
                            startActivity(intent2);
                            break;
                    }
                    return false;

                }
            };
}
