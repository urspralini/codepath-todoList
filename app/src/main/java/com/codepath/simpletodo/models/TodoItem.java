package com.codepath.simpletodo.models;

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
public class TodoItem extends Model {

    @Column(name="Name")
    public String name;

    @Column(name = "dueDate")
    public Date dueDate;

    @Column(name = "notes")
    public String notes;

    @Column(name = "status")
    public Status status;

    @Column(name = "priority")
    public Priority priority;

    public static List<TodoItem> getAll() {
        return new Select()
                .from(TodoItem.class)
                .orderBy("Name ASC")
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
}
