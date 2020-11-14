package com.example.teachapp.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.teachapp.R;

public class ListViewActivity extends AppCompatActivity {

    EditText m_editList;
    Button m_btnSubmit;
    ListView m_listExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        // Initialize the view variables, so we can use them later
        m_editList = findViewById(R.id.edit_list);
        m_btnSubmit = findViewById(R.id.button_submit_list);
        m_listExample = findViewById(R.id.list_example);


        // Setting the contents of the list view by the button press
        // Remember, when we want a long
        m_btnSubmit.setOnClickListener((v) ->
            // The setAdapter method set the values of the ListView according to a given array
            // The array is given as part of an "ArrayAdapter" object
            // Which is constructed with the following parameters
            m_listExample.setAdapter(new ArrayAdapter<String>(
                    // The activity we are acting from/
                    this,
                    // The ID of a layout containing the text element which will
                    // construct each row. NOTE - First that is the *id* rather than
                    // the layout itself, and second, that the file does not contain a
                    // full layout, but rather just a single element (e.g TextView),
                    // without any parents,
                    R.layout.listrow_listview_example,
                    // The array to fill the ListView with.
                    // Here, we just split the text in each comma,
                    // so you can create your own lists and have them
                    // show up in the view.
                    String.valueOf(m_editList.getText()).split(","))
            )
        );



    }






}
