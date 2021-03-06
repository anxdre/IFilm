package com.example.anxdre.ifilm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anxdre.ifilm.R;
import com.example.anxdre.ifilm.data.API;
import com.example.anxdre.ifilm.data.model.Movie;
import com.example.anxdre.ifilm.utils.GetImage;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {

    private ArrayList<Movie> movies;
    private Context mcontext;
    private ListOnClick mlistener;


    public MovieAdapter(ArrayList<Movie> film_collection, Context context) {
        movies = film_collection;
        mcontext = context;
    }


    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_adapter, viewGroup, false);

        MovieHolder holder = new MovieHolder(view);
        mlistener = (ListOnClick) viewGroup.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int i) {
        final Movie movie = movies.get(i);
        holder.mtitle.setText(movie.getTitle());
        GetImage.picasso(API.POSTER_PATH + "w500" + movies.get(i).getPoster(), holder.mthumbnail);
//        Log.i("_Pic",String.valueOf(API.POSTER_PATH + "w500" + movies.get(i).getPoster()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.mclick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface ListOnClick {
        void mclick(Movie movie);
    }

}
