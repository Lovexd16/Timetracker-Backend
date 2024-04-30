package com.timetracker.timetracker.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker.timetracker.models.Task;

@RestController
public class TaskController {

    @GetMapping
    public String getRoot() {
        return "{'Hello': 'World!'}";
    }

    @PostMapping("/task")
    public Task addTask() {
        return null;
    }
}
