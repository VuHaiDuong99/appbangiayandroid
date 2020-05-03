package com.example.appbangiay.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.example.appbangiay.Activity.Manager.ListCustomer;
import com.example.appbangiay.Activity.Manager.ProductManager;
import com.example.appbangiay.Adapter.ProductsAdapter;
import com.example.appbangiay.DataBase.ProductsDB;
import com.example.appbangiay.Model.Cart;
import com.example.appbangiay.Model.Products;
import com.example.appbangiay.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    private ViewFlipper viewFlipper;
    public  static ProductsDB productsDB;
    public  static ProductsAdapter adapter;
    public  ArrayList<Products> listProducts;
    private ListView listView;
    private RecyclerView recyclerView;
    // su dung processbar
    private View proscessbar;
    public static ArrayList<Cart> listCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        ActionViewFiler();
    }
    private void ActionViewFiler() {
        ArrayList<String> imageQuangCao = new ArrayList<>();
        imageQuangCao.add("https://streetstyle.vn/images/companies/1/ban-giay-hieu-1421076935.jpg");
        imageQuangCao.add("https://i.pinimg.com/originals/fa/45/96/fa4596ad9a9d39901eeb455ed4f74e44.jpg");
        imageQuangCao.add("https://bizweb.dktcdn.net/100/238/229/themes/576581/assets/banner-1.jpg?1506519169567");
        //imageQuangCao.add("drawable\\bannerbangiay2.jpg");
        for(int i=0;i<imageQuangCao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(imageQuangCao.get(i)).into(imageView);
            // hinh anh vua voi giao dien
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
            // chay trong bao lau 5s
            viewFlipper.setFlipInterval(3000);
            viewFlipper.setAutoStart(true);
            // dich chuyern
            Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
            Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
            viewFlipper.setInAnimation(animation_slide_in);
            viewFlipper.setOutAnimation(animation_slide_out);
        }
    }
    public void AnhXa(){
        drawerLayout = findViewById(R.id.drawerLayout);

        viewFlipper = findViewById(R.id.viewFlipper);
        recyclerView = findViewById(R.id.id_reView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        productsDB = new ProductsDB(this,"ProductsDB4",null,1);
        listProducts = new ArrayList<Products>();
        listProducts = productsDB.getAllProductsNew();
        adapter = new ProductsAdapter(getApplicationContext(),listProducts);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        proscessbar =  layoutInflater.inflate(R.layout.processbar,null);
        bottomNavigationView = findViewById(R.id.botton_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(nav);
        // neu gio hang bang null , cap phat bo nho cho no,con ko du nguyen gia tri trong mang
        if(listCart !=null){}
        else{listCart = new ArrayList<Cart>();}
    }
    private BottomNavigationView.OnNavigationItemSelectedListener nav =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment = null;
                    //AppCompatActivity appCompatActivity = null;
                    switch (menuItem.getItemId()){
                        case R.id.nav_Home:
                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.nav_Products:
                            Intent intent1 = new Intent(MainActivity.this,ListProductActivity.class);
                            intent1.putExtra("abc",1);
                            startActivity(intent1);
                            break;

                        case R.id.nav_Categorys:
                            Intent intent2 = new Intent(MainActivity.this,CategoryActivity.class);
                            startActivity(intent2);
                            break;

                        case R.id.nav_Cart:
                            Intent intent3 = new Intent(MainActivity.this,CartActivity.class);
                            startActivity(intent3);
                            break;
                        case R.id.nav_Account:
                            Intent intent4 = new Intent(MainActivity.this, AdminActivity.class);
                            startActivity(intent4);
                            break;
                    }
                    return false;
                }
            };
}
