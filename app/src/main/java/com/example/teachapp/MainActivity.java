package com.example.teachapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button layoutRedirect = findViewById(R.id.layoutRedirect);
        final Button intentRedirect = findViewById(R.id.intentRedirect);
        final Button conversationBoxRedirect = findViewById(R.id.conversationBoxRedirect);
        final Button menuRedirect = findViewById(R.id.menuRedirect);
        final Button dialogueRedirect = findViewById(R.id.dialogueRedirect);
        final Button sharedPreferencesRedirect = findViewById(R.id.sharedPreferencesRedirect);
        final Button contentProvidersRedirect = findViewById(R.id.contentProvidersRedirect);
        final Button phoneComponentRedirect = findViewById(R.id.phoneComponentsRedirect);
        final Button listViewRedirect = findViewById(R.id.listViewRedirect);
        final Button dynamicCodeRedirect = findViewById(R.id.dynamicCodeRedirect);

        layoutRedirect.setOnClickListener(v -> setContentView(R.layout.activity_layouts_intro));
        intentRedirect.setOnClickListener(v -> setContentView(R.layout.activity_intents));
        conversationBoxRedirect.setOnClickListener(v -> setContentView(R.layout.activity_conversation_box));
        menuRedirect.setOnClickListener(v -> setContentView(R.layout.activity_menu));
        dialogueRedirect.setOnClickListener(v -> setContentView(R.layout.activity_dialogue));
        sharedPreferencesRedirect.setOnClickListener(v -> setContentView(R.layout.activity_shared_preferences));
        contentProvidersRedirect.setOnClickListener(v -> setContentView(R.layout.activity_content_providers));
        phoneComponentRedirect.setOnClickListener(v -> setContentView(R.layout.activity_phone_components));
        listViewRedirect.setOnClickListener(v -> setContentView(R.layout.activity_listview));
        dynamicCodeRedirect.setOnClickListener(v -> setContentView(R.layout.activity_dynamic_code));
    }
}