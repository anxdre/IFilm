package com.example.anxdre.ifilm.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anxdre.ifilm.R;
import com.example.anxdre.ifilm.data.model.Favorite;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<MovieHolder> {
    private List<Favorite>favorites;

    public FavoriteAdapter(List<Favorite>favorites){
        this.favorites = favorites;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_adapter,viewGroup,false);
        MovieHolder holder = new MovieHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
