package com.example.teachapp.intents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachapp.R;

public class EmailIntentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents_email);

        final EditText emailRecipient= findViewById(R.id.edit_intents_email_recipient);
        final EditText emailSubject= findViewById(R.id.edit_intents_email_subject);
        final EditText emailBody= findViewById(R.id.edit_intents_email_message);
        final Button emailCancel = findViewById(R.id.button_intents_email_cancel);
        final Button emailSend = findViewById(R.id.button_intents_email_send);

        emailCancel.setOnClickListener(v -> startActivity(new Intent(this, IntentActivity.class)));

        emailSend.setOnClickListener(v -> {
            Intent email = new Intent(Intent.ACTION_SENDTO);
            String emailContent = "mailto:" + Uri.encode(emailRecipient.getText().toString()) + "?"
                    + "&subject=" + Uri.encode(emailSubject.getText().toString())
                    + "&body=" + Uri.encode(emailBody.getText().toString());
            email.setData(Uri.parse(emailContent));
            EmailIntentActivity.this.startActivity(email);
        });
    }
}
