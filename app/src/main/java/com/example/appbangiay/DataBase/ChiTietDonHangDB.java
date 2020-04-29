package com.example.appbangiay.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.appbangiay.Model.ChiTietDonHang;

import java.util.ArrayList;
public class ChiTietDonHangDB extends SQLiteOpenHelper {
    public static final String TableName = "ChiTietDonHangDB1";
    public static final String Id = "Id";
    public static final String IdDonHang = "IdDonHang";
    public static final String IdProduct = "IdProduct";
    public static final String NameProduct = "NameProduct";
    public static final String SoLuong = "SoLuong";
    public static final String GiaTien = "GiaTien";

    public ChiTietDonHangDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreat = "Create table " + TableName + "("
                + Id + " Integer Primary key AUTOINCREMENT,"
                + IdDonHang + " Integer, "
                + IdProduct + " Integer ,"
                + NameProduct + "Text, "
                + SoLuong + "Integer ,"
                + GiaTien + "Double )";


        db.execSQL(sqlCreat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // xoa bang Table da co
        db.execSQL("Drop table if exists " + TableName);
        // tạo lại
        onCreate(db);
    }

    public ArrayList<ChiTietDonHang> getAllDonHang() {
        ArrayList<ChiTietDonHang> list = new ArrayList<ChiTietDonHang>();
        String sql = "Select * from " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        // taoj list der tra ve
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ChiTietDonHang chiTiet = new ChiTietDonHang(cursor.getInt(0), cursor.getInt(1),
                        cursor.getInt(2), cursor.getString(3), cursor.getInt(4), cursor.getDouble(5));
                list.add(chiTiet);
            }
        }
        return list;
    }

    public void addCTDonHang(int IdDonHang, double giatien, String nameProduct, int soLuong, int idProduct) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Insert into ChiTietDonHangDB1 values (null,?,?,?,?,?)";
        SQLiteStatement sqLiteStatement = db.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindDouble(1, IdDonHang);
        sqLiteStatement.bindDouble(2, idProduct);
        sqLiteStatement.bindString(3, nameProduct);
        sqLiteStatement.bindDouble(4, soLuong);
        sqLiteStatement.bindDouble(5, giatien);
        sqLiteStatement.executeInsert();
    }

    public ArrayList<ChiTietDonHang> getAllDonHangById(int idDonHang) {
        ArrayList<ChiTietDonHang> list = new ArrayList<ChiTietDonHang>();
        String sql = "Select * from " + TableName + " where IdDonHang = " + idDonHang;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        // taoj list der tra ve
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ChiTietDonHang ct = new ChiTietDonHang(cursor.getInt(0), cursor.getInt(1),
                        cursor.getInt(2), cursor.getString(3), cursor.getInt(4), cursor.getDouble(5));
                list.add(ct);

            }

        }
        return list;
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


   /* public void deleteCategory (int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = " Delete From "+ TableName+" where Id =" + id;
        db.execSQL(sql);
        db.close();
    }*/
}
