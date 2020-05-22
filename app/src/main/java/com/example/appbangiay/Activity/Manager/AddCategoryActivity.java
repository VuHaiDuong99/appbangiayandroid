package com.example.appbangiay.Activity.Manager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    public ImageButton btnBack;
    private CategoryDB categoryDB;
    private EditText txtName;
    private Button btnImage,btnAdd,btnEdit,btnExit;
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
        //onClickButtonEdit();
        onClickBack();

    }

    private void onClickBack() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCategoryActivity.this,CategoryManagerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onClickButtonEdit() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
                byte[] imgeByte = baos.toByteArray();
                Category category = new Category(id,txtName.getText().toString().trim(),
                        imgeByte);
                categoryDB.updateCategory(id,category);
                Intent intent = new Intent(AddCategoryActivity.this,CategoryManagerActivity.class);
                startActivity(intent);*/
                Intent intent = new Intent(AddCategoryActivity.this,CategoryManagerActivity.class);

                Bundle bundle = new Bundle();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
                byte[] imgeByte = baos.toByteArray();
                bundle.putString("name",txtName.getText().toString().trim());
                bundle.putByteArray("image",imgeByte);
                // đặt bundle lên intent
                intent.putExtras(bundle);
                // trả về intent cho activity main
                setResult(200,intent);
                // kết thúc
//                startActivity(intent);
                //startActivity(intent);
                finish();
                Toast.makeText(AddCategoryActivity.this," Thành Công",Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void getData() {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if(bundle!= null){
                // đặt dữ liệu lên cac component
               // id = bundle.getInt("id");
                txtName.setText(bundle.getString("name"));
                byte[] image = bundle.getByteArray("image");
                Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
                imageView.setImageBitmap(bitmap);
            }

    }
    private void ClickButton() {

    }

    private void onClickButtonAdd() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCategoryActivity.this, CategoryManagerActivity.class);
                if(txtName.getText() == null){
                    Toast.makeText(AddCategoryActivity.this,"Bạn chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                Bundle bundle = new Bundle();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
                byte[] imgeByte = baos.toByteArray();
                bundle.putString("name",txtName.getText().toString().trim());
                bundle.putByteArray("image",imgeByte);
                // đặt bundle lên intent
                intent.putExtras(bundle);
                // trả về intent cho activity main
                setResult(200,intent);
                // kết thúc

                //startActivity(intent);
                finish();
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

        categoryDB = new CategoryDB(this,"CategoryDB5",null,1);
        txtName = findViewById(R.id.txtNameAddCategory);
        imageView = findViewById(R.id.imageViewAddCategory);
        btnImage = findViewById(R.id.btnImageCategory);
        btnBack = findViewById(R.id.idBackAddCategory);
        btnAdd = findViewById(R.id.btnAddCategoryMan);



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
