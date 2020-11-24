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

        // gets the sms recipient.
        final EditText smsRecipient = findViewById(R.id.edit_intents_sms_recipient);
        // gets the sms message.
        final EditText smsMessage = findViewById(R.id.edit_intents_sms_message);
        // button that sends the sms.
        final Button smsSend = findViewById(R.id.button_intents_sms_send);

        smsSend.setOnClickListener(v -> {
            // creates a new intent to send an sms.
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
            // puts the data into the intent.
            smsIntent.setData(Uri.parse("smsto:" + smsRecipient.getText().toString()));
            // adds the message to the data.
            smsIntent.putExtra("sms_body", smsMessage.getText().toString());
            startActivity(smsIntent);
        });
    }
}
