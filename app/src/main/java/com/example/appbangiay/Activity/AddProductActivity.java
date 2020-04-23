package com.example.appbangiay.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.appbangiay.Adapter.ProductsAdapter;
import com.example.appbangiay.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddProductActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private EditText txtName,txtDescription,txtPrice, txtCategory;
    private ImageView imageView;
    private Button btnImage,btnAdd;
    public int Request_code_camera = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct);
        AnhXa();
        bottomNavigationView.setOnNavigationItemSelectedListener(nav);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Request_code_camera);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chuyen data image ==> byte
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
                byte[] imgeByte = baos.toByteArray();
                MainActivity.productsDB .addProduct1(
                        txtName.getText().toString().trim(),
                        txtDescription.getText().toString().trim(),
                        Integer.parseInt(txtPrice.getText().toString().trim()),
                        imgeByte,
                        Integer.parseInt(txtCategory.getText().toString().trim())
                );

                Toast.makeText(AddProductActivity.this,"Them thanh cong",Toast.LENGTH_SHORT).show();
            }
        });
        /*MainActivity.listProducts = MainActivity.productsDB.getAllProducts();
        MainActivity.adapter = new ProductsAdapter(getApplicationContext(),MainActivity.listProducts);*/
        MainActivity.adapter.notifyDataSetChanged();
        //MainActivity.adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == Request_code_camera && resultCode == RESULT_OK && data !=null ){
           /* Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);*/
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void AnhXa() {
        txtCategory = findViewById(R.id.txtCategory);
        txtName = findViewById(R.id.txtName);
        txtPrice= findViewById(R.id.txtPrice);
        txtDescription = findViewById(R.id.txtDescription);
        imageView = findViewById(R.id.imageView);
        btnImage = findViewById(R.id.btnImage);
        btnAdd = findViewById(R.id.btnAdd);
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
                            Intent intent = new Intent(AddProductActivity.this,MainActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.nav_Account:
                            Intent intent1 = new Intent(AddProductActivity.this,HomeActivity.class);
                            startActivity(intent1);
                            break;
                        case R.id.nav_Search:
                            Intent intent2 = new Intent(AddProductActivity.this,AddProductActivity.class);
                            startActivity(intent2);
                            break;
                    }
                    return false;

                }
            };
}
