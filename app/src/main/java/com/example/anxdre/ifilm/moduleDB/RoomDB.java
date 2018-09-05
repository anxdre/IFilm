package com.example.anxdre.ifilm.moduleDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.anxdre.ifilm.data.model.Favorite;

@Database(entities = {Favorite.class},version = 1)
public abstract class RoomDB extends RoomDatabase{
    public abstract FavoriteDao favoriteDao();
}
