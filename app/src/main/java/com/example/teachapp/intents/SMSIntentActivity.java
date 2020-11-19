package com.example.teachapp.intents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachapp.R;

public class SMSIntentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents_sms);

        final Button smsSend = findViewById(R.id.button_intents_sms_send);
        final EditText smsRecipient = findViewById(R.id.edit_intents_sms_recipient);
        final EditText smsMessage = findViewById(R.id.edit_intents_sms_message);

        smsSend.setOnClickListener(v -> {
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
            smsIntent.setData(Uri.parse("smsto:" + smsRecipient.getText().toString()));
            smsIntent.putExtra("sms_body", smsMessage.getText().toString());
            startActivity(smsIntent);
        });
    }
}
