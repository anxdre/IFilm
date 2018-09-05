package com.example.anxdre.ifilm.moduleDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.anxdre.ifilm.data.model.Favorite;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM Favorite")
    List<Favorite> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (Favorite Newfavorite);

    @Update
    void update (Favorite favorite);

    @Delete
    void delete (Favorite favorite);
}
