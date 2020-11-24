package com.example.teachapp.intents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachapp.R;

public class PhoneIntentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents_phone);

        // gets the number to call.
        final EditText phoneNumber = findViewById(R.id.edit_intents_phone_number);
        // button that calls the selected number.
        final Button phoneCall = findViewById(R.id.button_intents_phone_call);

        phoneCall.setOnClickListener(v -> {
            // creates a new intent that calls.
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            // sets intent data to match the selected phone number.
            callIntent.setData(Uri.parse("tel:" + phoneNumber.getText().toString()));
            startActivity(callIntent);
        });
    }
}
