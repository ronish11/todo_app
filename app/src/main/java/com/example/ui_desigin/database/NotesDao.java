package com.example.ui_desigin.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import butterknife.OnClick;

@Dao
public interface NotesDao {


    // this method is used to create and update or  replace the note if ID is repeat more then once
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(NoteEntity noteEntity);


    // multiple notes create at same time (used for sample data in this program)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<NoteEntity> noteEntities);

    // delete the single  note
    @Delete
    void deleteNote(NoteEntity noteEntity);

    // Read the note by its ID
    @Query("SELECT * FROM notes WHERE id =:id")
    NoteEntity getNoteById(int id);

    // read note by descending order
    @Query("SELECT * FROM notes ORDER BY date DESC")
    List<NoteEntity> getAllNotes();

    // delete all notes
    @Query("DELETE FROM notes")
    NoteEntity deleteAllNotes();

    // count all the note
    @Query("SELECT COUNT(*) FROM notes")
    int getCount();
}
