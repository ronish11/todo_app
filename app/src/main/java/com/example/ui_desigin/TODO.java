package com.example.ui_desigin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toolbar;


import com.example.ui_desigin.database.NoteEntity;
import com.example.ui_desigin.Model.NotesAdepter;
import com.example.ui_desigin.utils.SampleDataProvider;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TODO extends AppCompatActivity {

    private List<NoteEntity> mNoteList;

    @BindView(R.id.notes_recyclerview)
    RecyclerView mRecyclerview;

    @OnClick(R.id.fab_add_note)
    void onFabClicked(){
        Intent intent=new Intent(TODO.this, EditorActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo);
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initRecyclerView();

        mNoteList= SampleDataProvider.getSampleData();
        showData();
    }

    private Void setSupportActionBar(Toolbar toolbar) {
        Void o = null;
        return o;
    }

    private void showData()
    {
        NotesAdepter notesAdepter= new NotesAdepter(this,mNoteList);
        mRecyclerview.setAdapter(notesAdepter);
    }


    private void initRecyclerView() {

        mRecyclerview.hasFixedSize();
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(layoutManager);

    }
}

