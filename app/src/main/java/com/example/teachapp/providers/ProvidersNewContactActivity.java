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

        final EditText contactName = findViewById(R.id.edit_providers_new_contact_name);
        final EditText contactEmail = findViewById(R.id.edit_providers_new_contact_email);
        final EditText contactPhone = findViewById(R.id.edit_providers_new_contact_phone);
        final Button contactSave = findViewById(R.id.button_providers_new_contact_save);

        contactSave.setOnClickListener(v -> {
            String name = contactName.getText().toString();
            String email = contactEmail.getText().toString();
            String phone = contactPhone.getText().toString();
            ArrayList<ContentProviderOperation> operationList = new ArrayList<ContentProviderOperation>();
            ContentProviderOperation.Builder contactOperation =
                    ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                            .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null);
            operationList.add(contactOperation.build());
            contactOperation = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name);

            operationList.add(contactOperation.build());

            contactOperation =
                    ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
                            .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);

            operationList.add(contactOperation.build());

            contactOperation =
                    ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.Email.ADDRESS, email)
                            .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME);

            contactOperation.withYieldAllowed(true);
            operationList.add(contactOperation.build());

            try {
                getContentResolver().applyBatch(ContactsContract.AUTHORITY, operationList);
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setMessage("Contact saved!");
                alertBuilder.setPositiveButton("OK", (dialog, which) -> {
                    startActivity(new Intent(this, MainActivity.class));
                });
                AlertDialog dialog = alertBuilder.create();
                dialog.show();
            } catch (Exception e) {
                System.out.println("nope.");
                e.printStackTrace();
            }
        });
    }
}
