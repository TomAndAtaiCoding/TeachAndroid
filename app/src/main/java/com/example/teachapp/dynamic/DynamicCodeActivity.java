package com.example.teachapp.dynamic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.teachapp.R;

public class DynamicCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // the dynamic output textview.
        TextView output = new TextView(this);
        // all views must have an id.
        output.setId(ViewCompat.generateViewId());
        // layout parameters position and size the view, i.e. what you would set in xml.
        ConstraintLayout.LayoutParams layoutParams =
                new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        // the drawable allows us to change the background color.
        GradientDrawable border = new GradientDrawable();
        // makes the drawable the same shape as the textbox.
        border.setShape(GradientDrawable.RECTANGLE);

        // gets the new width.
        EditText newWidth = new EditText(this);
        newWidth.setId(ViewCompat.generateViewId());
        ConstraintLayout.LayoutParams newWidthParams = new ConstraintLayout.LayoutParams(860, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        // view parameters.
        newWidthParams.topMargin = 18;
        newWidthParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        newWidthParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        newWidthParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        // attaches the parameters to the view.
        newWidth.setLayoutParams(newWidthParams);
        newWidth.setHint("Width:");

        // changes the width of the outputted view each time the text is changed.
        newWidth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // the width can't be something that's not a number. thus, if the new width isn't a number the app would throw an exception.
                // this makes it so the view doesn't change if the new width is not a number.
                try {
                    // changes the view's width.
                    output.setWidth(Integer.parseInt(newWidth.getText().toString()));
                }
                catch (Exception e) {

                }
            }
        });

        // gets the new height.
        EditText newHeight = new EditText(this);
        newHeight.setId(ViewCompat.generateViewId());
        ConstraintLayout.LayoutParams newHeightParams = new ConstraintLayout.LayoutParams(860, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        // view parameters.
        newHeightParams.topMargin = 16;
        newHeightParams.topToBottom = newWidth.getId();
        newHeightParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        newHeightParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        // attaches the parameters to the view.
        newHeight.setLayoutParams(newHeightParams);
        newHeight.setHint("Height:");

        // changes the height of the outputted view every time the text is changed.
        newHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // the height can't be something that's not a number. thus, if the new height isn't a number the app would throw an exception.
                // this makes it so the view doesn't change if the new height is not a number.
                try {
                    output.setHeight(Integer.parseInt(newHeight.getText().toString()));
                }
                catch (Exception e) {

                }
            }
        });

        // gets the new text.
        EditText newText = new EditText(this);
        newText.setId(ViewCompat.generateViewId());
        ConstraintLayout.LayoutParams newTextParams = new ConstraintLayout.LayoutParams(860, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        // view parameters.
        newTextParams.topMargin = 16;
        newTextParams.topToBottom = newHeight.getId();
        newTextParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        newTextParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        // attaches the parameters to the view.
        newText.setLayoutParams(newTextParams);
        newText.setHint("Text:");

        // changes the text of the outputted view every time the text is changed.
        newText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // makes sure that nothing bad happens.
                try {
                    output.setText(newText.getText().toString());
                }
                catch (Exception e) {
                    output.setText("A problem occured.");
                }
            }
        });

        // button that prompts the user to select a background color for the outputted view.
        Button newBackground = new Button(this);
        newBackground.setId(ViewCompat.generateViewId());
        ConstraintLayout.LayoutParams newBackgroundParams = new ConstraintLayout.LayoutParams(860, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        // view parameters.
        newBackgroundParams.topMargin = 16;
        newBackgroundParams.topToBottom = newText.getId();
        newBackgroundParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        newBackgroundParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        // attaches the parameters to the view.
        newBackground.setLayoutParams(newBackgroundParams);
        newBackground.setText("background color");

        // launches an alert dialogue with multiple selection options for the user to choose from.
        newBackground.setOnClickListener(v -> {
            // base of the dialogue.
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // sets the options that would be presented in the dialogue.
            CharSequence[] items = {"White", "Black", "Gray", "Blue", "Purple", "Green"};
            // attaches the list of options to the dialogue.
            builder.setItems(items, (dialog, which) -> {
                switch (which) {
                    case 0:
                        border.setColor(Color.parseColor("#FFFFFF"));
                    break;
                    case 1:
                        border.setColor(Color.parseColor("#000000"));
                    break;
                    case 2:
                        border.setColor(Color.parseColor("#454545"));
                    break;
                    case 3:
                        border.setColor(Color.parseColor("#0000FF"));
                    break;
                    case 4:
                        border.setColor(Color.parseColor("#800080"));
                    break;
                    case 5:
                        border.setColor(Color.parseColor("#00FF00"));
                    break;
                }
            });
            // creates an instance of the dialogue.
            AlertDialog dialogue = builder.create();
            // shows the dialogue.
            dialogue.show();
        });

        // sets the parameters for the outputted view.
        layoutParams.topMargin = 64;
        layoutParams.topToBottom = newBackground.getId();
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        output.setTextSize(20);
        output.setTextAlignment(ConstraintLayout.TEXT_ALIGNMENT_CENTER);
        output.setBackground(border);
        // attaches the parameters to the view.
        output.setLayoutParams(layoutParams);

        // button that generates the view.
        Button addView = new Button(this);
        addView.setId(ViewCompat.generateViewId());
        ConstraintLayout.LayoutParams addViewParams = new ConstraintLayout.LayoutParams(860, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        // view parameters.
        addViewParams.bottomMargin = 24;
        addViewParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        addViewParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        addViewParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        // attaches the parameters to the view.
        addView.setLayoutParams(addViewParams);
        addView.setText("generate view");

        // the layout that everything will fit into.
        final ConstraintLayout layout = new ConstraintLayout(this);
        layout.setLayoutParams(
                new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));

        // when the button to generate the view is clicked, this makes the view generate.
        addView.setOnClickListener(v -> {
            // if the view is already generated and you try to generate it again, the app would throw an exception.
            // this makes it so that if the view is generated, it will not generate it again.
            try {
                // adds the view into the layout.
                layout.addView(output);
            }
            catch (Exception e) {

            }
        });

        // adds the rest of the views into the layout.
        layout.addView(newWidth);
        layout.addView(newHeight);
        layout.addView(newText);
        layout.addView(newBackground);
        layout.addView(addView);

        // shows the layout.
        setContentView(layout);
    }
}
