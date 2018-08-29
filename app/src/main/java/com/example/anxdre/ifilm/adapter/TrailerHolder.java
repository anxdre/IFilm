package com.example.anxdre.ifilm.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.anxdre.ifilm.R;

public class TrailerHolder extends RecyclerView.ViewHolder {
    TextView trailer_title,trailer_type;
    public TrailerHolder(@NonNull View itemView) {
        super(itemView);
        trailer_title = itemView.findViewById(R.id.trailer_title);
        trailer_type = itemView.findViewById(R.id.trailer_type);
    }
}
