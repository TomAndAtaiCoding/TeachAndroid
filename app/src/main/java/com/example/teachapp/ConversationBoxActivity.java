package com.example.teachapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ConversationBoxActivity extends AppCompatActivity {

    Context m_context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_box);

        findViewById(R.id.button_submit_conversation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.text_Output)).setText(
                        ((EditText)findViewById(R.id.edit_text)).getText()
                );
            }
        });

        findViewById(R.id.switch_explanation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(m_context, ConversationExplanationActivity.class));
            }
        });
    }

}