package com.example.teachapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LayoutLinearActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layouts_linear);

        final Button layoutNextRedirect = findViewById(R.id.button_layouts_linear_next);

        layoutNextRedirect.setOnClickListener(v -> startActivity(new Intent(this, LayoutRelativeActivity.class)));
    }
}
