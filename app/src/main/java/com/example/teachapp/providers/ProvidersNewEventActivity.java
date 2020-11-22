package com.example.teachapp.providers;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachapp.MainActivity;
import com.example.teachapp.R;
import com.example.teachapp.dialogues.DialogueActivity;

import java.util.Calendar;
import java.util.TimeZone;

public class ProvidersNewEventActivity extends AppCompatActivity {

    int startHour, startMinute, endHour, endMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_providers_new_event);

        final EditText eventTitle = findViewById(R.id.edit_providers_new_event_title);
        final EditText eventDescription = findViewById(R.id.edit_providers_new_event_description);
        final Button eventStartTime = findViewById(R.id.button_providers_new_event_start);
        final Button eventEndTime = findViewById(R.id.button_providers_new_event_end);
        final EditText eventLocation = findViewById(R.id.edit_providers_new_event_location);
        final Button saveEvent = findViewById(R.id.button_providers_new_event_save);

        eventStartTime.setOnClickListener(v -> {
            TimePickerDialog timePicker = new TimePickerDialog(ProvidersNewEventActivity.this, (view, hourOfDay, minute) -> {
                Calendar calendar = Calendar.getInstance();
                startHour = hourOfDay;
                startMinute = minute;
                calendar.set(0, 0, 0, startHour, startMinute);
                String newStartText = eventStartTime.getText() + "(" + DateFormat.format("kk:mm", calendar) + ")";
                eventStartTime.setText(newStartText);
            }, 0, 0, true);
            timePicker.updateTime(startHour, startMinute);
            timePicker.show();
        });

        eventEndTime.setOnClickListener(v -> {
            TimePickerDialog timePicker = new TimePickerDialog(ProvidersNewEventActivity.this, (view, hourOfDay, minute) -> {
                Calendar calendar = Calendar.getInstance();
                endHour = hourOfDay;
                endMinute = minute;
                calendar.set(0, 0, 0, endHour, endMinute);
                String newEndText = eventEndTime.getText() + "(" + DateFormat.format("kk:mm", calendar) + ")";
                eventEndTime.setText(newEndText);
            }, 0, 0, true);
            timePicker.updateTime(endHour, endMinute);
            timePicker.show();
        });

        saveEvent.setOnClickListener(v -> {
            ContentResolver eventResolver = getContentResolver();
            Calendar startTime = Calendar.getInstance();
            startTime.set(startTime.get(Calendar.YEAR), startTime.get(Calendar.MONTH), startTime.get(Calendar.DAY_OF_MONTH), startHour, startMinute);
            Calendar endTime = Calendar.getInstance();
            endTime.set(endTime.get(Calendar.YEAR), endTime.get(Calendar.MONTH), endTime.get(Calendar.DAY_OF_MONTH), endHour, endMinute);
            ContentValues eventValues = new ContentValues();
            eventValues.put(CalendarContract.Events.DTSTART, startTime.getTimeInMillis());
            eventValues.put(CalendarContract.Events.DTEND, endTime.getTimeInMillis());
            eventValues.put(CalendarContract.Events.TITLE, eventTitle.getText().toString());
            eventValues.put(CalendarContract.Events.DESCRIPTION, eventDescription.getText().toString());
            eventValues.put(CalendarContract.Events.EVENT_LOCATION, eventLocation.getText().toString());
            eventValues.put(CalendarContract.Events.CALENDAR_ID, 1);
            eventValues.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
            Uri tempUri = eventResolver.insert(CalendarContract.Events.CONTENT_URI, eventValues);
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            alertBuilder.setMessage("Event saved!");
            alertBuilder.setPositiveButton("OK", (dialog, which) -> {
                startActivity(new Intent(this, MainActivity.class));
            });
            AlertDialog dialog = alertBuilder.create();
            dialog.show();
        });
    }
}
