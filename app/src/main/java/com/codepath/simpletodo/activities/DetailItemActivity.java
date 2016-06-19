package com.codepath.simpletodo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.models.TodoItem;

import java.text.SimpleDateFormat;

public class DetailItemActivity extends AppCompatActivity {

    private TodoItem mTodoItem;
    private TextView mItemStatus;
    private TextView mItemPriority;
    private TextView mItemName;
    private TextView mItemNotes;
    private TextView mDueDatePicker;
    private int mItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeUIElements();
        mTodoItem = getIntent().getParcelableExtra(MainActivity.TODO_ITEM_KEY);
        mItemPosition = getIntent().getIntExtra(MainActivity.POSITION, -1);
        populateUIWithValues();
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
            case R.id.action_edit:
                //explicit intent
                Intent editItemIntent = new Intent(this, EditItemActivity.class);
                Bundle todoItemBundle = new Bundle();
                todoItemBundle.putParcelable(MainActivity.TODO_ITEM_KEY, mTodoItem);
                editItemIntent.putExtra(MainActivity.POSITION, mItemPosition);
                editItemIntent.putExtras(todoItemBundle);
                startActivityForResult(editItemIntent, MainActivity.REQUEST_CODE_EDIT);
                return true;
            case R.id.action_delete:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == MainActivity.REQUEST_CODE_EDIT) {
                final TodoItem updatedTodoItem = data.getParcelableExtra(MainActivity.TODO_ITEM_KEY);
                copyFrom(updatedTodoItem, mTodoItem);
                populateUIWithValues();
            }
        }
    }



    private void initializeUIElements() {
        mItemName = (TextView) findViewById(R.id.tvNameVal);
        mItemNotes = (TextView) findViewById(R.id.tvNotesVal);
        mDueDatePicker = (TextView)findViewById(R.id.tvDueDateVal);
        mItemStatus = (TextView)findViewById(R.id.tvStatusVal);
        mItemPriority = (TextView)findViewById(R.id.tvPriorityVal);
    }

    private void populateUIWithValues() {
        mItemName.setText(mTodoItem.getName());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        mDueDatePicker.setText(sdf.format(mTodoItem.getDueDate()));
        mItemNotes.setText(mTodoItem.getNotes());
        mItemStatus.setText(mTodoItem.getStatus().toString());
        mItemPriority.setText(mTodoItem.getPriority().toString());
    }

    private void copyFrom(TodoItem updated, TodoItem existing) {
        existing.setName(updated.getName());
        existing.setDueDate(updated.getDueDate());
        existing.setNotes(updated.getNotes());
        existing.setPriority(updated.getPriority());
        existing.setStatus(updated.getStatus());
    }
}
