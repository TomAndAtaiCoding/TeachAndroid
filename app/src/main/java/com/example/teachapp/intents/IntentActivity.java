package com.example.teachapp.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.teachapp.R;

public class IntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents_main);

        final Button emailIntentRedirect = findViewById(R.id.button_intents_email);
        final Button SMSIntentRedirect = findViewById(R.id.button_intents_sms);
        final Button phoneIntentRedirect = findViewById(R.id.button_intents_phone);
        final Button webIntentRedirect = findViewById(R.id.button_intents_web);
        final Button openYT = findViewById(R.id.button_intents_yt);

        emailIntentRedirect.setOnClickListener(v -> startActivity(new Intent(this, EmailIntentActivity.class)));
        SMSIntentRedirect.setOnClickListener(v -> startActivity(new Intent(this, SMSIntentActivity.class)));
        phoneIntentRedirect.setOnClickListener(v -> startActivity(new Intent(this, PhoneIntentActivity.class)));
        webIntentRedirect.setOnClickListener(v -> startActivity(new Intent(this, WebIntentActivity.class)));

        openYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ytIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
                startActivity(ytIntent);
            }
        });
    }
}