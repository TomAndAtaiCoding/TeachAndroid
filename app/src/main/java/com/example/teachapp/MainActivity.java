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

        // sends the user to the layout tutorial starting page.
        final Button layoutRedirect = findViewById(R.id.button_layout_redirect);
        // sends the user to the intent tutorial starting page.
        final Button intentRedirect = findViewById(R.id.button_intent_redirect);
        // sends the user to the conversation box tutorial page.
        final Button conversationBoxRedirect = findViewById(R.id.button_conversation_box_redirect);
        // sends the user to the menu tutorial page.
        final Button menuRedirect = findViewById(R.id.button_menu_redirect);
        // sends the user to the dialogue tutorial page.
        final Button dialogueRedirect = findViewById(R.id.button_dialogue_redirect);
        // sends the user to the shared preferences tutorial page.
        final Button sharedPreferencesRedirect = findViewById(R.id.button_shared_preferences_redirect);
        // sends the user to the content providers tutorial starting page.
        final Button contentProvidersRedirect = findViewById(R.id.button_content_providers_redirect);
        // sends the user to the phone components tutorial starting page.
        final Button phoneComponentRedirect = findViewById(R.id.button_phone_components_redirect);
        // sends the user to the list view tutorial page.
        final Button listViewRedirect = findViewById(R.id.button_list_view_redirect);
        // sends tht user to the dynamic code tutorial page.
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