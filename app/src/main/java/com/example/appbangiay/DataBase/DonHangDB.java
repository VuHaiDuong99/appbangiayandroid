package com.example.appbangiay.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.appbangiay.Model.Category;
import com.example.appbangiay.Model.DonHang;

import java.util.ArrayList;

public class DonHangDB  extends SQLiteOpenHelper {
    public static final String TableName ="DonHangDB1";
    public static final String Id ="Id";
    public static final String IdCustomer ="IdCustomer";
    public static final String TongTien = "TongTien";
    public DonHangDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreat = "Create table " + TableName + "("
                + Id + " Integer Primary key AUTOINCREMENT,"
                + IdCustomer + " Integer, "
                + TongTien + " Double )";

        db.execSQL(sqlCreat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // xoa bang Table da co
        db.execSQL("Drop table if exists " + TableName);
        // tạo lại
        onCreate(db);
    }
    /*public ArrayList<DonHang> getAllDonHang(){
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
    }*/
    public void addDonHang(int idCustomer,double tongtien){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql ="Insert into DonHangDB1 values (null,?,?)";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindDouble(1,idCustomer);
        sqLiteStatement.bindDouble(2,tongtien);
        sqLiteStatement.executeInsert();
    }
   /* // Sua Category
    public void updateCategory (int id, Category category){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Id,category.getId());
        contentValues.put(Name,category.getName());
        contentValues.put(Image,category.getImage());
        db.update(TableName, contentValues, "Id = " + category.getId(), null );
        db.close();
    }*/
   public int getByIdDonHang(int idCustomer){
       int idDonHang = 0;
       String sql = "select Id from DonHangDB1 where IdCustomer = "+ idCustomer;
       SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery(sql, null);
       // taoj list der tra ve
       if (cursor != null) {
           while (cursor.moveToNext()) {
                idDonHang = cursor.getInt(cursor.getColumnIndex("Id"));
           }
       }
       return idDonHang;
   }
    public Double getTongTien(int idCustomer){
        Double tongtien = 0.0;
        String sql = "select TongTien from DonHangDB1 where IdCustomer = "+ idCustomer;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        // taoj list der tra ve
        if (cursor != null) {
            while (cursor.moveToNext()) {
                tongtien = cursor.getDouble(cursor.getColumnIndex("TongTien"));
            }
        }
        return tongtien;
    }
   /* public void deleteCategory (int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = " Delete From "+ TableName+" where Id =" + id;
        db.execSQL(sql);
        db.close();
    }*/}
