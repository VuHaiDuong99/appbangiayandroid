package com.example.appbangiay.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.appbangiay.Model.Category;
import com.example.appbangiay.Model.Products;

import java.util.ArrayList;

public class ProductsDB extends SQLiteOpenHelper {
    public static final String TableName ="ProductsDB4";
    public static final String Id ="Id";
    public static final String Name ="Name";
    public static final String Image = "Image";
    public static final String Description = "Description";
    public static final String Price = "Price";
    public static final String IdCategory = "IdCategory";
    public ProductsDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreat = "Create table " + TableName + "("
                + Id + " Integer Primary key AUTOINCREMENT  ,"
                + Name + " Text, "
                + Description + " Text, "
                + Price + " Integer, "
                + Image + " BLOB, "
                + IdCategory + " Integer )";

        db.execSQL(sqlCreat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // xoa bang Table da co
        db.execSQL("Drop table if exists " + TableName);
        // tạo lại
        onCreate(db);
    }
    public ArrayList<Products> getAllProducts(){
        ArrayList<Products> list = new ArrayList<>();
        String sql = "Select * from " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        // taoj list der tra ve
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Products product = new Products(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getInt(3)
                        ,cursor.getBlob(4),cursor.getInt(5));
                list.add(product);
            }
        }
        return list;
    }
    public ArrayList<Products> getAllProductsNew(){
        ArrayList<Products> list = new ArrayList<>();
        String sql = "Select * from " + TableName +" ORDER BY Id DESC ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        // taoj list der tra ve
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Products product = new Products(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getInt(3)
                        ,cursor.getBlob(4),cursor.getInt(5));
                list.add(product);
            }
        }
        return list;
    }
    public Products getProductById(int id){
        Products products = new Products();
        String sql = "Select * from "+ TableName+" where Id = "+id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        // taoj list der tra ve
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Products product = new Products(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getInt(3)
                        ,cursor.getBlob(4),cursor.getInt(5));
                products = product;
            }
        }
        return products;
    }
    public ArrayList<Products> getProductByCategory(int category_id){
        ArrayList<Products> list = new ArrayList<>();
        String sql = "Select * from "+ TableName+" where IdCategory = "+category_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        // taoj list der tra ve
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Products product = new Products(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getInt(3)
                        ,cursor.getBlob(4),cursor.getInt(5));
                list.add(product);
            }
        }
        return list;
    }
    public void addProduct(Products product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(Id,product.getId());
        contentValues.put(Name,product.getName());
        contentValues.put(Image,product.getImage());
        contentValues.put(Description,product.getDescription());
        contentValues.put(Price,product.getPrice());
        contentValues.put(IdCategory,product.getPrice());
        db.insert(TableName,null,contentValues);
        db.close();
    }
    public void addProduct1(String name,String description,Integer price,byte[] image,int category){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql ="Insert into ProductsDB4 values (null,?,?,?,?,?)";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,name);
        sqLiteStatement.bindString(2,description);
        sqLiteStatement.bindDouble(3,price);
        sqLiteStatement.bindBlob(4,image);
        sqLiteStatement.bindDouble(5,category);
        sqLiteStatement.executeInsert();
    }
    // Sua Product
    public void updateProduct (int id, Products product){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Id,product.getId());
        contentValues.put(Name,product.getName());
        contentValues.put(Image,product.getImage());
        contentValues.put(Description,product.getDescription());
        contentValues.put(Price,product.getPrice());
        contentValues.put(IdCategory,product.getPrice());
        db.update(TableName, contentValues, "Id = " + product.getId(), null );
        db.close();
    }
    public void deleteProduct (int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = " Delete From "+ TableName+" where Id =" + id;
        db.execSQL(sql);
        db.close();
    }
    public byte[] getImage(int id){
        byte[] image = null;
        ArrayList<Products> list = new ArrayList<>();
        String sql = "Select Image from "+ TableName+" where Id = "+id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        // taoj list der tra ve
        if (cursor != null) {
            while (cursor.moveToNext()) {
                image = cursor.getBlob(cursor.getColumnIndex("Image"));
            }
        }
        return image;
    }
}
