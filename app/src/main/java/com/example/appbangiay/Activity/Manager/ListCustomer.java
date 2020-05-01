package com.example.appbangiay.Activity.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appbangiay.Activity.CustomerActivity;
import com.example.appbangiay.Adapter.ManagerAdapter.DetailCarAdapter;
import com.example.appbangiay.Adapter.ManagerAdapter.ListCustomerAdapter;
import com.example.appbangiay.DataBase.CustomerDB;
import com.example.appbangiay.DataBase.DonHangDB;
import com.example.appbangiay.Model.Customer;
import com.example.appbangiay.R;

import java.util.ArrayList;

public class ListCustomer extends AppCompatActivity {
    public ImageButton back;
    public  CustomerDB customerDB;
    private ListView listView;
    private ListCustomerAdapter adapter;
    private ArrayList<Customer> list;
    private DonHangDB donHangDB;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_customer_manager);
        AnhXa();
        listViewClick();
        onclickBack();
    }

    private void onclickBack() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListCustomer.this,ProductManager.class);
                startActivity(intent);
            }
        });
    }

    private void listViewClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int id_customer = list.get(position).getId();
                int id_Donhang = donHangDB.getByIdDonHang(id_customer);
                Intent intent = new Intent(ListCustomer.this, DetailCartManager.class);
                intent.putExtra("idDonHang",id_Donhang);
                intent.putExtra("idCutomer",id_customer);
                startActivity(intent);
    }
});
    }
    private void AnhXa() {
        back = findViewById(R.id.idBackDS);
        listView = findViewById(R.id.lisViewCustomer);
        list = new ArrayList<Customer>();
        donHangDB = new DonHangDB(this,"DonHangDB1",null,1);
        customerDB = new CustomerDB(this,"CustomerDB1",null,1);
        list = customerDB.getAllCustomer();
        adapter = new ListCustomerAdapter(this,list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
