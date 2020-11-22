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

        final Button dialogueAlert = findViewById(R.id.button_dialogue_alert);
        final Button dialogueDate = findViewById(R.id.button_dialogue_date);
        final Button dialogueTime = findViewById(R.id.button_dialogue_time);
        final TextView output = findViewById(R.id.text_dialogue_output);
        final Calendar calendar = Calendar.getInstance();


        dialogueAlert.setOnClickListener(v -> {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            alertBuilder.setMessage("Did you understand this tutorial?");
            alertBuilder.setPositiveButton("YES", (dialog, which) -> {
                output.setText("I understood everything.");
            });
            alertBuilder.setNegativeButton("NO", (dialog, which) -> {
                output.setText("I understood nothing.");
            });
            alertBuilder.setNeutralButton("WHAT?", (dialog, which) -> {
                output.setText("huh?");
            });
            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.show();
        });

        dialogueDate.setOnClickListener(v -> {
                DatePickerDialog datePicker = new DatePickerDialog(DialogueActivity.this, (view, year, month, dayOfMonth) -> {
                    chosenYear = year;
                    chosenMonth = month;
                    chosenDay = dayOfMonth;
                    calendar.set(chosenYear, chosenMonth, chosenDay);
                    output.setText(DateFormat.format("dd/MM/yyyy", calendar));
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePicker.show();
        });

        dialogueTime.setOnClickListener(v -> {
            TimePickerDialog timePicker = new TimePickerDialog(DialogueActivity.this, (view, hourOfDay, minute) -> {
                chosenHour = hourOfDay;
                chosenMinute = minute;
                calendar.set(0, 0, 0, chosenHour, chosenMinute);
                output.setText(DateFormat.format("kk:mm", calendar));
            }, 0, 0, true);
            timePicker.updateTime(chosenHour, chosenMinute);
            timePicker.show();
        });
    }
}