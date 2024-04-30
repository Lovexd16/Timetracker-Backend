package com.timetracker.timetracker.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker.timetracker.models.Task;
import com.timetracker.timetracker.services.TaskService;

@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getRoot() {
        return "{'Hello': 'World!'}";
    }

    @PostMapping("/task")
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }
}
