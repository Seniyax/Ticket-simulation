package org.w2052932.backend.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w2052932.backend.Customer.*;
import org.w2052932.backend.vendor.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class LogService {
    private final Vendor vendor;
    private final Customer customer;

    public LogService(Vendor vendor , Customer customer) {
        this.vendor = vendor;
        this.customer = customer;
    }


    public List<String>getLog() {
        List<String> alllog = new ArrayList<String>();
        alllog.addAll(vendor.getVendorLogs());
        alllog.addAll(customer.getCustomersLogs());


        return alllog;
    }
}
