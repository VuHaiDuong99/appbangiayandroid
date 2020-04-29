package com.example.appbangiay.Activity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.appbangiay.DataBase.ProductsDB;
import com.example.appbangiay.Model.Cart;
import com.example.appbangiay.Model.Products;
import com.example.appbangiay.R;
import com.example.appbangiay.Util.ImageAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
public class ProductDetailActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private TextView txtName,txtMota, txtPrice;
    private ImageView imageView;
    private Button btnGioHang;
    private Spinner spinner;
    private ProductsDB productsDB;
    private Products product;
    private int id,tien;
    private String name;
    public ImageAdapter imageAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_product_layout);
        AnhXa();
        getInfo();
        soluongSanPhamGioHang();
        themGioHang();
    }
    private void themGioHang() {
        btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.listCart.size()> 0){
                    boolean tag = false;
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    for(int i =0;i<MainActivity.listCart.size();i++){
                        // neu như san pham trung nhau
                        if(MainActivity.listCart.get(i).getId() == id){
                            MainActivity.listCart.get(i).setCountProduct(soluong+MainActivity.listCart.get(i).getCountProduct());
                            tag = true;
                        }
                        MainActivity.listCart.get(i).setPrice(
                                MainActivity.listCart.get(i).getCountProduct()
                                        *MainActivity.listCart.get(i).getPrice());
                    }
                    if(tag == false){
                        int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                        //int tongTien = Integer.parseInt(txtPrice.getText().toString()) * sl;
                        int tongTien = tien * soluong;
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
                        byte[] imgeByte = baos.toByteArray();
                        MainActivity.listCart.add(new Cart(id,name,tongTien,imgeByte,sl));
                    }
                }
                else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    //int tongTien = Integer.parseInt(txtPrice.getText().toString()) * soluong;
                    int tongTien = tien * soluong;
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
                    byte[] imgeByte = baos.toByteArray();
                    MainActivity.listCart.add(new Cart(id,txtName.getText().toString(),tongTien,imgeByte,soluong));
                }
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
    private void soluongSanPhamGioHang() {
        Integer[] soluong =new Integer []{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> list = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(list);
    }
    private void getInfo() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!= null) {
            id = bundle.getInt("id");
            product = productsDB.getProductById(id);
            txtName.setText(product.getName());
            txtMota.setText(product.getDescription());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            txtPrice.setText("Giá : " + decimalFormat.format(product.getPrice())+"Đ");
            byte[] image = product.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imageView.setImageBitmap(bitmap);
            tien = product.getPrice();
            name = product.getName();
        }
    }
    private void AnhXa() {
        txtName = findViewById(R.id.txtNameDetail);
        txtMota = findViewById(R.id.txtMotaDetail);
        txtPrice = findViewById(R.id.txtPriceDetail);
        btnGioHang = findViewById(R.id.btnGioHang);
        imageView = findViewById(R.id.imageViewDetail);
        spinner = findViewById(R.id.spiner);
        productsDB = new ProductsDB(this,"ProductsDB2",null,1);
        product = new Products();
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
                            Intent intent = new Intent(ProductDetailActivity.this,MainActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.nav_Products:
                            Intent intent1 = new Intent(ProductDetailActivity.this,ListProductActivity.class);
                            intent1.putExtra("abc",1);
                            startActivity(intent1);
                            break;

                        case R.id.nav_Categorys:
                            Intent intent2 = new Intent(ProductDetailActivity.this,CategoryActivity.class);
                            startActivity(intent2);
                            break;

                        case R.id.nav_Cart:
                            Intent intent3 = new Intent(ProductDetailActivity.this,CartActivity.class);
                            startActivity(intent3);
                            break;
                        case R.id.nav_Account:
                            Intent intent4 = new Intent(ProductDetailActivity.this, AdminActivity.class);
                            startActivity(intent4);
                            break;
                    }
                    return false;
                }
            };

}
