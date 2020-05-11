package com.example.ui_desigin.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;


@Database(entities = {NoteEntity.class},version=1)
@TypeConverters(DateConveter.class)
public abstract class AppDatabase extends RoomDatabase {  //class is abstract direct implementation is not allowed

    public static final String DATABASE_NAME="notesdatabase.db";

    public static volatile AppDatabase Instance;

    private  static  final Object LOCK=new Object();

    public abstract NotesDao notesDao();

    public static AppDatabase getInstance(Context context){

        if(Instance ==null){
            synchronized (LOCK){
                if(Instance==null){
                    // actual creation of database
                    Instance= Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,DATABASE_NAME).build();
                }
            }
        }
        return Instance;
    }
}
