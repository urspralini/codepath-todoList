package com.codepath.simpletodo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.codepath.simpletodo.R;

public class DetailItemActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Spinner spinnerStatus = (Spinner)findViewById(R.id.spinnerStatus);
        final ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.status_arrays,
                R.layout.my_spinner_textview);
        statusAdapter.setDropDownViewResource(R.layout.my_spinner_textview);
        spinnerStatus.setAdapter(statusAdapter);
        Spinner spinnerPriority = (Spinner)findViewById(R.id.spinnerPriority);
        final ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter.createFromResource(this,
                R.array.priority_arrays,
                R.layout.my_spinner_textview);
        priorityAdapter.setDropDownViewResource(R.layout.my_spinner_textview);
        spinnerPriority.setAdapter(priorityAdapter);
    }

}
