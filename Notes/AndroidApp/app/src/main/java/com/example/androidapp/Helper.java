package com.example.androidapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.wear.activity.ConfirmationActivity;

import java.util.ArrayList;
import java.util.Map;

public class Helper {
    public static String saveNote(Note note, Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        String id = String.valueOf(System.currentTimeMillis());
        editor.putString(id, note.getTitle());

        editor.commit();
        return id;

    }

    public static ArrayList<Note> getAllNotes(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        ArrayList<Note> notes = new ArrayList<>();

        Map<String, ?> key = sharedPreferences.getAll();

        for(Map.Entry<String,?> entry: key.entrySet()){
            String saveData = (String) entry.getValue();

            if(saveData != null){
                Note note = new Note(entry.getKey(), saveData);

                notes.add(note);
            }
        }
        return notes;

    }


    public static void removeNote(String id,Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.remove(id);

        editor.commit();



    }
    public static void displayConfirmation(String message,Context context){
        Intent intent = new Intent(context, ConfirmationActivity.class);
        intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,ConfirmationActivity.SUCCESS_ANIMATION);

        intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE,message);
        context.startActivity(intent);
    }

}

