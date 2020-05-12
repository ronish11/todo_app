package com.example.ui_desigin.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ui_desigin.database.AppRepository;
import com.example.ui_desigin.database.NoteEntity;
import com.example.ui_desigin.utils.SampleDataProvider;

import java.util.List;

public class ListActivityViewModel extends AndroidViewModel {

    public LiveData<List<NoteEntity>> mNotesList;
    private AppRepository mRepository;
    public ListActivityViewModel(@NonNull Application application) {
        super(application);
        mRepository=AppRepository.getInstance(application.getApplicationContext());
        mNotesList=mRepository.mNotesList;
    }

    public void addSampleData() {

        mRepository.addSampleData();
    }

    public void deleteAllData() {
        mRepository.deleteAllData();
    }

    public void deleteNote(NoteEntity noteEntity) {

        mRepository.deleteNote(noteEntity);

    }
}
