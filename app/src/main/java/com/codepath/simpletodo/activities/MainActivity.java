package com.codepath.simpletodo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.adapters.TodoItemArrayAdapter;
import com.codepath.simpletodo.models.TodoItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String POSITION = "position";
    private ListView lvItems;
    private List<TodoItem> todoItems = new ArrayList<>();
    private TodoItemArrayAdapter todoAdapter;
    private Spinner mSpinnerSortBy;
    private ArrayAdapter<CharSequence> mSpinnerSortByAdapter;
    public static final String TODO_ITEM_KEY = "todoItemParcelable";
    public static final String TODO_ITEM_ID_KEY = "todoItemId";
    public static final int DEFAULT_SORT_POSTION = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //get references to list view and edit text
        lvItems = (ListView)findViewById(R.id.lvItems);
        mSpinnerSortBy = (Spinner)findViewById(R.id.spinnerSortBy);
        mSpinnerSortByAdapter = ArrayAdapter.createFromResource(this, R.array.sortBy,
                android.R.layout.simple_spinner_dropdown_item);
        mSpinnerSortBy.setAdapter(mSpinnerSortByAdapter);
        mSpinnerSortBy.setSelection(DEFAULT_SORT_POSTION);
        mSpinnerSortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                final String sortFieldText = mSpinnerSortByAdapter.getItem(position).toString();
                if(position > 0) {
                    String sortField;
                    switch (position){
                        case 1:
                            sortField = "DueDate";
                            break;
                        case 2:
                        case 3:
                        case 4:
                            sortField = sortFieldText;
                            break;
                        default:
                            sortField = "Name";
                    }
                    readItems(sortField);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        configureListViewAdapter();
        //on item click, open detail item activity
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //explicit intent
                Intent detailItemIntent = new Intent(MainActivity.this, DetailItemActivity.class);
                final TodoItem todoItem = todoItems.get(position);
                detailItemIntent.putExtra(TODO_ITEM_ID_KEY, todoItem.getId());
                detailItemIntent.putExtra(POSITION, position);
                startActivity(detailItemIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        readItems();
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
                Intent addItemIntent = new Intent(this, EditItemActivity.class);
                startActivity(addItemIntent);
                break;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }

    private void configureListViewAdapter() {
        todoAdapter = new TodoItemArrayAdapter(this, R.layout.list_item, todoItems);
        lvItems.setAdapter(todoAdapter);
    }

    private void readItems(){
       readItems("Name");
    }

    private void readItems(String sortField) {
        todoItems = TodoItem.getAll(sortField);
        todoAdapter.clear();
        todoAdapter.addAll(todoItems);
        todoAdapter.notifyDataSetChanged();
    }
}
