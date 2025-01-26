package org.w2052932.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w2052932.backend.Configuration.Configuration;
import org.w2052932.backend.Service.TicketService;

@RestController
@RequestMapping("/api/sys")
@CrossOrigin(origins =  "http://localhost:5174/")
public class TicketController {
    Configuration config;

    @Autowired
    TicketService ticketService;

    @PostMapping("/start")
    public String Start(Configuration config) {
        ticketService.startThreads(config);
        return "Threads started";
    }

    @PostMapping("/stop")
    public String Stop(Configuration config) {
        ticketService.stopThreads(config);
        return "Threads stopped";
    }

}
