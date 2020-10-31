package com.example.teachapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button layoutRedirect = findViewById(R.id.button_layout_redirect);
        final Button intentRedirect = findViewById(R.id.button_intent_redirect);
        final Button conversationBoxRedirect = findViewById(R.id.button_conversation_box_redirect);
        final Button menuRedirect = findViewById(R.id.button_menu_redirect);
        final Button dialogueRedirect = findViewById(R.id.button_dialogue_redirect);
        final Button sharedPreferencesRedirect = findViewById(R.id.button_shared_preferences_redirect);
        final Button contentProvidersRedirect = findViewById(R.id.button_content_providers_redirect);
        final Button phoneComponentRedirect = findViewById(R.id.button_phone_components_redirect);
        final Button listViewRedirect = findViewById(R.id.button_list_view_redirect);
        final Button dynamicCodeRedirect = findViewById(R.id.button_dynamic_code_redirect);

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