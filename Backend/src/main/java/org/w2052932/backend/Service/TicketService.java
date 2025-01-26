package org.w2052932.backend.Service;
import org.springframework.stereotype.Service;
import org.w2052932.backend.Configuration.Configuration;
import org.w2052932.backend.TicketPool.*;
import org.w2052932.backend.Customer.*;
import org.w2052932.backend.vendor.*;


@Service
public class TicketService {
    private TicketPool ticketPool;
    private Configuration config;
    protected Vendor vendor;
    private Customer customer;


    public void startThreads(Configuration config) {
         ticketPool = new TicketPool(config);

         for (int i = 1;i <= 2; i++){
          Thread vendor  = new Thread(new Vendor(config, ticketPool), "Vendor" + i);
          vendor.start();

         }

         for (int i = 1;i <= 3; i++){
            Thread customer =  new Thread(new Customer(config, ticketPool), "Customer" + i);
            customer.start();
         }


    }

    public void stopThreads(Configuration config) {

        Thread vendor  = new Thread(new Vendor(this.config, ticketPool));
        vendor.interrupt();

        Thread customer  = new Thread(new Customer(this.config, ticketPool));
        customer.interrupt();

        }
    }









