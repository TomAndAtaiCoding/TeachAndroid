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

        // gets the email recipient.
        final EditText emailRecipient= findViewById(R.id.edit_intents_email_recipient);
        // gets the email subject.
        final EditText emailSubject= findViewById(R.id.edit_intents_email_subject);
        // gets the  email body.
        final EditText emailBody= findViewById(R.id.edit_intents_email_message);
        // button that sends the user back to the intents page, thus cancelling the email.
        final Button emailCancel = findViewById(R.id.button_intents_email_cancel);
        // button that sends the constructed email.
        final Button emailSend = findViewById(R.id.button_intents_email_send);

        emailCancel.setOnClickListener(v -> startActivity(new Intent(this, IntentActivity.class)));

        emailSend.setOnClickListener(v -> {
            // creates a new intent to send the email.
            Intent email = new Intent(Intent.ACTION_SENDTO);
            // sets the content of the intent to the email content from the activity.
            String emailContent = "mailto:" + Uri.encode(emailRecipient.getText().toString()) + "?"
                    + "&subject=" + Uri.encode(emailSubject.getText().toString())
                    + "&body=" + Uri.encode(emailBody.getText().toString());
            // puts the data in the intent.
            email.setData(Uri.parse(emailContent));
            startActivity(email);
        });
    }
}
