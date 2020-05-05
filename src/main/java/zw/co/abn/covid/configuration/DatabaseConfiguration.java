package zw.co.abn.covid.configuration;

import com.couchbase.lite.*;
import com.couchbase.lite.replicator.Replication;
import org.springframework.stereotype.Component;



@Component
public class DatabaseConfiguration {

    private Manager manager;
    private Database database;


    private static DatabaseConfiguration instance = null;

    private DatabaseConfiguration() {
        try {
            this.manager = new Manager(new JavaContext("data"), Manager.DEFAULT_OPTIONS);
            this.database = this.manager.getDatabase("covidfx");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConfiguration getInstance() {
        System.out.println("GETTING INSTANCE");
        if(instance == null) {
            System.out.println("CREATING NEW COUCHBASE INSTANCE");
            instance = new DatabaseConfiguration();
        }
        return instance;
    }

    public Database getDatabase() {
        return this.database;
    }






}
