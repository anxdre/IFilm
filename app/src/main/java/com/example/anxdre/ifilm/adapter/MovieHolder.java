package com.example.anxdre.ifilm.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.anxdre.ifilm.R;

import butterknife.BindView;

public class MovieHolder extends RecyclerView.ViewHolder {
    ImageView mthumbnail;
    TextView mtitle;

    public MovieHolder(@NonNull View itemView) {
        super(itemView);
        mthumbnail = itemView.findViewById(R.id.thumbnail);
        mtitle = itemView.findViewById(R.id.movie_title);
    }
}
