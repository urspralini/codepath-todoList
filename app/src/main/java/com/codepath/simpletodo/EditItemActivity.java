package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    private EditText etEditItemText;
    private String itemText;
    private int itemPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etEditItemText = (EditText)findViewById(R.id.etEditItemText);
        //get the current text of the item from the intent
        final Intent intent = getIntent();
        itemText = intent.getExtras().getString(MainActivity.TEXT, "");
        //get item position
        itemPosition = intent.getExtras().getInt(MainActivity.POSITION, -1);
        etEditItemText.setText(itemText);
        //set cursor to the end of the text
        etEditItemText.setSelection(etEditItemText.getText().length());
        //set default focus to the edit text
        etEditItemText.requestFocus();
    }


    public void saveItemText(View view) {
        itemText = etEditItemText.getText().toString();
        Intent returnData = new Intent();
        returnData.putExtra(MainActivity.TEXT, itemText);
        returnData.putExtra(MainActivity.POSITION, itemPosition);
        setResult(RESULT_OK, returnData);
        this.finish();
    }
}
