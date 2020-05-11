package com.example.ui_desigin.utils;

import com.example.ui_desigin.database.NoteEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleDataProvider {
    private static final String SAMPLE_TEXT_1 = "Go for meeting";
    private static final String SAMPLE_TEXT_2= "play pubg";
    private static final String SAMPLE_TEXT_3= "Read novel";

    private static Date getDate(int i)
    {
        GregorianCalendar calendar=new GregorianCalendar();
        calendar.add(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static List<NoteEntity> getSampleData(){

        List<NoteEntity> notesList = new ArrayList<>();

        notesList.add(new NoteEntity(1, getDate(0),SAMPLE_TEXT_1));
        notesList.add(new NoteEntity(2,getDate(-1),SAMPLE_TEXT_2));
        notesList.add(new NoteEntity(3,getDate(-2),SAMPLE_TEXT_3));

        return notesList;
    }

}
