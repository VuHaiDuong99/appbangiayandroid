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
import com.example.appbangiay.Model.Category;
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
        buttonImage();
        buttonAdd();
    }

    private void buttonAdd() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddProductActivity.this, ProductManager.class);

                Bundle bundle = new Bundle();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
                byte[] imgeByte = baos.toByteArray();
                bundle.putString("name",txtName.getText().toString().trim());
                bundle.putString("description",txtDescription.getText().toString().trim());
                bundle.putInt("price",Integer.parseInt(txtPrice.getText().toString().trim()));
                bundle.putInt("id_category",Integer.parseInt(txtCategory.getText().toString().trim()));
                bundle.putByteArray("image",imgeByte);
                // đặt bundle lên intent
                intent.putExtras(bundle);
                // trả về intent cho activity main
                setResult(200,intent);
                // kết thúc

                //startActivity(intent);
                finish();
                Toast.makeText(AddProductActivity.this,"Thêm Thành Công",Toast.LENGTH_SHORT).show();
               /* Intent intent = new Intent(AddProductActivity.this,ProductManager.class);
                startActivity(intent);*/
            }
        });
    }


    private void buttonImage() {
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Request_code_camera);
            }
        });
    }
    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!= null){
            // đặt dữ liệu lên cac component

            txtName.setText(bundle.getString("name"));
            txtDescription.setText(bundle.getString("description"));
            txtPrice.setText(Integer.toString(bundle.getInt("price")));
            txtCategory.setText(Integer.toString(bundle.getInt("id_category")));
            byte[] image = bundle.getByteArray("image");
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imageView.setImageBitmap(bitmap);
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

    }
}
