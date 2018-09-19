package com.example.anxdre.ifilm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.anxdre.ifilm.adapter.TrailerAdapter;
import com.example.anxdre.ifilm.data.API;
import com.example.anxdre.ifilm.data.model.Favorite;
import com.example.anxdre.ifilm.data.model.Trailer;
import com.example.anxdre.ifilm.moduleDB.DBModule;
import com.example.anxdre.ifilm.utils.GetImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class DescFilm extends AppCompatActivity implements TrailerAdapter.ListOnClickTrailer {
    TextView movietitle, desc_film, date, vote, runtime, relased;
    ImageView cover, thumbnail;
    TrailerAdapter adapter;
    FloatingActionButton addButton;
    ProgressBar load;
    RecyclerView recyclerView;
    ArrayList<Trailer> trailers = new ArrayList<>();
    String Film_ID, backdrop, vote_average, relase_date, duration, poster_path, overview, status;
    int mFilm_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_film);

        movietitle = findViewById(R.id.trailer_title);
        cover = findViewById(R.id.toolbarimage);
        desc_film = findViewById(R.id.Desc);
        date = findViewById(R.id.relase_date);
        relased = findViewById(R.id.Status);
        vote = findViewById(R.id.rating);
        load = findViewById(R.id.load);
        recyclerView = findViewById(R.id.list_trailer);
        runtime = findViewById(R.id.runtime);
        thumbnail = findViewById(R.id.thumbnail);
        addButton = findViewById(R.id.fab);

        Bundle extras = getIntent().getExtras();
        String title = extras.getString("title");
        String overview = extras.getString("overview");
        String poster_pic = extras.getString("image");
//        String release_date = extras.getString("release_date");
//        String vote_average = extras.getString("vote_average");
        String id = extras.getString("ID");
        final String fTitle = title;
        Film_ID = id;
        mFilm_ID = Integer.parseInt(Film_ID);

        //set title and data
        CollapsingToolbarLayout toolbarLayout;
        toolbarLayout = findViewById(R.id.collapsing);
        toolbarLayout.setTitle(title);
        fan();

        if (DBModule.roomDB.favoriteDao().selectOnRow(mFilm_ID) != null) {
            addButton.setImageResource(R.drawable.ic_delete_forever_black_24dp);
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DBModule.roomDB.favoriteDao().selectOnRow(mFilm_ID) != null) {
                    addButton.setImageResource(R.drawable.ic_add_black_24dp);
                    Favorite fav = DBModule.roomDB.favoriteDao().selectOnRow(mFilm_ID);
                    DBModule.roomDB.favoriteDao().delete(fav);
                    Toast.makeText(DescFilm.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                } else {
                    addButton.setImageResource(R.drawable.ic_delete_forever_black_24dp);
                    File file = getApplicationContext().getFileStreamPath("Image" + Film_ID);
                    final String imageFullPath = file.getAbsolutePath();
//                new DownloadImage().execute(API.POSTER_PATH + "w1280" + poster_pic);
                    FANImgDownload(API.POSTER_PATH + "w1280" + poster_path, imageFullPath, "image" + Film_ID);

                    Favorite favorite = new Favorite();
                    favorite.setId(mFilm_ID);
                    favorite.setTitle(fTitle);
                    favorite.setPoster(imageFullPath + "/image" + Film_ID);
//                Log.e("_Poster,Path",imageFullPath);
                    DBModule.roomDB.favoriteDao().insert(favorite);
                    Toast.makeText(getBaseContext(), "Succesfully Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void fan() {
        trailers.clear();
//        Log.i("_URLDESC", API.MOVIE_VIDEO_BASE + Film_ID + API.MOVIE_DESC);
        AndroidNetworking.get(API.MOVIE_VIDEO_BASE + Film_ID + API.MOVIE_DESC).setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        backdrop = String.valueOf(response.optString("backdrop_path"));
                        vote_average = String.valueOf(response.optString("vote_average"));
                        relase_date = String.valueOf(response.optString("release_date"));
                        duration = String.valueOf(response.optString("runtime"));
                        poster_path = String.valueOf(response.optString("belongs_to_collection"));
                        overview = String.valueOf(response.optString("overview"));
                        status = String.valueOf(response.optString("status"));

                        for (int i = 0; i < poster_path.length(); i++) {
                            poster_path = String.valueOf(response.optString("poster_path"));
                        }
//                        Log.e("_IMG",poster_path);
//                        Log.d("_Backdrop", backdrop);
//                        Log.d("_vote", vote_average);
//                        Log.d("_relase", relase_date);
//                        Log.d("_duration", duration);

                        GetImage.picasso(API.POSTER_PATH + "w1280" + backdrop, cover);
                        GetImage.picasso(API.POSTER_PATH + "w1280" + poster_path, thumbnail);
                        vote.setText(vote_average);
                        date.setText(relase_date);
                        runtime.setText(duration);
                        desc_film.setText(overview);
                        relased.setText(status);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.i("_error", "error");
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
//                                Log.i("name", jsonObject.optString("name"));

                                Trailer trailer = new Trailer();
                                trailer.setName(jsonObject.optString("name"));
                                trailer.setType(jsonObject.optString("type"));
                                trailer.setKey(jsonObject.optString("key"));
                                trailers.add(trailer);
                            }

                            adapter = new TrailerAdapter(trailers);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DescFilm.this);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(adapter);

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

//    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
//        private String TAG = "Download Image";
//
//        private Bitmap ImageDownload(String URL) {
//            Bitmap bitmap = null;
//            try {
//                InputStream inputStream = new URL(URL).openStream();
//                bitmap = BitmapFactory.decodeStream(inputStream);
//                inputStream.close();
//            } catch (Exception e) {
//                Log.e(TAG, "Exception Error");
//                e.printStackTrace();
//            }
//            return bitmap;
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... params) {
//            return ImageDownload(params[0]);
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            GetImage.saveImage(getApplicationContext(), result, "Image" + Film_ID);
//            Log.i("svImg",String.valueOf(result));
//        }
//
//    }

    public void FANImgDownload(String URL, String Path, String FileName) {
        AndroidNetworking.download(URL, Path, FileName)
                .setTag("downloadTest")
                .setPriority(Priority.MEDIUM)
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @Override
                    public void onProgress(long bytesDownloaded, long totalBytes) {
                        // do anything with progress
                    }
                })
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Toast.makeText(DescFilm.this, "Succes downloading the poster", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(DescFilm.this, "Error downloading the poster", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void TrailerClick(Trailer trailer) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + trailer.getKey())));
        //  System.out.println("it work");
    }
}
