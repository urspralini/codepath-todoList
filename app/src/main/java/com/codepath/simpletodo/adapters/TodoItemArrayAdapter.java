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
                priorityColor = Color.parseColor(getContext()
                        .getString(R.string.priority_high_color));
                break;
            case MEDIUM:
                priorityColor = Color.parseColor(getContext()
                        .getString(R.string.priority_medium_color));
                break;
            default:
                priorityColor = Color.parseColor(getContext()
                        .getString(R.string.priority_low_color));
        }
        viewHolder.tvPriority.setTextColor(priorityColor);
        viewHolder.tvStatus.setText(todoItem.getStatus().toString());
        int statusColor;
        switch (todoItem.getStatus()) {
            case NEW:
                statusColor = Color.parseColor(getContext()
                        .getString(R.string.status_new_color));
                break;
            case IN_PROGRESS:
                statusColor = Color.parseColor(getContext()
                        .getString(R.string.status_in_progress_color));
                break;
            default:
                statusColor = Color.parseColor(getContext()
                        .getString(R.string.status_done_color));
        }
        viewHolder.tvStatus.setTextColor(statusColor);
        viewHolder.tvDueDate.setText(DetailItemActivity.SDF.format(todoItem.getDueDate()));
        return  convertView;
    }
    
    
}
