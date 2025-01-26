package org.w2052932.backend.Customer;

import org.springframework.stereotype.Component;
import org.w2052932.backend.TicketPool.*;
import org.w2052932.backend.Configuration.*;


import java.util.ArrayList;
import java.util.List;

@Component
public class Customer implements Runnable{
    private final List<String> customersLogs = new ArrayList<String>();
    private final TicketPool ticketPool;
    private Configuration config;




    public Customer(Configuration config, TicketPool ticketPool) {
        this.config = config;
        this.ticketPool = ticketPool;


    }

    public List<String> getCustomersLogs() {
        return new ArrayList<>(customersLogs);
    }

    @Override
public void run() {
        synchronized (ticketPool) {
    while (true) {
        String ticket = ticketPool.removeTicket();
        if (ticket != null) {
            String message = Thread.currentThread().getName() + " purchased " + ticket;
            System.out.println(message);
            customersLogs.add(message);
//            notificationService.sendNotifications("/topic/notification", message);

        } else {

            System.out.println(Thread.currentThread().getName() + " waiting for tickets...");
        }

        try {

            Thread.sleep(config.getCustomerRetrievalRate());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " stopped.");
            break;
    }
}


}}}
