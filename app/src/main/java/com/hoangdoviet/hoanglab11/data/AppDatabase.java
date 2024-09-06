package com.hoangdoviet.hoanglab11.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hoangdoviet.hoanglab11.data.dao.UserDAO;
import com.hoangdoviet.hoanglab11.data.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract UserDAO userDAO();
    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
