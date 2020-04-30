package com.example.appbangiay.Activity.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.appbangiay.Activity.AdminActivity;
import com.example.appbangiay.Activity.CartActivity;
import com.example.appbangiay.Activity.CategoryActivity;
import com.example.appbangiay.Activity.ListProductActivity;
import com.example.appbangiay.Activity.MainActivity;
import com.example.appbangiay.Activity.ProductDetailActivity;
import com.example.appbangiay.Adapter.ListProductAdapter;
import com.example.appbangiay.DataBase.ProductsDB;
import com.example.appbangiay.Model.Category;
import com.example.appbangiay.Model.Products;
import com.example.appbangiay.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;

public class ProductManager extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ListView listViewBar;
    private ImageButton btnImage;
    private ArrayList<Products> listProduct;
    private ProductsDB productsDB;
    private ListProductAdapter adapter;
    private ListView listView;
    private BottomNavigationView bottomNavigationView;
    public int id,dl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_manager_layout);
        AnhXa();
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu();
            }
        });
        getDataProducts();
       // ClickListView();
    }
    private void showMenu() {
        PopupMenu popupMenu = new PopupMenu(this,btnImage);
        popupMenu.getMenuInflater().inflate(R.menu.menu_manager,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.menuHome:
                        intent = new Intent(ProductManager.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.menuProduct:
                        intent = new Intent(ProductManager.this, ProductManager.class);
                        startActivity(intent);
                        break;
                    case R.id.menuCategory:
                        intent = new Intent(ProductManager.this, CategoryManagerActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.menuCustomer:
                        intent = new Intent(ProductManager.this, ListCustomer.class);
                        startActivity(intent);
                        break;
                    case R.id.menuAddProduct:
                        intent = new Intent(ProductManager.this, AddProductActivity.class);
                        startActivityForResult(intent,100);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
    private void getDataProducts() {
        listProduct = MainActivity.productsDB.getAllProducts();
        adapter = new ListProductAdapter(listProduct,getApplicationContext());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void AnhXa() {
        btnImage = findViewById(R.id.btnImageList);
        listView = findViewById(R.id.listViewProductMan);
        registerForContextMenu(listView);
        listProduct = new ArrayList<Products>();
        bottomNavigationView = findViewById(R.id.botton_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(nav);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_listview_option, menu);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            Bundle bundle = data.getExtras();
            String name = bundle.getString("name");
            String description = bundle.getString("description");
            int price = bundle.getInt("price");
            int idCategpry = bundle.getInt("id_category");
            byte[] image = bundle.getByteArray("image");
            MainActivity.productsDB.addProduct1(name,description,price,image,idCategpry);
            listProduct =  MainActivity.productsDB.getAllProducts();
            adapter.setData(listProduct);
            adapter.notifyDataSetChanged();
        }
        if(requestCode==200 && resultCode == 200){
            // laays duw lieu

            Bundle bundle = data.getExtras();

            String name = bundle.getString("name");
            String description = bundle.getString("description");
            int price = bundle.getInt("price");
            int idCategpry = bundle.getInt("id_category");
            byte[] image = bundle.getByteArray("image");
            Products products = new Products(id,name,description,price,image,idCategpry);
            MainActivity.productsDB.updateProduct(id,products);
            listProduct =  MainActivity.productsDB.getAllProducts();
            adapter.setData(listProduct);
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int selectId = info.position;
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menuEdit:
                intent = new Intent(ProductManager.this, AddProductActivity.class);
                Products products = listProduct.get(selectId);
                Bundle bundle = new Bundle();
                id = products.getId();
                bundle.putString("name",products.getName());
                bundle.putString("description",products.getDescription());
                bundle.putInt("price",products.getPrice());
                bundle.putInt("id_category",products.getId_category());
                bundle.putByteArray("image",products.getImage());
                intent.putExtras(bundle);
                ProductManager.this.startActivityForResult(intent,200);
                break;
            case R.id.menuDelete:
                int id = listProduct.get(selectId).getId();
                MainActivity.productsDB.deleteProduct(id);
                listProduct = MainActivity.productsDB.getAllProducts();
                adapter.setData(listProduct);
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener nav =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment = null;
                    //AppCompatActivity appCompatActivity = null;
                    switch (menuItem.getItemId()){
                        case R.id.nav_Home:
                            Intent intent = new Intent(ProductManager.this, MainActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.nav_Products:
                            Intent intent1 = new Intent(ProductManager.this,ListProductActivity.class);
                            startActivity(intent1);
                            break;

                        case R.id.nav_Categorys:
                            Intent intent2 = new Intent(ProductManager.this,CategoryActivity.class);
                            startActivity(intent2);
                            break;

                        case R.id.nav_Cart:
                            Intent intent3 = new Intent(ProductManager.this, CartActivity.class);
                            startActivity(intent3);
                            break;
                        case R.id.nav_Account:
                            Intent intent4 = new Intent(ProductManager.this, AdminActivity.class);
                            startActivity(intent4);
                            break;
                    }
                    return false;
                }
            };
}
