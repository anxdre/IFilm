package com.example.anxdre.ifilm.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.anxdre.ifilm.R;
import com.example.anxdre.ifilm.adapter.MovieAdapter;
import com.example.anxdre.ifilm.data.model.Favorite;
import com.example.anxdre.ifilm.data.model.Movie;
import com.example.anxdre.ifilm.moduleDB.DBModule;
import com.example.anxdre.ifilm.moduleDB.RoomDB;

import java.util.ArrayList;
import java.util.List;

public class FragmentMovieFavorite extends Fragment {

    ImageView thumbnail;
    TextView title;
    RecyclerView recyclerView;
    MovieAdapter adapter;
    List<Favorite> Favorites;

    public FragmentMovieFavorite(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie,container,false);

        thumbnail = view.findViewById(R.id.thumbnail);
        title = view.findViewById(R.id.movie_title);
        recyclerView = view.findViewById(R.id.list_latest);

        return view;
    }

    public void load(){
        Favorites = DBModule.roomDB.favoriteDao().getAll();
//        adapter = new MovieAdapter()
    }
}
