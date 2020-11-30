package com.example.teachapp.dialogues;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.time.LocalDate;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.teachapp.R;

import org.w3c.dom.Text;

public class DialogueActivity extends AppCompatActivity {

    int chosenHour, chosenMinute, chosenYear, chosenMonth, chosenDay;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogue);

        // button that launches an alert dialogue.
        final Button dialogueAlert = findViewById(R.id.button_dialogue_alert);
        // button that launches a date picker dialogue.
        final Button dialogueDate = findViewById(R.id.button_dialogue_date);
        // button that launches a time picker dialogue
        final Button dialogueTime = findViewById(R.id.button_dialogue_time);
        // shows the choices from the dialogues.
        final TextView output = findViewById(R.id.text_dialogue_output);
        // standard calendar that keeps all time/date variables.
        final Calendar calendar = Calendar.getInstance();

        dialogueAlert.setOnClickListener(v -> {
            //  builder for the alert dialogue.
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            // sets the message for the alert dialogue.
            alertBuilder.setMessage("Did you understand this tutorial?");
            // sets the text and function of the positive button of the alert dialogue.
            alertBuilder.setPositiveButton("YES", (dialog, which) -> {
                output.setText("I understood everything.");
            });
            // sets the text and function of the negative button of the alert dialogue.
            alertBuilder.setNegativeButton("NO", (dialog, which) -> {
                output.setText("I understood nothing.");
            });
            // sets the text and function of the neutral button of the alert dialogue.
            alertBuilder.setNeutralButton("WHAT?", (dialog, which) -> {
                output.setText("huh?");
            });
            // builds alert dialogue. basically creates an instance of the dialogue that was coded in the builder.
            AlertDialog alertDialog = alertBuilder.create();
            // shows the dialogue.
            alertDialog.show();
        });

        dialogueDate.setOnClickListener(v -> {
            // creates a new date picker dialogue.
            DatePickerDialog datePicker = new DatePickerDialog(
                DialogueActivity.this,
                (view, year, month, dayOfMonth) -> {
                    // saves the year chosen in the dialogue.
                    chosenYear = year;
                    // saves the month chosen in the dialogue.
                    chosenMonth = month;
                    // saves the day of the month chosen in the dialogue.
                    chosenDay = dayOfMonth;
                    // changes the calendar to save the variables.
                    calendar.set(chosenYear, chosenMonth, chosenDay);
                    output.setText(DateFormat.format("dd/MM/yyyy", calendar)); },
                // sets the current date for the date picker, so it'll show today as the default value.
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            );
            // shows the dialogue.
            datePicker.show();
        });

        dialogueTime.setOnClickListener(v -> {
            // creates a new time picker dialogue.
            TimePickerDialog timePicker = new TimePickerDialog(
                    DialogueActivity.this,
                    (view, hourOfDay, minute) -> {
                        // saves the hour chosen in the dialogue.
                        chosenHour = hourOfDay;
                        // saves the minute chosen in the dialogue.
                        chosenMinute = minute;
                        // changes the calendar to save the values.
                        calendar.set(0, 0, 0, chosenHour, chosenMinute);
                        output.setText(DateFormat.format("kk:mm", calendar)); },
                // sets a base time for the time picker, so it'll show midnight as the default value.
                0,
                0,
                true
            );
            // saves the chosen time, so the next time the dialogue opens the previous values would be shown.
            timePicker.updateTime(chosenHour, chosenMinute);
            // shows the time picker.
            timePicker.show();
        });
    }
}