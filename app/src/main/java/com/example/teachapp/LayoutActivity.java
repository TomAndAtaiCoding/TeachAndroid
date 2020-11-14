package com.example.teachapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layouts_intro);

        final Button layoutNextRedirect = findViewById(R.id.button_layouts_intro_next);

        layoutNextRedirect.setOnClickListener(v -> startActivity(new Intent(this, LayoutLinearActivity.class)));
    }
}
