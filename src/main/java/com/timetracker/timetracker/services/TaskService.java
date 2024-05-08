package com.timetracker.timetracker.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.timetracker.timetracker.models.Task;

@Service
public class TaskService {
    // Tar in mongoOperations i Service.
    private final MongoOperations mongoOperations;

    public TaskService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    // Metod för att hämta alla aktiva tasks via kriteriet att deleted ska vara
    // false.
    public List<Task> getActiveTasks() {
        Query query = Query.query(Criteria.where("deleted").is(false));
        return mongoOperations.find(query, Task.class);
    }

    // Metod för att hämta alla inaktiva tasks via kriteriet att deleted ska vara
    // true.
    public List<Task> getDeletedTasks() {
        Query query = Query.query(Criteria.where("deleted").is(true));
        return mongoOperations.find(query, Task.class);
    }

    // Metod för att hämta en task med ID. Metoden används i controller för att
    // hämta tiden av en task.
    public Task getTaskById(String id) {
        return mongoOperations.findById(id, Task.class);
    }

    // Metod för att lägga till en task.
    public Task addTask(Task task) {
        return mongoOperations.insert(task);
    }

    // Metod för att ändra namn på en task.
    public Task editTask(String id, Task task) {
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = Update.update("taskName", task.getTaskName());

        mongoOperations.updateFirst(query, update, Task.class);
        return mongoOperations.findById(id, Task.class);
    }

    // Metod för att uppdatera en tasks tid.
    public Task totalTimeForTask(String id, long time) {
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = Update.update("time", time);

        mongoOperations.updateFirst(query, update, Task.class);
        return mongoOperations.findById(id, Task.class);
    }

    // Metod för att ta bort en task.
    public void deleteTask(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        mongoOperations.remove(query, Task.class);
    }

    // Metod för att soft-deleta en task.
    public void softDeleteTask(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = Update.update("deleted", true);
        mongoOperations.updateFirst(query, update, Task.class);
    }

    // Metod för att soft deleta alla befintliga tasks på måndagar.
    public void softDeleteMonday() {
        LocalDate checkDay = LocalDate.now();
        if (checkDay.getDayOfWeek() == java.time.DayOfWeek.MONDAY) {
            Query query = Query.query(Criteria.where("deleted").is(false));
            Update update = Update.update("deleted", true);
            mongoOperations.updateMulti(query, update, Task.class);
        }
    }

}
