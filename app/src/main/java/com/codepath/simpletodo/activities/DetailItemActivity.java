package com.codepath.simpletodo.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.models.TodoItem;

public class DetailItemActivity extends AppCompatActivity {

    private TodoItem mTodoItem;
    private Spinner mSpinnerStatus;
    private Spinner mSpinnerPriority;
    private EditText mItemName;
    private EditText mItemNotes;
    private DatePicker mDueDatePicker;
    private int mItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    private void populateFieldsForEdit(){

    }

}
