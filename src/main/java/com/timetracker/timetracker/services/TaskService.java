package com.timetracker.timetracker.services;

import java.util.List;

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
        task.setTime(0);
        task.setTotalTimeSpent(0);
        return mongoOperations.insert(task);
    }

    public List<Task> getTasks() {
        return mongoOperations.findAll(Task.class);
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

    public Task addTime(String id, long addTime) {
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = new Update();
        update.inc("totalTimeSpent", addTime);

        mongoOperations.updateFirst(query, update, Task.class);
        return mongoOperations.findById(id, Task.class);
    }
}
