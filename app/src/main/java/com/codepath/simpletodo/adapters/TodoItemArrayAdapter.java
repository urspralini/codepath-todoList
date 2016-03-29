package com.codepath.simpletodo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codepath.simpletodo.models.TodoItem;

import java.util.List;

/**
 * Created by pbabu on 3/28/16.
 */
public class TodoItemArrayAdapter extends ArrayAdapter<TodoItem> {

    public static class ViewHolder {
        TextView tvTodoItem;
    }

    public TodoItemArrayAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final TodoItem todoItem = getItem(position);
        ViewHolder viewHolder;
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            TextView tvTodoItem = (TextView)convertView.findViewById(android.R.id.text1);
            viewHolder = new ViewHolder();
            viewHolder.tvTodoItem = tvTodoItem;
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.tvTodoItem.setText(todoItem.getName());
        return  convertView;
    }
    
    
}
