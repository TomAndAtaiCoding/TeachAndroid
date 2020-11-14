package com.example.teachapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.teachapp.components.ComponentActivity;
import com.example.teachapp.conversation.ConversationBoxActivity;
import com.example.teachapp.dialogues.DialogueActivity;
import com.example.teachapp.dynamic.DynamicCodeActivity;
import com.example.teachapp.intents.IntentActivity;
import com.example.teachapp.layouts.LayoutActivity;
import com.example.teachapp.listview.ListViewActivity;
import com.example.teachapp.menus.MenuActivity;
import com.example.teachapp.preferences.SharedPreferencesActivity;
import com.example.teachapp.providers.ContentProviderActivity;

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

        layoutRedirect.setOnClickListener(v -> startActivity(new Intent(this, LayoutActivity.class)));
        intentRedirect.setOnClickListener(v -> startActivity(new Intent(this, IntentActivity.class)));
        conversationBoxRedirect.setOnClickListener(v -> startActivity(new Intent(this, ConversationBoxActivity.class)));
        menuRedirect.setOnClickListener(v -> startActivity(new Intent(this, MenuActivity.class)));
        dialogueRedirect.setOnClickListener(v -> startActivity(new Intent(this, DialogueActivity.class)));
        sharedPreferencesRedirect.setOnClickListener(v -> startActivity(new Intent(this, SharedPreferencesActivity.class)));
        contentProvidersRedirect.setOnClickListener(v -> startActivity(new Intent(this, ContentProviderActivity.class)));
        phoneComponentRedirect.setOnClickListener(v -> startActivity(new Intent(this, ComponentActivity.class)));
        listViewRedirect.setOnClickListener(v -> startActivity(new Intent(this, ListViewActivity.class)));
        dynamicCodeRedirect.setOnClickListener(v -> startActivity(new Intent(this, DynamicCodeActivity.class)));
    }
}