package com.example.teachapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ListViewActivity extends AppCompatActivity {

    EditText m_editList;
    Button m_submitList;
    ListView m_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        m_editList = findViewById(R.id.edit_list);
        m_submitList = findViewById(R.id.button_submit_conversation);

        m_submitList.setOnClickListener((v) -> {
            m_listView = new ListView(this);
            m_listView.setAdapter(new ArrayAdapter<String>(
                    this,
                    R.layout.listrow_listview_example,
                    ",".split(String.valueOf(m_editList.getText()))));


        });
    }






}
