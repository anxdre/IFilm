package com.example.anxdre.ifilm.moduleDB;

import android.app.Application;
import android.arch.persistence.room.Room;

public class DBModule extends Application {
    public static RoomDB roomDB;

    @Override
    public void onCreate() {
        super.onCreate();
        roomDB = Room.databaseBuilder(getApplicationContext()
                ,RoomDB.class,"favorite.db")
                .allowMainThreadQueries().build();
    }
}
