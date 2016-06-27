package com.codepath.simpletodo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by pbabu on 3/28/16.
 */
@Table(name="TodoItems")
public class TodoItem extends Model implements Parcelable {

    @Column(name="Name")
    public String name;

    @Column(name = "DueDate")
    public Date dueDate;

    @Column(name = "Notes")
    public String notes;

    @Column(name = "Status")
    public Status status;

    @Column(name = "Priority")
    public Priority priority;

    public static final Parcelable.Creator<TodoItem> CREATOR = new Parcelable.Creator<TodoItem>() {
        @Override
        public TodoItem createFromParcel(Parcel in) {
            return new TodoItem(in);
        }

        @Override
        public TodoItem[] newArray(int size) {
            return new TodoItem[size];
        }
    };

    private TodoItem(Parcel in){
        this.name = in.readString();
        this.dueDate = new Date(in.readLong());
        this.notes = in.readString();
        this.priority = Priority.valueOf(in.readString());
        this.status = Status.valueOf(in.readString());
    }

    public TodoItem(){}

    public static List<TodoItem> getAll() {
        return getAll("Name");
    }

    public static List<TodoItem> getAll(String sortField) {
        return new Select()
                .from(TodoItem.class)
                .orderBy(sortField + " ASC")
                .limit(100)
                .execute();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(getName());
        out.writeLong(dueDate.getTime());
        out.writeString(getNotes());
        out.writeString(getPriority().name());
        out.writeString(getStatus().name());
    }



}
