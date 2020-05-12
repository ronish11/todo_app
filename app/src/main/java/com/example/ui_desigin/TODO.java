package com.example.ui_desigin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;


import com.example.ui_desigin.ViewModel.ListActivityViewModel;
import com.example.ui_desigin.database.NoteEntity;
import com.example.ui_desigin.Model.NotesAdepter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TODO extends AppCompatActivity {


    private List<NoteEntity> mNotesList = new ArrayList<>();
    private ListActivityViewModel mViewModel;
    NotesAdepter mNotesAdepter;

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

        initViewModel();


        ButterKnife.bind(this);
        initRecyclerView();

    }

    private void initViewModel() {

        Observer<List<NoteEntity>> notesObserver=
                noteEntities -> {

                    mNotesList.clear();
                    assert noteEntities != null;
                    mNotesList.addAll(noteEntities);

                    if(mNotesAdepter == null)
                    {
                        mNotesAdepter = new NotesAdepter(TODO.this,mNotesList);
                        mRecyclerview.setAdapter(mNotesAdepter);
                    }else{
                        mNotesAdepter.notifyDataSetChanged();
                    }
                };
        mViewModel= ViewModelProviders.of(this).get(ListActivityViewModel.class);
        mViewModel.mNotesList.observe(TODO.this,notesObserver);
    }

    private void setSupportActionBar(Toolbar toolbar) {
        Void o = null;
    }

    private void initRecyclerView() {

        mRecyclerview.hasFixedSize();
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration=new
                DividerItemDecoration(mRecyclerview.getContext(),layoutManager.getOrientation());

        mRecyclerview.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper= new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                deleteNote(mNotesAdepter.getNotePosition(viewHolder.getAdapterPosition()));
            }
        });

        itemTouchHelper.attachToRecyclerView(mRecyclerview);

    }

    private void deleteNote(NoteEntity noteEntity) {
        mViewModel.deleteNote(noteEntity);
        Toast.makeText(this, "Note Sucessfully Deleted", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        switch(id){
            case R.id.add_sample_data:{
                addSampleData();
                return true;
            }
            case R.id.delete_all_data:{
                deleteAllData();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAllData() {
        mViewModel.deleteAllData();
    }

    private void addSampleData() {
        mViewModel.addSampleData();
    }
}

