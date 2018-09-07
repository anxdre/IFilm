package com.example.anxdre.ifilm.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.example.anxdre.ifilm.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

public class GetImage {
    public static void picasso(String url, ImageView imageView) {
        Picasso.get().load(url).placeholder(R.drawable.ic_landscape_black_24dp)
                .error(R.drawable.ic_landscape_black_24dp).fit()
                .centerCrop().into(imageView);
    }

//    public static void saveImage(Context context, Bitmap b, String imageName) {
//        FileOutputStream foStream;
//        try {
//            foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE);
//            b.compress(Bitmap.CompressFormat.PNG, 100, foStream);
//            foStream.close();
//        } catch (Exception e) {
//            Log.d("saveImage", "Exception 2, Something went wrong!");
//            e.printStackTrace();
//        }
//    }


}