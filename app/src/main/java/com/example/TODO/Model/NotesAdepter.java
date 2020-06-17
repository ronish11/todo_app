package com.example.TODO.Model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TODO.EditorActivity;
import com.example.TODO.R;
import com.example.TODO.database.NoteEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.TODO.utils.Constants.NOTE_ID_KEY;

public class NotesAdepter extends RecyclerView.Adapter<NotesAdepter.MyViewHolder> {

    private Context mContext;
    private List<NoteEntity> mDataList;

    public NotesAdepter(Context mContext, List<NoteEntity> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.note_item_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        NoteEntity noteEntity= mDataList.get(position);
        holder.textView.setText(noteEntity.getText());

        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, EditorActivity.class);
                intent.putExtra(NOTE_ID_KEY,noteEntity.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public  NoteEntity getNotePosition(int position){
        return mDataList.get(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.note_text)
        TextView textView;

         @BindView(R.id.fab_edit_note)
         FloatingActionButton fab;



        MyViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
