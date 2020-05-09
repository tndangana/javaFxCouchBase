package zw.co.abn.covid.service.impl;

import com.couchbase.lite.*;
import org.springframework.stereotype.Service;
import zw.co.abn.covid.configuration.DatabaseConfiguration;
import zw.co.abn.covid.service.IGenericService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//Service class which implements and brings life to the generic service interface
public class GenericService implements IGenericService<String, Object> {

     //Couch base configurations are setup in here
    private final DatabaseConfiguration databaseConfiguration;
    private final Database database;
    private Document document = null;

    //instantiate configuration and database
    public GenericService() {
        this.databaseConfiguration = DatabaseConfiguration.getInstance();
        this.database = this.databaseConfiguration.getDatabase();
    }


    @Override
    public List<Object> findAll(String viewName) {
        List<Object> list = new ArrayList<>();
        try {
            View view = database.getView(viewName);
            view.setMap((document, emitter) -> emitter.emit(document.get("_id"), document), "1");
            Query query = view.createQuery();
            QueryEnumerator result = query.run();
            result.forEach(queryRow -> {
                document = queryRow.getDocument();
                list.add(document.getProperties());
            });
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        } finally {
            document = null;
        }
        return list;
    }

    @Override
    public boolean save(Map<String, Object> properties, String viewName) {
        properties.put("type", viewName);
        properties.putAll(properties);
        Document document = database.createDocument();
        try {
            document.putProperties(properties);
            return true;
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Map<String, Object> properties, String id) {
        Document doc = database.getDocument(id);
        Map<String, Object> modelProperties = new HashMap<String, Object>();
        modelProperties.putAll(doc.getProperties());
        modelProperties.putAll(properties);
        try {
            doc.putProperties(modelProperties);
            return true;
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(String viewName, String id) {
        Document task = database.getDocument(id);

        try {
            if (!task.getProperties().isEmpty()) {
                task.delete();
                return true;
            }
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean recordExist(String viewName, String id) {
        Document document = database.getDocument(id);
        if (!document.getProperties().isEmpty())
            return true;
        return false;
    }

    @Override
    public Map<String,Object> findById(String viewName, String id) {
         Document document = database.getDocument(id);
        return document.getProperties();
    }


}
