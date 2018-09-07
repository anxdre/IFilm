package com.example.anxdre.ifilm.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anxdre.ifilm.R;
import com.example.anxdre.ifilm.data.model.Favorite;

import java.io.FileInputStream;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<MovieHolder> {
    private List<Favorite> mfavorites;
    private Context mcontext;
    private Clicked mClick;

    public FavoriteAdapter(List<Favorite> favorites, Context context) {
        mfavorites = favorites;
        mcontext = context;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_adapter, viewGroup, false);
        MovieHolder holder = new MovieHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
        final Favorite favorite = mfavorites.get(i);
        movieHolder.mthumbnail.setImageBitmap(BitmapFactory.decodeFile(favorite.getPoster()));
        movieHolder.mtitle.setText(favorite.getTitle());

        movieHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClick.OnClick(favorite);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mfavorites.size();
    }

    public interface Clicked{
        void OnClick (Favorite favorite);
    }

//
//    public Bitmap loadImageBitmap(Context context, String imageName) {
//        Bitmap bitmap = null;
//        FileInputStream fiStream;
//        try {
//            fiStream = context.openFileInput(imageName);
//            bitmap = BitmapFactory.decodeStream(fiStream);
//            fiStream.close();
//        } catch (Exception e) {
//            Log.d("saveImage", "Exception 3, Something went wrong!" + e);
//            e.printStackTrace();
//        }
//        return bitmap;
//    }
}
