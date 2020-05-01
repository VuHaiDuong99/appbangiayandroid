package com.example.appbangiay.Activity.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbangiay.Activity.CategoryActivity;
import com.example.appbangiay.Activity.MainActivity;
import com.example.appbangiay.Adapter.CategoryAdapter;
import com.example.appbangiay.DataBase.CategoryDB;
import com.example.appbangiay.Model.Category;
import com.example.appbangiay.Model.Products;
import com.example.appbangiay.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CategoryManagerActivity extends AppCompatActivity {
    public ImageButton btnBack;
    private Button btnAdd,btnQuayLai;
    private ListView listView;
    private Category category;
    private ArrayList<Category> listCategory;
    public  CategoryAdapter adapter;
    public  CategoryDB categoryDB;
    public static int idCategory;
    public  int id;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_manager_manager);
        AnhXa();
        ClickButtonAdd();
        clickButtonQuayLai();
        clickBack();
    }

    private void clickBack() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryManagerActivity.this,ProductManager.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            Bundle bundle = data.getExtras();
            String name = bundle.getString("name");
            byte[] image = bundle.getByteArray("image");
            categoryDB.addCategory(name,image);
            listCategory = categoryDB.getAllCategory();
            adapter.setData(listCategory);
            adapter.notifyDataSetChanged();
        }
        if(requestCode==200 && resultCode == 200){
            // laays duw lieu

            Bundle bundle = data.getExtras();
            //int id = bundle.getInt("id");
            String name = bundle.getString("name");
            byte[] image = bundle.getByteArray("image");
            Category category = new Category(id,name,image);
            categoryDB.updateCategory(id,category);
            listCategory = categoryDB.getAllCategory();
            adapter.setData(listCategory);
            adapter.notifyDataSetChanged();
        }
    }

    private void clickButtonQuayLai() {
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryManagerActivity.this,ProductManager.class);
                startActivity(intent);
            }
        });
    }

    private void ClickButtonAdd() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryManagerActivity.this,AddCategoryActivity.class);
                //intent.putExtra("Edit",2);
                CategoryManagerActivity.this.startActivityForResult(intent,100);
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_listview_option, menu);

    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int selectId = info.position;
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menuEdit:
                intent = new Intent(CategoryManagerActivity.this, AddCategoryActivity.class);
                Category category = listCategory.get(selectId);
                Bundle bundle = new Bundle();
                id = category.getId();
                bundle.putString("name",category.getName());
                bundle.putByteArray("image",category.getImage());
                intent.putExtras(bundle);
                //intent.putExtra("Edit",1);
                // goij activity moi
                CategoryManagerActivity.this.startActivityForResult(intent,200);
                break;
            case R.id.menuDelete:
                int id = listCategory.get(selectId).getId();
                categoryDB.deleteCategory(id);
                listCategory = categoryDB.getAllCategory();
                adapter.setData(listCategory);
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
    private void AnhXa() {
        btnBack = findViewById(R.id.idBackCategory);
        btnQuayLai = findViewById(R.id.btnExit);
        btnAdd = findViewById(R.id.btnAddMan);
        categoryDB = new CategoryDB(this,"CategoryDB3",null,1);
        listView = findViewById(R.id.listViewCategoryMan);
        registerForContextMenu(listView);
        listCategory = new ArrayList<Category>();
        listCategory = categoryDB.getAllCategory();
        adapter = new CategoryAdapter(listCategory,this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
