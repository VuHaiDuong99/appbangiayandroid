package com.example.appbangiay.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.FrameMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.appbangiay.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity{
    private BottomNavigationView bottomNavigationView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        AnhXa();
        //bottomNavigationView.setOnNavigationItemSelectedListener(nav);
        // getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new HomeActivity().onAttachFragment()).commit();
    }
    public void AnhXa(){bottomNavigationView = findViewById(R.id.botton_nav);}
    private BottomNavigationView.OnNavigationItemSelectedListener nav =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment = null;
                    //AppCompatActivity appCompatActivity = null;
                    switch (menuItem.getItemId()){
                        case R.id.nav_Home:
                            Intent intent = new Intent(HomeActivity.this,HomeActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.nav_Account:
                            Intent intent1 = new Intent(HomeActivity.this,HomeActivity.class);
                            startActivity(intent1);
                            break;
                    }
                    return false;

                }
            };
}
