package com.example.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private TextView mTextView;
    private ActivityMainBinding binding;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position,long id){
                if(position ==0){
                    displaySpeechScreen();
                }else{



                }
            }
        });

        updateUI();

    }
    public void updateUI(){
        ArrayList<Note> notes = Helper.getAllNotes(this);

        notes.add(0, new Note("",""));
        listView.setAdapter(new ListViewAdapter(this,0,notes));



    }
    public void displaySpeechScreen(){
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Koji je naslov?");
        startActivityForResult(intent,1001);

    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode == 1001 && resultCode == RESULT_OK){
            List<String> results=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            String message = results.get(0);

            Note note = new Note (null,message);
            Helper.saveNote(note,this);

            Helper.displayConfirmation("Note je sacuvan!",this);

            updateUI();

        }
    }
}