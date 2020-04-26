package com.example.appbangiay.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.appbangiay.Adapter.ListProductAdapter;
import com.example.appbangiay.DataBase.ProductsDB;
import com.example.appbangiay.Model.Products;
import com.example.appbangiay.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ListProductActivity extends AppCompatActivity {
    private ArrayList<Products> listProduct;
    private ProductsDB productsDB;
    private ListProductAdapter adapter;
    private ListView listView;
    private BottomNavigationView bottomNavigationView;
    int dl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_layout);
        AnhXa();
        //getData();
        ClickListView();
        dl = getIntent().getIntExtra("abc",-1);
        if(dl == 1){
                getDataProducts();
        }
        else{
            getDataByCategory();
        }
    }
    private void getDataByCategory() {
        listProduct = productsDB.getProductByCategory(CategoryActivity.idCategory);
        adapter = new ListProductAdapter(listProduct,getApplicationContext());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void getDataProducts() {
        listProduct = productsDB.getAllProducts();
        adapter = new ListProductAdapter(listProduct,getApplicationContext());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void ClickListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListProductActivity.this,ProductDetailActivity.class);
                Products products = listProduct.get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("id", products.getId());
                intent.putExtras(bundle);
                // goij activity moi
                ListProductActivity.this.startActivityForResult(intent,200);
            }
        });
    }
    private void AnhXa() {
        listView = findViewById(R.id.listViewProduct);
        listProduct = new ArrayList<Products>();
        productsDB = new ProductsDB(this,"ProductsDB",null,1);
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
                            Intent intent = new Intent(ListProductActivity.this,MainActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.nav_Products:
                            Intent intent1 = new Intent(ListProductActivity.this,ListProductActivity.class);
                            intent1.putExtra("abc",1);
                            startActivity(intent1);
                            break;

                        case R.id.nav_Categorys:
                            Intent intent2 = new Intent(ListProductActivity.this,CategoryActivity.class);
                            startActivity(intent2);
                            break;

                        case R.id.nav_Cart:
                            Intent intent3 = new Intent(ListProductActivity.this,CartActivity.class);
                            startActivity(intent3);
                            break;
                        case R.id.nav_Account:
                            Intent intent4 = new Intent(ListProductActivity.this, AdminActivity.class);
                            startActivity(intent4);
                            break;
                    }
                    return false;
                }
            };

}
