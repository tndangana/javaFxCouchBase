package zw.co.abn.covid.configuration;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.JavaContext;
import com.couchbase.lite.Manager;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DatabaseConfiguration {

    private Manager manager = null;
    private Database database = null;

    public DatabaseConfiguration() {
        init();
    }


    public void init(){
        try {
            //create Manager
            //create sub directory were database will be kept
            JavaContext context = new JavaContext("data");
            manager = new Manager(context, Manager.DEFAULT_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //create a database
            database = manager.getDatabase("covid");
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
    }


    public Database getDatabase() {
        return database;
    }
}
