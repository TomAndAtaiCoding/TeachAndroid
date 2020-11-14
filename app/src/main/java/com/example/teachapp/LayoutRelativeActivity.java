package com.example.teachapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LayoutRelativeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layouts_relative);

        final Button layoutNextRedirect = findViewById(R.id.button_layouts_relative_next);

        layoutNextRedirect.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
    }
}