package org.w2052932.backend.TicketPool;

import org.springframework.stereotype.Component;
import org.w2052932.backend.Configuration.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TicketPool {


    protected Configuration config;
    private final List<String> tickets = Collections.synchronizedList(new ArrayList<>());
    private int totalTicketsAdded;


//    public TicketPool(List<String> tickets){
//        this.tickets = tickets;
//    }


    public TicketPool() {

    }

    public TicketPool(Configuration config) {
        this.config = config;
    }

//    public TicketPool() {
//
//        this.tickets = Collections.synchronizedList(new ArrayList<>());
//
//    }


    public List<String> getTickets() {
        return tickets;
    }

    public int getTotalTicketsAdded() {
        return totalTicketsAdded;
    }


    public synchronized void addTicket(String ticket) {

        while (tickets.size() >= config.getMaxTicketCapacity() || totalTicketsAdded >= config.getMaxTicketCapacity()) {
            try{
                System.out.println(Thread.currentThread().getName() + " Ticket pool is almost full");
                wait();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }
        }
        tickets.add(ticket);
        totalTicketsAdded++;
        notifyAll();




    }

    public synchronized String removeTicket() {
        while (tickets.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " Waiting for tickets to buy...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }

            String ticket = tickets.remove(0);
            notifyAll();
            return ticket;







    }
}



