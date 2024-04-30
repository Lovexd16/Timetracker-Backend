package com.timetracker.timetracker.services;

import org.springframework.data.mongodb.core.MongoOperations;
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
}
