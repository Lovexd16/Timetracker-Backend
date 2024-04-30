package com.timetracker.timetracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping
    public String getIndex() {
        return "{'message': 'Hello, World!'}";
    }
}
