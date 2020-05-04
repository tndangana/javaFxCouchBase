package zw.co.abn.covid.service.impl;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.abn.covid.configuration.DatabaseConfiguration;
import zw.co.abn.covid.model.Patient;

import zw.co.abn.covid.service.PatientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final DatabaseConfiguration databaseConfiguration;
    private final Database database;


    public PatientServiceImpl() {
        this.databaseConfiguration = new DatabaseConfiguration();
        this.database = this.databaseConfiguration.getDatabase();
    }

    @Override
    public Optional<Patient> findbyId(String id) {
//        return Optional.of(databaseConfiguration.getDatabase());
        return Optional.ofNullable(null);
    }

    @Override
    public Optional<List<Patient>> findAll() {
        return Optional.empty();
    }

    @Override
    public Patient save(Patient patient) {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("type","patient");
//        properties.putAll(patient);
        Document document = database.createDocument();
        try {
            //save patient
            patient.setId(document.putProperties(properties).getDocument().getId());

        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
        return patient;

    }

    @Override
    public void deleteById(String id) {

    }
}
