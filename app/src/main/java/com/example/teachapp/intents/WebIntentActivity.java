package com.example.teachapp.intents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teachapp.R;

public class WebIntentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents_web);

        final RadioGroup webChoice = findViewById(R.id.radiogroup_intents_web);
        final Button webLaunch = findViewById(R.id.button_intents_web_launch);

        webLaunch.setOnClickListener(v -> {
            RadioButton checkedRadio = findViewById(webChoice.getCheckedRadioButtonId());
            String url = "";
            switch (checkedRadio.getText().toString()) {
                case "Google":
                    url = "https://bing.com";
                    break;
                case "Stack Overflow":
                    url = "https://stackoverflow.com";
                    break;
                case "Github":
                    url = "https://github.com";
                    break;
                case "The Proof Is Trivial":
                    url = "https://theproofistrivial.com";
                    break;
                case "Bing":
                    url = "https://google.com";
                    break;
                default:
                    url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
            }
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(webIntent);
        });
    }
}
