package com.example.appbangiay.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbangiay.Adapter.CategoryAdapter;
import com.example.appbangiay.Adapter.ProductsAdapter;
import com.example.appbangiay.DataBase.CategoryDB;
import com.example.appbangiay.DataBase.ProductsDB;
import com.example.appbangiay.Model.Category;
import com.example.appbangiay.Model.Products;
import com.example.appbangiay.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    private ListView listView;
    private Category category;
    private ArrayList<Category> listCategory;
    public static CategoryAdapter adapter;
    public static CategoryDB categoryDB;
    public static int idCategory;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_layout);
        AnhXa();
        ListViewClick();
    }

    private void ListViewClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idCategory = listCategory.get(position).getId();
                Intent intent = new Intent(CategoryActivity.this,ListProductActivity.class);
                intent.putExtra("abc",2);
                startActivity(intent);
            }
        });
    }

    public void AnhXa(){
        listView = findViewById(R.id.listViewCategory);
        bottomNavigationView = findViewById(R.id.botton_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(nav);
        categoryDB = new CategoryDB(this,"CategoryDB2",null,1);
        listCategory = new ArrayList<Category>();
        //Category category1 = new Category(1,"giay au",null);
        // category = new Category(2,"giay luoi",null);
       // listCategory.add(category);
        listCategory =  categoryDB.getAllCategory();
        adapter = new CategoryAdapter(listCategory,this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener nav =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment = null;
                    //AppCompatActivity appCompatActivity = null;
                    switch (menuItem.getItemId()){
                        case R.id.nav_Home:
                            Intent intent = new Intent(CategoryActivity.this,MainActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.nav_Products:
                            Intent intent1 = new Intent(CategoryActivity.this,ListProductActivity.class);
                            intent1.putExtra("abc",1);
                            startActivity(intent1);
                            break;

                        case R.id.nav_Categorys:
                            Intent intent2 = new Intent(CategoryActivity.this,CategoryActivity.class);
                            startActivity(intent2);
                            break;

                        case R.id.nav_Cart:
                            Intent intent3 = new Intent(CategoryActivity.this,CartActivity.class);
                            startActivity(intent3);
                            break;
                        case R.id.nav_Account:
                            Intent intent4 = new Intent(CategoryActivity.this, AdminActivity.class);
                            startActivity(intent4);
                            break;
                    }
                    return false;
                }
            };

}
