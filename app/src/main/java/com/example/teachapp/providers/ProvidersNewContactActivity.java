package com.example.teachapp.providers;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachapp.MainActivity;
import com.example.teachapp.R;

import java.io.Console;
import java.util.ArrayList;

public class ProvidersNewContactActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_providers_new_contact);

        // gets the contact's name.
        final EditText contactName = findViewById(R.id.edit_providers_new_contact_name);
        // gets the contact's email.
        final EditText contactEmail = findViewById(R.id.edit_providers_new_contact_email);
        // gets the contact's phone.
        final EditText contactPhone = findViewById(R.id.edit_providers_new_contact_phone);
        // button that saves the contact.
        final Button contactSave = findViewById(R.id.button_providers_new_contact_save);

        contactSave.setOnClickListener(v -> {
            // saves the contact's name.
            String name = contactName.getText().toString();
            // saves the contact's email.
            String email = contactEmail.getText().toString();
            // saves the contact's phone.
            String phone = contactPhone.getText().toString();
            // the list of operations needed to create a contact.
            ArrayList<ContentProviderOperation> operationList = new ArrayList<ContentProviderOperation>();
            // first operation, sets the contact to be saved to the default account.
            ContentProviderOperation.Builder contactOperation =
                    ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                            .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null);
            // adds the operation to the list.
            operationList.add(contactOperation.build());
            // second operation that sets the name of the contact.
            contactOperation = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name);
            // adds the operation to the list.
            operationList.add(contactOperation.build());
            // third operation that sets the contact's phone.
            contactOperation =
                    ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
                            .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
            // adds the operation to the list.
            operationList.add(contactOperation.build());
            // fourth operation that sets the contact's email.
            contactOperation =
                    ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.Email.ADDRESS, email)
                            .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME);
            // adds the operation to the list.
            operationList.add(contactOperation.build());
            // tries to add the contact to the contact database.
            try {
                // performs the operations.
                getContentResolver().applyBatch(ContactsContract.AUTHORITY, operationList);
                // creates an alert dialogue that notifies that the contact was indeed saved.
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setMessage("Contact saved!");
                alertBuilder.setPositiveButton("OK", (dialog, which) -> {
                    // sends the user back to the main page after the contact was added.
                    startActivity(new Intent(this, MainActivity.class));
                });
                // creates an instance of the dialogue.
                AlertDialog dialog = alertBuilder.create();
                // shows the dialogue.
                dialog.show();
            } catch (Exception e) {
                System.out.println("nope.");
                e.printStackTrace();
            }
        });
    }
}
