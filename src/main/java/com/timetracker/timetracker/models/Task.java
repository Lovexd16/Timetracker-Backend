package com.timetracker.timetracker.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Tasks")
public class Task {
    @Id
    private String id;
    private String taskName;
    private long time;
    private LocalDate taskDate; // För att kunna visa skapelse-datum för tasks.
    private boolean deleted; // För att kunna soft-deleta.

    public Task(String id, String taskName, long time, LocalDate taskDate, boolean deleted) {
        this.id = id;
        this.taskName = taskName;
        this.time = time;
        this.taskDate = taskDate;
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public LocalDate getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(LocalDate taskDate) {
        this.taskDate = taskDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}