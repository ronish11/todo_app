package com.example.ui_desigin;

import android.os.Bundle;

import com.example.ui_desigin.ViewModel.EditorViewModel;
import com.example.ui_desigin.database.NoteEntity;
import com.example.ui_desigin.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.ui_desigin.utils.Constants.EDITING_KEY;

public class EditorActivity extends AppCompatActivity {

    private EditorViewModel mViewModel;
    @BindView(R.id.edit_note_text)
    TextView mEditText;
    private boolean mNewNote;
    private boolean isEditing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check_black_24dp);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            isEditing=savedInstanceState.getBoolean(Constants.EDITING_KEY);
        }
        initViewModel();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putBoolean(EDITING_KEY,true);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(EditorViewModel.class);

        mViewModel.mLiveNote.observe(this, noteEntity -> {

            if (noteEntity != null && !isEditing) {
                mEditText.setText(noteEntity.getText());
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {

            setTitle("New Note");
            mNewNote = true;
        } else {
            setTitle("Edit Note");
            int noteId= (int) bundle.get(Constants.NOTE_ID_KEY);
            mViewModel.loadNotes(noteId);
            mNewNote = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!mNewNote){
            getMenuInflater().inflate(R.menu.menu_editor,menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            saveAndExit();
            return true;
        }else if(item.getItemId() == R.id.action_delete_note){
            deleteNote();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteNote() {
        mViewModel.deleteNote();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        saveAndExit();
    }

    private void saveAndExit(){
        mViewModel.saveAndExit(mEditText.getText().toString());
    }
}
