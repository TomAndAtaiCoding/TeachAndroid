package com.example.teachapp.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.teachapp.MainActivity;
import com.example.teachapp.R;

public class LayoutRelativeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layouts_relative);

        // button that sends the user back to the main page.
        final Button layoutNextRedirect = findViewById(R.id.button_layouts_relative_next);

        layoutNextRedirect.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
    }
}