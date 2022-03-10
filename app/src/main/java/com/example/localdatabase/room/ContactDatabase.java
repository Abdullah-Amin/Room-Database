package com.example.localdatabase.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {

    private static ContactDatabase insatance;

    public static ContactDatabase getInsatance(Context context){
        if(insatance == null){
            insatance = Room.databaseBuilder(
                    context,
                    ContactDatabase.class,
                    "contactDatabase")
                    .allowMainThreadQueries()
                    .build();
        }
        return insatance;
    }

    public abstract ContactDao contactDao();
}
