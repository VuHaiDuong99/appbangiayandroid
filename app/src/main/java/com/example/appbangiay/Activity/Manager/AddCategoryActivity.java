package com.example.appbangiay.Activity.Manager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appbangiay.Activity.CategoryActivity;
import com.example.appbangiay.Activity.MainActivity;
import com.example.appbangiay.DataBase.CategoryDB;
import com.example.appbangiay.Model.Category;
import com.example.appbangiay.Model.Products;
import com.example.appbangiay.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddCategoryActivity extends AppCompatActivity {
    private CategoryDB categoryDB;
    private EditText txtName;
    private Button btnImage,btnAdd,btnEdit;
    private ImageView imageView;
    public int Request_code_camera = 123;
    private int info,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcategory_layout);
        AnhXa();
        getData();
        ClickButton();
        onClickButtonImage();
        onClickButtonAdd();
        onClickButtonEdit();
    }

    private void onClickButtonEdit() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
                byte[] imgeByte = baos.toByteArray();
                Category category = new Category(id,txtName.getText().toString().trim(),
                        imgeByte);
                categoryDB.updateCategory(id,category);
                Intent intent = new Intent(AddCategoryActivity.this,CategoryManagerActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getData() {
        if(info == 1){
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if(bundle!= null){
                // đặt dữ liệu lên cac component
                id = bundle.getInt("id");
                txtName.setText(bundle.getString("name"));
                byte[] image = categoryDB.getImage(id);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
                imageView.setImageBitmap(bitmap);
            }
        }
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

    private void onClickButtonAdd() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
                byte[] imgeByte = baos.toByteArray();
                categoryDB.addCategory(
                        txtName.getText().toString().trim(),
                        imgeByte
                );
                Toast.makeText(AddCategoryActivity.this,"Them thanh cong",Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void onClickButtonImage() {
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Request_code_camera);
            }
        });
    }
    private void AnhXa() {
        info = getIntent().getIntExtra("Edit",-1);
        categoryDB = new CategoryDB(this,"CategoryDB2",null,1);
        txtName = findViewById(R.id.txtNameAddCategory);
        imageView = findViewById(R.id.imageViewAddCategory);
        btnImage = findViewById(R.id.btnImageCategory);
        btnAdd = findViewById(R.id.btnAddCategoryMan);
        btnEdit = findViewById(R.id.btnEditCategoryMan);
        btnAdd.setVisibility(View.INVISIBLE);
        btnEdit.setVisibility(View.INVISIBLE);

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
}
