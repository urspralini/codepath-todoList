package com.codepath.simpletodo.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by pbabu on 3/28/16.
 */
@Table(name="TodoItems")
public class TodoItem extends Model {

    @Column(name="Name")
    public String name;

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
}
