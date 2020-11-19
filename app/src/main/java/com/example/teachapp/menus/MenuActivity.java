package com.example.teachapp.menus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.teachapp.R;

public class MenuActivity extends AppCompatActivity {

    TextView m_text_action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        m_text_action = findViewById(R.id.text_menu_action);

        // As you'll see later on, we will initialize a specific "Context Menu", which is a
        // built-in menu you can customize
        // Giving a view to the function "registerForContextMenu" makes that context menu
        // appear whenever that view (here, the textbox) is pressed for two seconds or longer.
        registerForContextMenu(m_text_action);
    }


    // This function initializes the options menu; this is the menu that's opened fro m the top bar,
    // with the 3 dots.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // The "MenuInflater", that you can get by using the function getMenuInflater
        // can take menu and use them to initialize the ContextMenu or OptionsMenu objects,
        // according to the method it is used in.
        getMenuInflater().inflate(R.menu.menu_example, menu);

        // The return value determines if the menu will appear.
        // You may use this to make your menu conditionally appear based on settings
        // and other in-app events.
        return true;
    }


    // This function initializes context menus; this is the menu you will get from a long
    // press over an object, and the menu that will be associated wit objects given
    // to the built-in function registerForContextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // The "MenuInflater", that you can get by using the function getMenuInflater
        // can take menu and use them to initialize the ContextMenu or OptionsMenu objects,
        // according to the method it is used in.
        getMenuInflater().inflate(R.menu.menu_context_example, menu);
    }


    // Whenever an item from the Context Menu is selected,
    // this functions handles the selection.
    // The parameter is the menu item being selected menu item.
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        // We can distinguish between the different menu items based on their id;
        // the method getItemId built in to every view provides the id of the item selected,
        // and then we can compare it to each of the IDs of the menu items, and handle
        // the action accordingly.
        switch (item.getItemId()) {
            case R.id.meni_blue:
                m_text_action.setTextColor(Color.BLUE);
                break;
            case R.id.meni_red:
                m_text_action.setTextColor(Color.RED);
                break;
            case R.id.meni_green:
                m_text_action.setTextColor(Color.GREEN);
                break;

        }

        return onOptionsItemSelected(item);
    }

    // Whenever an item from the Options Menu is selected,
    // this functions handles the selection.
    // The parameter is the menu item being selected menu item.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);


        // We can distinguish between the different menu items based on their id;
        // the method getItemId built in to every view provides the id of the item selected,
        // and then we can compare it to each of the IDs of the menu items, and handle
        // the action accordingly.
        switch (item.getItemId()) {
            case R.id.meni_option_1:
                m_text_action.setText(R.string.meni_example_option_3_text);
                break;
            case R.id.meni_option_2:
                m_text_action.setText(R.string.meni_example_option_2_text);
                break;
            case R.id.meni_option_3:
                m_text_action.setText(R.string.meni_example_option_1_text);
                break;
        }

        return true;
    }
}
