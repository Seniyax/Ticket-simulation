package org.w2052932.backend.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class ConfigurationService {

    public void ConvertToJson(Configuration config){
        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(new File("Configuration.json"),config);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
