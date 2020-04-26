package com.example.appbangiay.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.appbangiay.Activity.AddProductActivity;
import com.example.appbangiay.Model.Customer;
import com.example.appbangiay.Model.Products;

import java.util.ArrayList;

public class CustomerDB extends SQLiteOpenHelper {
    public static final String TableName ="ProductsDB";
    public static final String Id ="Id";
    public static final String Name ="Name";
    public static final String Address = "Address";
    public static final String Phone = "Phone";
    public static final String Email = "Email";

    public CustomerDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreat = "Create table " + TableName + "("
                + Id + " Integer Primary key AUTOINCREMENT  ,"
                + Name + " Text, "
                + Address + " Text, "
                + Phone + " Text, "
                + Email + " Text) ";


        db.execSQL(sqlCreat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // xoa bang Table da co
        db.execSQL("Drop table if exists " + TableName);
        // tạo lại
        onCreate(db);
    }
    public ArrayList<Customer> getAllCustomer(){
        ArrayList<Customer> list = new ArrayList<>();
        String sql = "Select * from " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        // taoj list der tra ve
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Customer customer = new Customer(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(3),cursor.getString(4));
                list.add(customer);
            }
        }
        return list;
    }
    public void addCustomer(String name,String email,String phone,String address){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql ="Insert into CustomerDB values (null,?,?,?,?)";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,name);
        sqLiteStatement.bindString(2,address);
        sqLiteStatement.bindString(3,phone);
        sqLiteStatement.bindString(4,email);
        sqLiteStatement.executeInsert();
    }

    public void updateCustomer (int id,Customer customer){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Id,customer.getId());
        contentValues.put(Name,customer.getName());
        contentValues.put(Address,customer.getAddress());
        contentValues.put(Email,customer.getEmail());
        contentValues.put(Phone,customer.getPhone());
        db.update(TableName, contentValues, "Id = " + customer.getId(), null );
        db.close();
    }
    public void deleteCustomer (int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = " Delete From "+ TableName+" where Id =" + id;
        db.execSQL(sql);
        db.close();
    }
}
