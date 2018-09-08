package com.example.anxdre.ifilm.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.anxdre.ifilm.R;
import com.example.anxdre.ifilm.adapter.FavoriteAdapter;
import com.example.anxdre.ifilm.data.model.Favorite;
import com.example.anxdre.ifilm.moduleDB.DBModule;

import java.util.List;

public class FragmentMovieFavorite extends Fragment {

    ImageView thumbnail;
    TextView title;
    RecyclerView recyclerView;
    FavoriteAdapter adapter;
    List<Favorite> favorites;
    ProgressBar load;

    public FragmentMovieFavorite(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie,container,false);

        thumbnail = view.findViewById(R.id.thumbnail);
        title = view.findViewById(R.id.movie_title);
        recyclerView = view.findViewById(R.id.list_latest);
        load =view.findViewById(R.id.load);

        load();
        return view;
    }

    public void load(){
        favorites = DBModule.roomDB.favoriteDao().getAll();
        Log.d("database",String.valueOf(favorites));
        adapter = new FavoriteAdapter(favorites,getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);
        load.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResume() {
        load();
        super.onResume();
    }
}
