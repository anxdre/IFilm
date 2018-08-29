package com.example.anxdre.ifilm.utils;

import android.widget.ImageView;

import com.example.anxdre.ifilm.R;
import com.squareup.picasso.Picasso;

public class Download_Image {
    public static void picasso(String url, ImageView imageView){
        Picasso.get().load(url).placeholder(R.drawable.ic_landscape_black_24dp)
                .error(R.drawable.ic_landscape_black_24dp).fit()
                .centerCrop().into(imageView);
    }
}
