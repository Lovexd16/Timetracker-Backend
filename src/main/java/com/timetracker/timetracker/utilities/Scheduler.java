package com.timetracker.timetracker.utilities;

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

    // Schemalägger metoden "softDeleteMonday()"" till 00:00 på måndagar, då den ska
    // köras och automatiskt inaktivera alla aktiva tasks.
    @Scheduled(cron = "0 0 0 * * MON")
    public void softDeleteMonday() {
        taskService.softDeleteMonday();
    }

}
