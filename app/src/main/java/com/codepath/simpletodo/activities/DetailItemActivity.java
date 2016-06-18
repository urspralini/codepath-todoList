package com.codepath.simpletodo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.codepath.simpletodo.R;
import com.codepath.simpletodo.models.Priority;
import com.codepath.simpletodo.models.Status;
import com.codepath.simpletodo.models.TodoItem;

public class DetailItemActivity extends AppCompatActivity {

    private TodoItem todoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        todoItem = new TodoItem();
        setContentView(R.layout.activity_detail_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //configure spinner status
        Spinner spinnerStatus = (Spinner)findViewById(R.id.spinnerStatus);
        ArrayAdapter<CharSequence> statusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        for (Status status : Status.values()) {
            statusAdapter.add(status.toString());
        }
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(statusAdapter);
        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                final String itemAtPosition = (String) adapterView.getItemAtPosition(position);
                String statusStr = itemAtPosition;
                Status status;
                switch (statusStr){
                    case "In Progress":
                        status = Status.IN_PROGRESS;
                        break;
                    case "Done":
                        status = Status.DONE;
                        break;
                    default:
                        status = Status.NEW;
                }
                todoItem.setStatus(status);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //configure spinner status
        Spinner spinnerPriority = (Spinner)findViewById(R.id.spinnerPriority);
        ArrayAdapter<CharSequence> priorityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        for (Priority priority : Priority.values()) {
            priorityAdapter.add(priority.toString());
        }
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPriority.setAdapter(priorityAdapter);
        spinnerPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                final String itemAtPosition = (String) adapterView.getItemAtPosition(position);
                String priorityStr = itemAtPosition;
                Priority priority;
                switch (priorityStr){
                    case "High":
                        priority = Priority.HIGH;
                        break;
                    case "Medium":
                        priority = Priority.MEDIUM;
                        break;
                    default:
                        priority = Priority.LOW;
                }
                todoItem.setPriority(priority);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
