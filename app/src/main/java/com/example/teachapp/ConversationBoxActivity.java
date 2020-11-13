package com.example.teachapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class ConversationBoxActivity extends AppCompatActivity {

    // We use a naming convention where m_ signifies a variable is a member of a class not a
    // local variable in a function
    Context m_context = this;

    // To get the objects for each view:
    //  Use the builtin Activity method findViewById to get the basic view object:
    //  as this is the same method for all views, this will only returns a basic common denominator
    //  version of the object. To use it as a button/switch/text view/etc., we need to case it
    //  complex Button object

    // To get each view, we may use the builtin activity method findById, and access the id
    // we set in the XML code using the R.id class.
    Button m_button;
    EditText m_edit;
    TextView m_text;
    Switch m_switch;



    // On create runs when the activity is fiest created. We can use it to configure buttons, views,
    // And so on.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_box);

        m_button = findViewById(R.id.button_submit_conversation);
        m_edit = findViewById(R.id.edit_text);
        m_text = findViewById(R.id.text_Output);
        m_switch = findViewById(R.id.switch_explanation);

        // The following is three versions of the same code, each written a bit differently
        // In a real project, you would, of course, need to write only one of them,
        // but for the sake of learning and since setting the same thing three times doesn't
        // have any side effects here, we'll use and show all of them. First:

        // Each button  has a built in method to set the action it will take when pressed,
        // called "setOnClickListener". It takes a single parameter of the type "OnClickListener" -
        // which, of course, indicates an object listening and waiting for the button to be clicked,
        // them doing an action. Im reality this type is not even a class but an interface.
        // Representing a single abstract "onClick" action. The thing it will do in our implementation
        // Will be what the button will do when pressed. Now, while trying to implement the interface
        // would normally require a separate class, java has a fun little feature called
        // "Anonymous Inheritance", which allows you to have an object extending a class
        // or implementing an interface directly. This is why the curly braces { } after the
        // OnClickListener constructors.

        // So in this block, we implement the onclick method to set the text of our TextView
        // (m_text) using its setText(String) method, to the current text in the EditText, named m_edit,
        // which we get using the getText() method.

        m_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_text.setText(m_edit.getText());
            }
        });


        // Second:
        // Just a quick fun thing. While anonymous inheritance is fun an much faster than fully
        // creating classes, java (starting from version 8) allows an even quicker shortcut
        // when using interfaces with only one method, called lambda expressions.
        // Specifically, rather than open curly braces {} and writing a full mrthod, we can use
        // a cleaner, smaller syntax with parenthesis () and arrows: (params) -> result.
        //(params) will be a comma separated list of names, without types (since the types are
        // inferred by the interface method)
        // And result will be the value to return, or the action to take if the method returns void.
        // In our example:

        m_button.setOnClickListener((view) -> m_text.setText(m_edit.getText()));

        // Clean! Isn't it? Notice that the method takes a view, which is in fact the button itself,
        // but we do not use it here.
        // Now, third:

        // While as you can see, using variables results in much cleaner code, it's good to
        // understand how to get the views in one line as well.
        // You see, the method findById returns a very basic View object, same for buttons, switches
        // and EditTexts, but you want to convert it to its full capacity. When you declare a
        // variable, the program will try to do this automatically, with the class you declared.
        // But for a one-liner this you  will need  feature called casting, with a (type)(value) syntax.
        // For example, (Button)findViewById(R.id.submit) will take The basic button object given
        // by the id 'submit' and will then convert it to the actual Button object.
        // (EditText)findViewById(R.id.box) will convert the view with id 'box' to EditText.
        //
        // Notice: (EditText)findViewById(R.id.box).getText() will try to convert
        // findViewById(R.id.box).getText() to an EditText object. To call the getText() method for
        // the EditText object you will need an extra parenthesis, like so:
        // ((EditText)findViewById(R.id.box)).getText()
        // For our final product:
        ((Button)findViewById(R.id.button_submit_conversation))
                .setOnClickListener(
                        (view) -> ((TextView)findViewById(R.id.text_Output))
                                .setText(((EditText)findViewById(R.id.edit_text)).getText()));
        //It is much uglier though - generally try to use variables.




        // This is the product-code switch. Not really covered in this lesson - but I bet you
        // get the gist!
        findViewById(R.id.switch_explanation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(m_context, ConversationExplanationActivity.class));
            }
        });
    }

}