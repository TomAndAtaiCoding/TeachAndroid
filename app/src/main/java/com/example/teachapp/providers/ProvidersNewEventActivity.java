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

        // gets the event's title.
        final EditText eventTitle = findViewById(R.id.edit_providers_new_event_title);
        // gets the event's description.
        final EditText eventDescription = findViewById(R.id.edit_providers_new_event_description);
        // button that launches a dialogue that gets the event's starting time.
        final Button eventStartTime = findViewById(R.id.button_providers_new_event_start);
        // button that launches a dialogue that gets the event's ending time.
        final Button eventEndTime = findViewById(R.id.button_providers_new_event_end);
        // gets the event's location.
        final EditText eventLocation = findViewById(R.id.edit_providers_new_event_location);
        // button that saves the event.
        final Button saveEvent = findViewById(R.id.button_providers_new_event_save);

        eventStartTime.setOnClickListener(v -> {
            // sets a new time picker dialogue for the event's starting time.
            TimePickerDialog timePicker = new TimePickerDialog(ProvidersNewEventActivity.this, (view, hourOfDay, minute) -> {
                Calendar calendar = Calendar.getInstance();
                startHour = hourOfDay;
                startMinute = minute;
                calendar.set(0, 0, 0, startHour, startMinute);
                // displays the chosen starting time in the button's text.
                String newStartText = eventStartTime.getText() + "(" + DateFormat.format("kk:mm", calendar) + ")";
                eventStartTime.setText(newStartText);
            }, 0, 0, true);
            timePicker.updateTime(startHour, startMinute);
            timePicker.show();
        });

        eventEndTime.setOnClickListener(v -> {
            // sets a new time picker dialogue for the event's ending time.
            TimePickerDialog timePicker = new TimePickerDialog(ProvidersNewEventActivity.this, (view, hourOfDay, minute) -> {
                Calendar calendar = Calendar.getInstance();
                endHour = hourOfDay;
                endMinute = minute;
                calendar.set(0, 0, 0, endHour, endMinute);
                // displays the chosen ending time in the button's text.
                String newEndText = eventEndTime.getText() + "(" + DateFormat.format("kk:mm", calendar) + ")";
                eventEndTime.setText(newEndText);
            }, 0, 0, true);
            timePicker.updateTime(endHour, endMinute);
            timePicker.show();
        });

        saveEvent.setOnClickListener(v -> {
            // this handles the event creation.
            ContentResolver eventResolver = getContentResolver();
            // calendar that saves the starting time.
            Calendar startTime = Calendar.getInstance();
            startTime.set(startTime.get(Calendar.YEAR), startTime.get(Calendar.MONTH), startTime.get(Calendar.DAY_OF_MONTH), startHour, startMinute);
            // calendar that saves the ending time.
            Calendar endTime = Calendar.getInstance();
            endTime.set(endTime.get(Calendar.YEAR), endTime.get(Calendar.MONTH), endTime.get(Calendar.DAY_OF_MONTH), endHour, endMinute);
            // this saves the event details (title, description, starting time, ending time, location).
            ContentValues eventValues = new ContentValues();
            eventValues.put(CalendarContract.Events.DTSTART, startTime.getTimeInMillis());
            eventValues.put(CalendarContract.Events.DTEND, endTime.getTimeInMillis());
            eventValues.put(CalendarContract.Events.TITLE, eventTitle.getText().toString());
            eventValues.put(CalendarContract.Events.DESCRIPTION, eventDescription.getText().toString());
            eventValues.put(CalendarContract.Events.EVENT_LOCATION, eventLocation.getText().toString());
            // sets the event to be in the first account on the phone.
            eventValues.put(CalendarContract.Events.CALENDAR_ID, 1);
            // sets the event to be in the default timezone.
            eventValues.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
            // saves the event.
            Uri tempUri = eventResolver.insert(CalendarContract.Events.CONTENT_URI, eventValues);
            // sets an alert dialogue that notifies the user that the event was saved.
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            alertBuilder.setMessage("Event saved!");
            alertBuilder.setPositiveButton("OK", (dialog, which) -> {
                // sends the user back to the main page once the event has been saved.
                startActivity(new Intent(this, MainActivity.class));
            });
            AlertDialog dialog = alertBuilder.create();
            dialog.show();
        });
    }
}
