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

public class CategoryDB extends SQLiteOpenHelper {
    public static final String TableName ="CategoryDB3";
    public static final String Id ="Id";
    public static final String Name ="Name";
    public static final String Image = "Image";
    public CategoryDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreat = "Create table " + TableName + "("
                + Id + " Integer Primary key AUTOINCREMENT,"
                + Name + " Text, "
                + Image + " BLOB )";

        db.execSQL(sqlCreat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // xoa bang Table da co
        db.execSQL("Drop table if exists " + TableName);
        // tạo lại
        onCreate(db);
    }
    public ArrayList<Category> getAllCategory(){
        ArrayList<Category> list = new ArrayList<Category>();
        String sql = "Select * from " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        // taoj list der tra ve
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Category category = new Category(cursor.getInt(0),cursor.getString(1),
                        cursor.getBlob(2));
                list.add(category);
            }
        }
        return list;
    }
    public void addCategory(String name,byte[] image){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql ="Insert into CategoryDB3 values (null,?,?)";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1,name);
        sqLiteStatement.bindBlob(2,image);
        sqLiteStatement.executeInsert();
    }
    // Sua Category
    public void updateCategory (int id, Category category){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Id,category.getId());
        contentValues.put(Name,category.getName());
        contentValues.put(Image,category.getImage());
        db.update(TableName, contentValues, "Id = " + category.getId(), null );
        db.close();
    }
    public void deleteCategory (int id){
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
    public ArrayList<String> getNameCateogry(){
        ArrayList<String> list = new ArrayList<>();
        String sql = "Select Name from " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        // taoj list der tra ve
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("Name"));
                list.add(name);
            }
        }
        return list;
    }
}
