package org.w2052932.backend.Configuration;



import org.w2052932.backend.TicketPool.TicketPool;
import org.w2052932.backend.Customer.*;
import org.w2052932.backend.vendor.*;

import java.lang.Thread;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();
        TicketPool ticketpool = new TicketPool(config);

        config.setTotalTickets(getinput(scanner, "Enter the Total Ticket Count: "));
        config.setTicketReleaseRate(getinput(scanner, "Enter the Ticket Release Rate(ms): "));
        config.setCustomerRetrievalRate(getinput(scanner, "Enter the Customer Retrieval Rate(ms): "));
        config.setMaxTicketCapacity(getinput(scanner, "Enter the Max Ticket Capacity: "));

        System.out.println("JSON File is Created");
        ConfigurationService service = new ConfigurationService();
        service.ConvertToJson(config);

//        System.out.println(config.getTotalTickets());
//        System.out.println(config.getTicketReleaseRate());
//        System.out.println(config.getCustomerRetrievalRate());
//        System.out.println(config.getMaxTicketCapacity());


//

        for (int i = 1; i <= 2; i++){
            new Thread(new Vendor(config,ticketpool),"Vendor" +i).start();
        }

        for (int i = 1; i <= 3; i++){
            new Thread(new Customer(config,ticketpool),"Customer " +i).start();
        }


    }


    private static int getinput(Scanner scanner, String prompt) {

        int value;
        while (true){
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value>0){
                    return value;

                }else {
                    System.out.println("Enter a positive integer");
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid Input,Please Enter again");
            }

        }

        }












    }



