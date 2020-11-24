package com.example.teachapp.preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.teachapp.MainActivity;
import com.example.teachapp.R;

public class SharedPreferencesActivity extends AppCompatActivity {

    RadioGroup m_radgrFavoriteNumber;
    EditText m_editFavoriteSock;
    CheckBox m_checkHappy;
    EditText m_editAge;
    Button m_buttonSave;

    // Declare the shared preferences object - this will act as a sort of dictionary
    // which will keep data we put in it persistently - that is, beyond app closure, in a file.
    // Really, the object could be initialized here as well, but we put it in onCreate
    // for consistency sake.
    SharedPreferences m_saveFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        m_radgrFavoriteNumber = findViewById(R.id.radgr_shared_pref);
        m_editFavoriteSock = findViewById(R.id.edit_sp_socks);
        m_editAge = findViewById(R.id.edit_sp_age);
        m_checkHappy = findViewById(R.id.check_sp_happy);
        m_buttonSave = findViewById(R.id.button_sp_save);

        // Initialize the SharedPreferences. Note that this is the method getSharedPreferences,
        // Rather than the constructor SharedPreferences, which cannot be used directly.
        // The first parameter specifies a name for your "dictionaty". That way, you can create
        // multiple different dictionaries under different names and access them separately.
        // The second parameter is an int that specifies exactly how the SharedPreferences file will
        // be written to. You may look at the various Context.MODE_ fields to see the various
        // options.
        m_saveFile = getSharedPreferences(
                "ExampleSaveFolder", Context.MODE_PRIVATE);

        // We recommend using variables for your keys, so you won't have typos resulting
        // in non-matching get and set keys.
        String ageKey = "age";
        String sockKey = "sock";
        String happyKey = "happy";
        String radioKey = "radio_iD";


        // Here, we get the info from the SharedPreferences object. The method will always be
        // get<Type>(String key, <Type> default). The key specifies which value you are getting or
        // setting, and is always a string. You also
        // have specify a default value, for the case there is no value under your specified key.
        // here, we use the defaults as special values to indicate an "unset" result, and then check
        // against them to make sure the values were the saved ones and not the defaults.

        int age = m_saveFile.getInt(ageKey, -1);
        String favoriteSock = m_saveFile.getString(sockKey, null);
        boolean happy = m_saveFile.getBoolean(happyKey, false);
        int radioID = m_saveFile.getInt(radioKey, -1);

        if (age != -1) {
            m_editAge.setText(String.valueOf(age));
        }

        if (favoriteSock != null) {
            m_editFavoriteSock.setText(favoriteSock);
        }

        if (happy) {
            m_checkHappy.setChecked(happy);
        }

        if (radioID != -1) {
            ((RadioButton)findViewById(radioID)).setChecked(true);
        }


        m_buttonSave.setOnClickListener((view) -> {
            // You can't edit the SharedPreferences directly, but rather need to
            // use its "Editor" object, which you can get from the "edit()" method/
            SharedPreferences.Editor prefEditor = m_saveFile.edit();

            // The method here is always put<Type>(String key, <Type> value). Pretty self
            // explanatory.
            prefEditor.putInt(radioKey, m_radgrFavoriteNumber.getCheckedRadioButtonId());
            prefEditor.putString(sockKey, m_editFavoriteSock.getText().toString());
            prefEditor.putBoolean(happyKey, m_checkHappy.isChecked());

            // We surrounded the int parse with a try-catch block, to ignore non-number input.
            try {
                prefEditor.putInt(ageKey, Integer.parseInt(m_editAge.getText().toString()));
            } catch (Exception ignore) {}

            // The changes you made to the SharedPreferences will only be applied after a comm
            prefEditor.apply();

            startActivity(new Intent(this, MainActivity.class));

        });
    }


}
