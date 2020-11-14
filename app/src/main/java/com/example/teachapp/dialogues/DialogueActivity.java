package com.example.teachapp.dialogues;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachapp.R;

public class DialogueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogue);

        final Button dialogueAlert = findViewById(R.id.button_dialogue_alert);
        final Button dialogueDate = findViewById(R.id.button_dialogue_date);
        final Button dialogueTime = findViewById(R.id.button_dialogue_time);

        //dialogueAlert.setOnClickListener();
    }
}