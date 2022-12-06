package runner;

import java.util.concurrent.ConcurrentMap;
import java.io.File;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestEnvironment {

    private String environmentFile;
    public ConcurrentMap<String, String> variables;

    public TestEnvironment(String environmentFile) {
        this.environmentFile = environmentFile;
        variables = loadVariables();
    }

    public String getHost() {
        return variables.get("host");
    }

    public String getVariable(String key) {
        return variables.get(key);
    }

    public ConcurrentMap<String, String> loadVariables() {
        ConcurrentMap<String, String> userData = null;
        ObjectMapper mapper = new ObjectMapper();
        // create instance of the File class
        File fileObj = new File(environmentFile);
        // use try-catch block to convert JSON data into Map
        try {
            // read JSON data from file using fileObj and map it using ObjectMapper and
            // TypeReference classes
            userData = mapper.readValue(
                    fileObj, new TypeReference<ConcurrentMap<String, Object>>() {
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userData;
    }

}