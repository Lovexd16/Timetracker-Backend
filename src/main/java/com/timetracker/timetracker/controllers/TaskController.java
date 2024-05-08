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

    // Tar in servicen
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Get endpoint för att hämta alla tasks som inte är soft deletade.
    @GetMapping("/tasks/active")
    public List<Task> getActiveTasks() {
        return taskService.getActiveTasks();
    }

    // Get endpoint för att hämta alla tasks som är soft deletade.
    @GetMapping("/tasks/deleted")
    public List<Task> getDeletedTasks() {
        return taskService.getDeletedTasks();
    }

    // Get endpoint för att hämta tiden för en task med hjälp av ID.
    @GetMapping("/task/{id}/time")
    public long getTimeForTask(@PathVariable String id) {
        Task task = taskService.getTaskById(id);
        return task.getTime();
    }

    // Post endpoint för att lägga till en uppgift. Tiden sätts till 0, och datumet
    // sätts till det nuvarande datumet.
    @PostMapping("/task")
    public Task addTask(@RequestBody Task task) {
        task.setTime(0);
        task.setTaskDate(LocalDate.now());
        return taskService.addTask(task);
    }

    // Patch endpoint för att ändra namnet på en task med hjälp av ID.
    @PatchMapping("/task/{id}")
    public Task editTask(@PathVariable String id, @RequestBody Task task) {
        return taskService.editTask(id, task);
    }

    // Patch endpoint för att ändra tiden på en task med hjälp av ID. Används när en
    // stopptimer-knapp trycks och den nya tiden ska sparas.
    @PatchMapping("/task/{id}/time")
    public Task totalTimeForTask(@PathVariable String id, @RequestBody Map<String, Long> request) {
        long time = request.get("time");
        return taskService.totalTimeForTask(id, time);
    }

    // DeleteMapping för att helt ta bort en task.
    @DeleteMapping("/task/{id}")
    public String deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return "{'message': 'Task with id " + id + "has been deleted.'}";
    }

    // DeleteMapping för att soft deleta en task, sätter "deleted" till true.
    @DeleteMapping("/task/{id}/soft")
    public String softDeleteTask(@PathVariable String id) {
        taskService.softDeleteTask(id);
        return "{'message': 'Task with id " + id + " has been soft deleted.'}";
    }
}
