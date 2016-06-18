package com.codepath.simpletodo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.adapters.TodoItemArrayAdapter;
import com.codepath.simpletodo.models.TodoItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TEXT = "text";
    public static final String POSITION = "position";
    public static final int REQUEST_CODE_EDIT = 200;
    public static final int REQUEST_CODE_NEW = 201;
    private ListView lvItems;
    private List<TodoItem> todoItems;
    private TodoItemArrayAdapter todoAdapter;
    public static final String TODO_ITEM_KEY = "todoItemParcelable";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //get references to list view and edit text
        lvItems = (ListView)findViewById(R.id.lvItems);
        populateListItems();
        //add item long click listener to delete the item from the list
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                final TodoItem todoItem = todoItems.remove(position);
                //delete todo item from the sql lite
                todoItem.delete();
                //notify the adapter about the change
                todoAdapter.notifyDataSetChanged();
                return true;
            }
        });
        //on item click, open editItem activity
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //explicit intent
                Intent editItemIntent = new Intent(MainActivity.this, EditActivity.class);
                Bundle todoItemBundle = new Bundle();
                todoItemBundle.putParcelable(TODO_ITEM_KEY, todoItems.get(position));
                editItemIntent.putExtra(POSITION, position);
                editItemIntent.putExtras(todoItemBundle);
                startActivityForResult(editItemIntent, REQUEST_CODE_EDIT);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_item:
                //open detail item activity
                Intent addItemIntent = new Intent(this, EditActivity.class);
                startActivityForResult(addItemIntent,REQUEST_CODE_NEW);
                break;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == REQUEST_CODE_EDIT) {
                final TodoItem updatedTodoItem = data.getParcelableExtra(TODO_ITEM_KEY);
                final int itemPosition = data.getExtras().getInt(POSITION);
                final TodoItem existingTodoItem = todoItems.remove(itemPosition);
                copyFrom(updatedTodoItem, existingTodoItem);
                writeItem(existingTodoItem);
                todoItems.add(itemPosition, existingTodoItem);
                todoAdapter.notifyDataSetChanged();
            }else if(requestCode == REQUEST_CODE_NEW){
                TodoItem newItem = data.getParcelableExtra(TODO_ITEM_KEY);
                todoItems.add(newItem);
                todoAdapter.notifyDataSetChanged();
                writeItem(newItem);
            }

        }
    }

    private void populateListItems() {
        readItems();
        todoAdapter = new TodoItemArrayAdapter(this, android.R.layout.simple_list_item_1, todoItems);
        lvItems.setAdapter(todoAdapter);
    }

    private void readItems(){
        todoItems = TodoItem.getAll();
    }

    private void writeItem(TodoItem item){
       item.save();
    }

    private void copyFrom(TodoItem updated, TodoItem existing) {
        existing.setName(updated.getName());
        existing.setDueDate(updated.getDueDate());
        existing.setNotes(updated.getNotes());
        existing.setPriority(updated.getPriority());
        existing.setStatus(updated.getStatus());
    }
}
