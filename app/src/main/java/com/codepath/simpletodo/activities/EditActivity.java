package com.codepath.simpletodo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.models.Priority;
import com.codepath.simpletodo.models.Status;
import com.codepath.simpletodo.models.TodoItem;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity {

    private TodoItem mTodoItem;
    private Spinner mSpinnerStatus;
    private Spinner mSpinnerPriority;
    private EditText mItemName;
    private EditText mItemNotes;
    private DatePicker mDueDatePicker;
    private int mItemPosition;
    private ArrayAdapter<CharSequence> mStatusAdapter;
    private ArrayAdapter<CharSequence> mPriorityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_activity_new_item);
        setContentView(R.layout.activity_edit_new__item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeUIElements();
        mTodoItem = getIntent().getParcelableExtra(MainActivity.TODO_ITEM_KEY);
        if(mTodoItem == null) {
            mTodoItem = new TodoItem();
        }else {
            populateFieldsForEdit();
        }
    }

    private void initializeUIElements() {
        mItemPosition = getIntent().getIntExtra(MainActivity.POSITION, -1);
        mItemName = (EditText) findViewById(R.id.etDetailItemText);
        mItemNotes = (EditText) findViewById(R.id.etDetailItemNotes);
        mDueDatePicker = (DatePicker)findViewById(R.id.datePicker);

        //configure spinner status
        mSpinnerStatus = (Spinner)findViewById(R.id.spinnerStatus);
        mStatusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        for (Status status : Status.values()) {
            mStatusAdapter.add(status.toString());
        }
        mStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerStatus.setAdapter(mStatusAdapter);

        //configure spinner priority
        mSpinnerPriority = (Spinner)findViewById(R.id.spinnerPriority);
        mPriorityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        for (Priority priority : Priority.values()) {
            mPriorityAdapter.add(priority.toString());
        }
        mPriorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerPriority.setAdapter(mPriorityAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_todo_detail, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                populateTodoItem();
                Intent returnData = new Intent();
                returnData.putExtra(MainActivity.TODO_ITEM_KEY, mTodoItem);
                returnData.putExtra(MainActivity.POSITION, mItemPosition);
                setResult(RESULT_OK, returnData);
                this.finish();
                return true;
            case R.id.action_cancel:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void populateTodoItem(){
        mTodoItem.setName(mItemName.getText().toString());
        mTodoItem.setNotes(mItemNotes.getText().toString());
        final Calendar calendar = Calendar.getInstance();
        calendar.set(mDueDatePicker.getYear(),
                mDueDatePicker.getMonth(),
                mDueDatePicker.getDayOfMonth());
        mTodoItem.setDueDate(calendar.getTime());
        mTodoItem.setPriority(getItemPriority());
        mTodoItem.setStatus(getItemStatus());
    }

    private void populateFieldsForEdit(){
        mItemName.setText(mTodoItem.getName());
        mItemNotes.setText(mTodoItem.getNotes());
        updateDateInDatePicker();
        mSpinnerStatus.setSelection(mStatusAdapter.getPosition(mTodoItem.getStatus().toString()));
        mSpinnerPriority.setSelection(mPriorityAdapter.getPosition(mTodoItem.getPriority().toString()));
    }

    private void updateDateInDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(mTodoItem.getDueDate());
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        mDueDatePicker.updateDate(year,month,dayOfMonth);
    }

    private Status getItemStatus() {
        final String statusStr = mStatusAdapter.getItem(
                mSpinnerStatus.getSelectedItemPosition()).toString();
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
        return status;
    }

    private Priority getItemPriority() {
        final String priorityStr = mPriorityAdapter.getItem(
                mSpinnerPriority.getSelectedItemPosition()).toString();
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
        return priority;
    }

}
