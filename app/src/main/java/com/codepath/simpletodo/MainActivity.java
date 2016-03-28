package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.codepath.simpletodo.adapters.TodoItemArrayAdapter;
import com.codepath.simpletodo.models.TodoItem;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TEXT = "text";
    public static final String POSITION = "position";
    public static final int REQUEST_CODE = 200;
    private ListView lvItems;
    private List<TodoItem> todoItems;
    private TodoItemArrayAdapter todoAdapter;
    private EditText etEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //get references to list view and edit text
        lvItems = (ListView)findViewById(R.id.lvItems);
        etEditText = (EditText)findViewById(R.id.etEditText);
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
                Intent editItemIntent = new Intent(MainActivity.this, EditItemActivity.class);
                editItemIntent.putExtra(TEXT, todoItems.get(position).getName());
                editItemIntent.putExtra(POSITION, position);
                startActivityForResult(editItemIntent, REQUEST_CODE);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            final String itemText = data.getExtras().getString(TEXT);
            final int itemPosition = data.getExtras().getInt(POSITION);
            final TodoItem todoItem = todoItems.remove(itemPosition);
            todoItem.setName(itemText);
            todoItems.add(itemPosition, todoItem);
            todoAdapter.notifyDataSetChanged();
            writeItem(todoItem);
        }
    }

    private void populateListItems() {
        readItems();
        todoAdapter = new TodoItemArrayAdapter(this, android.R.layout.simple_list_item_1, todoItems);
        lvItems.setAdapter(todoAdapter);
    }

    public void addItem(View view) {
        final TodoItem newTodoItem = new TodoItem();
        newTodoItem.setName(etEditText.getText().toString());
        todoItems.add(newTodoItem);
        etEditText.setText("");
        writeItem(newTodoItem);
    }

    private void readItems(){
        todoItems = TodoItem.getAll();
    }

    private void writeItem(TodoItem item){
       item.save();
    }
}
