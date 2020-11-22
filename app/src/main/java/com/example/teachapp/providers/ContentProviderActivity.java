package com.example.teachapp.providers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.teachapp.R;

public class ContentProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_providers);

        final Button newEvent = findViewById(R.id.button_providers_new_event);
        final Button newContact = findViewById(R.id.button_providers_new_contact);

        newEvent.setOnClickListener(v -> {startActivity(new Intent(this, ProvidersNewEventActivity.class));});
        newContact.setOnClickListener(v -> {startActivity(new Intent(this, ProvidersNewContactActivity.class));});
    }
}
