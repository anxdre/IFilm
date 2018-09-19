package com.example.anxdre.ifilm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.anxdre.ifilm.adapter.TrailerAdapter;
import com.example.anxdre.ifilm.data.API;
import com.example.anxdre.ifilm.data.model.Trailer;
import com.example.anxdre.ifilm.utils.Download_Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DescFilm extends AppCompatActivity implements TrailerAdapter.ListOnClickTrailer {
    TextView movietitle, desc_film, relase, vote, runtime;
    ImageView cover,thumbnail;
    TrailerAdapter adapter;
    ProgressBar load;
    RecyclerView list_trailer;
    ArrayList<Trailer> trailers = new ArrayList<>();
    String Film_ID,backdrop,vote_average,relase_date,duration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_film);

        movietitle = findViewById(R.id.trailer_title);
        cover = findViewById(R.id.toolbarimage);
        desc_film = findViewById(R.id.Desc);
        relase = findViewById(R.id.relase_date);
        vote = findViewById(R.id.rating);
        load = findViewById(R.id.load);
        list_trailer = findViewById(R.id.list_trailer);
        runtime = findViewById(R.id.runtime);
        thumbnail = findViewById(R.id.thumbnail);

        Bundle extras = getIntent().getExtras();
        String title = extras.getString("title");
        String overview = extras.getString("overview");
        String poster_pic = extras.getString("image");
//        String release_date = extras.getString("release_date");
//        String vote_average = extras.getString("vote_average");
        String id = extras.getString("ID");

        //sett title and data
        CollapsingToolbarLayout a;
        a = findViewById(R.id.collapsing);
        a.setTitle(title);
        Film_ID = id;

      Download_Image.picasso(API.POSTER_PATH + "w1280" + poster_pic, thumbnail);
        desc_film.setText(overview);
//        relase.setText(release_date);
//
//        vote.setText(vote_average);
        fan();
    }

    private void fan() {
        trailers.clear();
        Log.i("_URLDESC" , API.MOVIE_VIDEO_BASE + Film_ID + API.MOVIE_DESC);
        AndroidNetworking.get(API.MOVIE_VIDEO_BASE + Film_ID + API.MOVIE_DESC).setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        backdrop = String.valueOf(response.optString("backdrop_path"));
                        vote_average = String.valueOf(response.optString("vote_average"));
                        relase_date = String.valueOf(response.optString("release_date"));
                        duration = String.valueOf(response.optString("runtime"));
                        Log.d("_Backdrop", backdrop);
                        Log.d("_vote", vote_average);
                        Log.d("_relase", relase_date);
                        Log.d("_duration", duration);
                        vote.setText(vote_average);
                        relase.setText(relase_date);
                        runtime.setText(duration);
                        Download_Image.picasso(API.POSTER_PATH + "w1280" + backdrop, cover);
//                        try {
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.i("_error","error");
                    }
                });

        AndroidNetworking.get(API.MOVIE_VIDEO_BASE + Film_ID + API.MOVIE_VIDEO_URL).setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.i("name", jsonObject.optString("name"));

                                Trailer trailer = new Trailer();
                                trailer.setName(jsonObject.optString("name"));
                                trailer.setType(jsonObject.optString("type"));
                                trailer.setKey(jsonObject.optString("key"));
                                trailers.add(trailer);
                            }
                            adapter = new TrailerAdapter(trailers);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DescFilm.this);
                            list_trailer.setLayoutManager(linearLayoutManager);
                            list_trailer.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        load.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    @Override
    public void TrailerClick(Trailer trailer) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + trailer.getKey())));
        //  System.out.println("it work");
    }
}
