package com.codepath.simpletodo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.activities.DetailItemActivity;
import com.codepath.simpletodo.models.TodoItem;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by pbabu on 3/28/16.
 */
public class TodoItemArrayAdapter extends ArrayAdapter<TodoItem> {

    public static class ViewHolder {
        TextView tvName;
        TextView tvPriority;
        TextView tvStatus;
        TextView tvDueDate;
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView)convertView.findViewById(R.id.tv_li_name);
            viewHolder.tvPriority = (TextView)convertView.findViewById(R.id.tv_li_priority);
            viewHolder.tvStatus = (TextView)convertView.findViewById(R.id.tv_li_status);
            viewHolder.tvDueDate = (TextView)convertView.findViewById(R.id.tv_li_duedate);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.tvName.setText(todoItem.getName());
        viewHolder.tvPriority.setText(todoItem.getPriority().toString());
        int priorityColor;
        switch (todoItem.getPriority()) {
            case HIGH:
                priorityColor = Color.RED;
                break;
            case MEDIUM:
                priorityColor = Color.YELLOW;
                break;
            default:
                priorityColor = Color.BLUE;
                break;
        }
        viewHolder.tvPriority.setTextColor(priorityColor);
        viewHolder.tvStatus.setText(todoItem.getStatus().toString());
        viewHolder.tvDueDate.setText(DetailItemActivity.SDF.format(todoItem.getDueDate()));
        return  convertView;
    }
    
    
}
