package org.w2052932.backend.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w2052932.backend.Configuration.Configuration;

@RestController
@RequestMapping("/api/config")
@CrossOrigin(origins = "http://localhost:5174/")
public class ConfigurationController {


     private final Configuration config;

    public ConfigurationController(Configuration config) {
        this.config = config;
    }


//    @GetMapping
//    public Configuration getConfig() {
//        return config;
//    }

    @PostMapping
    public ResponseEntity<String> updateConfig( @RequestBody Configuration newConfig ) {

        System.out.println("Received Payload:" + newConfig);
         config.setMaxTicketCapacity(newConfig.getMaxTicketCapacity());
         config.setTicketReleaseRate(newConfig.getTicketReleaseRate());
         config.setCustomerRetrievalRate(newConfig.getCustomerRetrievalRate());
         config.setTotalTickets(newConfig.getTotalTickets());
         config.setName(newConfig.getName());
         return ResponseEntity.ok(config.toString());

    }
}
