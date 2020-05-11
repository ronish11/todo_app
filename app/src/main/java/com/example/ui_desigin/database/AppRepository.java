package com.example.ui_desigin.database;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.ui_desigin.utils.SampleDataProvider;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {

    private static AppRepository ourInstance;
    private AppDatabase mDatabase;
    public static final String tag="my tag";


    public LiveData<List<NoteEntity>> mNotesList;
    private Executor mExecutor= Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context)
    {
        return ourInstance =new AppRepository(context);
    }

    private AppRepository(Context context){
        mDatabase = AppDatabase.getInstance(context);
        mNotesList=getAllNotes();
    }

    public void addSampleData() {

        mExecutor.execute(() -> mDatabase.notesDao().insertAll(SampleDataProvider.getSampleData()));
    }

    private LiveData<List<NoteEntity>> getAllNotes(){
      return   mDatabase.notesDao().getAllNotes();
    }

    public void deleteAllData() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int i=mDatabase.notesDao().deleteAllNotes();
                Log.d(tag,"run:notes deleted" +i);
            }
        });

    }

    public NoteEntity loadNote(int noteId) {
        return mDatabase.notesDao().getNoteById(noteId);
    }

    public void insertNote(NoteEntity noteEntity) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.notesDao().insertNote(noteEntity);
            }
        });
    }
}
