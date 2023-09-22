package com.example.healthlink;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.healthlink.profileSetting.User;
import com.example.healthlink.profileSetting.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();



    // singleton pattern
    //1
    private static AppDatabase appDatabase; //declaration for the instance

    //2
    public AppDatabase() {

    }

    //3
    public static synchronized AppDatabase getInstance(Context context) {

        if(appDatabase == null)
        {
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class, "AppDatabase").allowMainThreadQueries().build();
        }
        return appDatabase;
    }

}
