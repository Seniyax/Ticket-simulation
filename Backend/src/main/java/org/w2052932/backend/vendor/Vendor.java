package org.w2052932.backend.vendor;
import org.springframework.stereotype.Component;
import org.w2052932.backend.Configuration.*;
import org.w2052932.backend.TicketPool.*;


import java.util.ArrayList;
import java.util.List;

@Component
public class Vendor implements Runnable {
    private final List<String> vendorLogs = new ArrayList<>();
    protected Configuration config;
    protected final TicketPool ticketPool;




    public Vendor(Configuration config, TicketPool ticketPool) {
        this.config = config;
        this.ticketPool = ticketPool;
    }

    public List<String> getVendorLogs() {
         return new ArrayList<>(vendorLogs);
    }

    @Override
    public void run() {
        while (true) {

            if (ticketPool.getTotalTicketsAdded() >= config.getTotalTickets()){
                    break;}

            String  ticket = "Ticket" ;


            ticketPool.addTicket(ticket);
            String message = (Thread.currentThread().getName() + " added " + ticket);
            System.out.println(message);
            vendorLogs.add(message);
//            notificationService.sendNotifications("/topic/notification", message);
                try{
                    Thread.sleep(config.getTicketReleaseRate());

        }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                    break;

                }



//


            }
        }


    }

