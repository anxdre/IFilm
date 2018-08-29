package com.example.anxdre.ifilm.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anxdre.ifilm.R;
import com.example.anxdre.ifilm.data.model.Movie;
import com.example.anxdre.ifilm.data.model.Trailer;

import java.util.ArrayList;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerHolder> {

    private ArrayList<Trailer>trailers;
    ListOnClickTrailer onClickTrailer;

    public TrailerAdapter (ArrayList<Trailer>trailer_collection){
        this.trailers = trailer_collection;
    }

    @NonNull
    @Override
    public TrailerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.desc_adapter,viewGroup,false);

        TrailerHolder holder = new TrailerHolder(view);
        onClickTrailer = (ListOnClickTrailer) viewGroup.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerHolder trailerHolder, int i) {
        final Trailer trailer = trailers.get(i);
        trailerHolder.trailer_title.setText(trailer.getName());
        trailerHolder.trailer_type.setText(trailer.getType());

        trailerHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickTrailer.TrailerClick(trailer);
            }
        });

    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public interface ListOnClickTrailer{
        void TrailerClick(Trailer trailer);
    }
}
