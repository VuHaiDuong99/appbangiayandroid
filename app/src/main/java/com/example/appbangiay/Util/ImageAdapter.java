package com.example.appbangiay.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class ImageAdapter {
    public byte[] imageToByte(ImageView imageView){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] imgeByte = baos.toByteArray();
        return  imgeByte;
    }
    public void bytetoImage( byte[] image,ImageView imageView ){
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        imageView.setImageBitmap(bitmap);
    }
}
