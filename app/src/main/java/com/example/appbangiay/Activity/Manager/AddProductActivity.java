package com.example.appbangiay.Activity.Manager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
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

import com.example.appbangiay.Activity.MainActivity;
import com.example.appbangiay.Model.Products;
import com.example.appbangiay.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddProductActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private EditText txtName,txtDescription,txtPrice, txtCategory;
    private ImageView imageView;
    private Button btnImage,btnAdd,btnEdit;
    public int Request_code_camera = 123;
    private int info,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct);
        AnhXa();

        getData();
        bottomNavigationView.setOnNavigationItemSelectedListener(nav);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Request_code_camera);
            }
        });
        ClickButton();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
                byte[] imgeByte = baos.toByteArray();
                MainActivity.productsDB.addProduct1(
                        txtName.getText().toString().trim(),
                        txtDescription.getText().toString().trim(),
                        Integer.parseInt(txtPrice.getText().toString().trim()),
                        imgeByte,
                        Integer.parseInt(txtCategory.getText().toString().trim())
                );

                Toast.makeText(AddProductActivity.this,"Thêm Thành Công",Toast.LENGTH_SHORT).show();
               /* Intent intent = new Intent(AddProductActivity.this,ProductManager.class);
                startActivity(intent);*/
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
                byte[] imgeByte = baos.toByteArray();
                Products products = new Products(id,txtName.getText().toString().trim(),
                        txtDescription.getText().toString().trim(),
                        Integer.parseInt(txtPrice.getText().toString().trim()),
                        imgeByte,
                        Integer.parseInt(txtCategory.getText().toString().trim()));
                MainActivity.productsDB.updateProduct(id,products);

                Intent intent = new Intent(AddProductActivity.this,ProductManager.class);
                startActivity(intent);
            }

        });

    }

    private void ClickButton() {

        if(info == 1 ){
            btnAdd.setVisibility(View.INVISIBLE);
            btnEdit.setVisibility(View.VISIBLE);
        }
        else {
            btnAdd.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.INVISIBLE);
        }
    }

    private void getData() {
        if(info == 1){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!= null){
            // đặt dữ liệu lên cac component
            id = bundle.getInt("id");
            txtName.setText(bundle.getString("name"));
            txtDescription.setText(bundle.getString("description"));
            txtPrice.setText(Integer.toString(bundle.getInt("price")));
            txtCategory.setText(Integer.toString(bundle.getInt("id_category")));
            byte[] image = MainActivity.productsDB.getImage(id);
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imageView.setImageBitmap(bitmap);
        }
        }

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
        btnEdit = findViewById(R.id.btnEdit);
        btnAdd.setVisibility(View.INVISIBLE);
        btnEdit.setVisibility(View.INVISIBLE);
        info = getIntent().getIntExtra("Edit",-1);
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
                            Intent intent1 = new Intent(AddProductActivity.this,MainActivity.class);
                            startActivity(intent1);
                            break;

                    }
                    return false;

                }
            };
}
