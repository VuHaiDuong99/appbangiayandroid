package com.example.appbangiay.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appbangiay.DataBase.ProductsDB;
import com.example.appbangiay.Model.Products;
import com.example.appbangiay.R;
import java.text.DecimalFormat;
public class ProductDetailActivity extends AppCompatActivity {
    private TextView txtName,txtMota, txtPrice;
    private ImageView imageView;
    private Button btnGioHang;
    private Spinner spinner;
    private ProductsDB productsDB;
    private Products product;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_product_layout);
        AnhXa();
        getInfo();
    }
    private void getInfo() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!= null) {
            int id = bundle.getInt("id");
            product = productsDB.getProductById(id);
            txtName.setText(product.getName());
            txtMota.setText(product.getDescription());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            txtPrice.setText("Giá : " + decimalFormat.format(product.getPrice())+"Đ");
            byte[] image = product.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imageView.setImageBitmap(bitmap);
        }
    }
    private void AnhXa() {
        txtName = findViewById(R.id.txtNameDetail);
        txtMota = findViewById(R.id.txtMotaDetail);
        txtPrice = findViewById(R.id.txtPriceDetail);
        btnGioHang = findViewById(R.id.btnGioHang);
        imageView = findViewById(R.id.imageViewDetail);
        spinner = findViewById(R.id.spiner);
        productsDB = new ProductsDB(this,"ProductsDB",null,1);
        product = new Products();
    }
}
