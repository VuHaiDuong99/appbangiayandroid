package com.example.appbangiay.Activity;

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

import com.example.appbangiay.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddCategoryActivity extends AppCompatActivity {
    private EditText txtName;
    private Button btnImage,btnAdd;
    private ImageView imageView;
    public int Request_code_camera = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcategory_layout);
        AnhXa();
        onClickButtonImage();
        onClickButtonAdd();
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
                CategoryActivity.categoryDB.addCategory(
                        txtName.getText().toString().trim(),
                        imgeByte
                );

                Toast.makeText(AddCategoryActivity.this,"Them thanh cong",Toast.LENGTH_SHORT).show();
            }
        });

        MainActivity.adapter.notifyDataSetChanged();

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
        txtName = findViewById(R.id.txtNameAddCategory);
        imageView = findViewById(R.id.imageViewAddCategory);
        btnImage = findViewById(R.id.btnImageCategory);
        btnAdd = findViewById(R.id.btnAddCategory);
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
