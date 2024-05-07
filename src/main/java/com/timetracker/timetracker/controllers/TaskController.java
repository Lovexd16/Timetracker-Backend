package com.timetracker.timetracker.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker.timetracker.models.Task;
import com.timetracker.timetracker.services.TaskService;

@CrossOrigin(origins = "*")
@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/task/{id}/time")
    public long getTimeForTask(@PathVariable String id) {
        Task task = taskService.getTaskById(id);
        return task.getTime();
    }

    @GetMapping("/tasks/dates")
    public List<LocalDate> getTaskDates() {
        return taskService.getTaskDates();
    }

    @PostMapping("/task")
    public Task addTask(@RequestBody Task task) {
        task.setTime(0);
        task.setTaskDate(LocalDate.now());
        return taskService.addTask(task);
    }

    @PatchMapping("/task/{id}")
    public Task editTask(@PathVariable String id, @RequestBody Task task) {
        return taskService.editTask(id, task);
    }

    @PatchMapping("/task/{id}/time")
    public Task totalTimeForTask(@PathVariable String id, @RequestBody Map<String, Long> request) {
        long time = request.get("time");
        return taskService.lastTimeForTask(id, time);
    }

    @DeleteMapping("/task/{id}")
    public String deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return "{'message': 'Task with id " + id + "has been deleted.'}";
    }
}
