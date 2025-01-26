//package org.w2052932.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
//import org.w2052932.backend.Service.NotificationService;


//@Configuration
//public class WebsocketController {
//    @Autowired
//    private NotificationService notificationService;
//
//    @MessageMapping("/message")
//    @SendTo("/topic/notification")
//    public String handleMessage(String message) {
//        System.out.println(message);
//        notificationService.sendNotifications("/topic/notification", message);
//
//        return message;
//    }
//}
