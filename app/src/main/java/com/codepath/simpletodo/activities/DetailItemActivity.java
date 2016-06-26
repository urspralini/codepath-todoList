package com.codepath.simpletodo.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.activeandroid.Model;
import com.codepath.simpletodo.R;
import com.codepath.simpletodo.fragments.DeleteConfirmationFragment;
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
    private long mTodoItemId;
    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/M/yyyy");
    public static final String TODO_ITEM_TITLE_KEY = "todoItemTitleKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        initializeUIElements();
        mItemPosition = getIntent().getIntExtra(MainActivity.POSITION, -1);
        mTodoItemId = getIntent().getLongExtra(MainActivity.TODO_ITEM_ID_KEY, -1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTodoItem = Model.load(TodoItem.class, mTodoItemId);
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
                editItemIntent.putExtra(MainActivity.POSITION, mItemPosition);
                editItemIntent.putExtra(MainActivity.TODO_ITEM_ID_KEY, mTodoItemId);
                startActivity(editItemIntent);
                return true;
            case R.id.action_delete:
                showDeleteConfirmDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
        mDueDatePicker.setText(SDF.format(mTodoItem.getDueDate()));
        mItemNotes.setText(mTodoItem.getNotes());
        mItemStatus.setText(mTodoItem.getStatus().toString());
        mItemPriority.setText(mTodoItem.getPriority().toString());
        int priorityColor;
        switch (mTodoItem.getPriority()) {
            case HIGH:
                priorityColor = Color.parseColor(getString(R.string.priority_high_color));
                break;
            case MEDIUM:
                priorityColor = Color.parseColor(getString(R.string.priority_medium_color));
                break;
            default:
                priorityColor = Color.parseColor(getString(R.string.priority_low_color));
        }
        mItemPriority.setTextColor(priorityColor);
        int statusColor;
        switch (mTodoItem.getStatus()) {
            case NEW:
                statusColor = Color.parseColor(getString(R.string.status_new_color));
                break;
            case IN_PROGRESS:
                statusColor = Color.parseColor(getString(R.string.status_in_progress_color));
                break;
            default:
                statusColor = Color.parseColor(getString(R.string.status_done_color));
        }
        mItemStatus.setTextColor(statusColor);
    }

    public void showDeleteConfirmDialog() {
        DialogFragment delConfirmFragment = new DeleteConfirmationFragment();
        Bundle args = new Bundle();
        args.putString(TODO_ITEM_TITLE_KEY, mTodoItem.getName());
        delConfirmFragment.setArguments(args);
        delConfirmFragment.show(getFragmentManager(), "dialog");
    }

    public void confirmDelete() {
        mTodoItem.delete();
        this.finish();
    }
}
