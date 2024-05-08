package com.timetracker.timetracker;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.timetracker.timetracker.services.TaskService;

@Component
@EnableScheduling
public class Scheduler {
    private final TaskService taskService;

    public Scheduler(TaskService taskService) {
        this.taskService = taskService;
    }

    @Scheduled(cron = "0 0 0 * * MON")
    public void softDeleteMonday() {
        taskService.softDeleteMonday();
    }

}
