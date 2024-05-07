package com.timetracker.timetracker.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.timetracker.timetracker.models.Task;

@Service
public class TaskService {
    private final MongoOperations mongoOperations;

    public TaskService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Task addTask(Task task) {
        return mongoOperations.insert(task);
    }

    public List<Task> getTasks() {
        return mongoOperations.findAll(Task.class);
    }

    public Task getTaskById(String id) {
        return mongoOperations.findById(id, Task.class);
    }

    public List<LocalDate> getTaskDates() {
        List<Task> tasks = mongoOperations.findAll(Task.class);
        List<LocalDate> taskDates = tasks.stream()
                .map(Task::getTaskDate)
                .collect(Collectors.toList());
        return taskDates;
    }

    public Task editTask(String id, Task task) {
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = Update.update("taskName", task.getTaskName());

        mongoOperations.updateFirst(query, update, Task.class);
        return mongoOperations.findById(id, Task.class);
    }

    public void deleteTask(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        mongoOperations.remove(query, Task.class);
    }

    public Task totalTimeForTask(String id, long time) {
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = Update.update("time", time);

        mongoOperations.updateFirst(query, update, Task.class);
        return mongoOperations.findById(id, Task.class);
    }

    public void softDeleteTask(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = Update.update("deleted", true);
        mongoOperations.updateFirst(query, update, Task.class);
    }

}
