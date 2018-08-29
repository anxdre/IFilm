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

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.anxdre.ifilm.R;
import com.example.anxdre.ifilm.adapter.MovieAdapter;
import com.example.anxdre.ifilm.data.API;
import com.example.anxdre.ifilm.data.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentMovieUpcoming extends Fragment {
    ImageView thumbnail;
    TextView title;
    RecyclerView recyclerView;
    MovieAdapter adapter;
    ArrayList<Movie> movies = new ArrayList<>();
    ProgressBar progressBar;
    public FragmentMovieUpcoming() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        thumbnail = view.findViewById(R.id.thumbnail);
        title = view.findViewById(R.id.movie_title);
        recyclerView = view.findViewById(R.id.list_latest);
        progressBar = view.findViewById(R.id.load);

        fan();
        return view;
    }

    private void fan() {
        progressBar.setVisibility(View.VISIBLE);
        movies.clear();
        AndroidNetworking.get(API.MOVIE_UPCOMING).setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.i("_name", jsonObject.optString("title"));

                                Movie movie = new Movie();
                                movie.setId(jsonObject.optInt("id"));
                                movie.setPoster(jsonObject.optString("poster_path"));
                                movie.setTitle(jsonObject.optString("title"));
                                movie.setOverview(jsonObject.optString("overview"));
                                movie.setRelease_date(jsonObject.optString("release_date"));
                                movie.setVote_average(jsonObject.optString("vote_average"));
                                movies.add(movie);
                            }

                            adapter = new MovieAdapter(movies);
                            recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(),2));
                            recyclerView.setAdapter(adapter);

                            progressBar.setVisibility(View.INVISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }
}
