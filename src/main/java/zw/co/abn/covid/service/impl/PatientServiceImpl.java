package zw.co.abn.covid.service.impl;

import com.couchbase.lite.*;
import org.springframework.stereotype.Service;
import zw.co.abn.covid.configuration.DatabaseConfiguration;
import zw.co.abn.covid.model.Patient;

import zw.co.abn.covid.service.PatientService;

import java.util.*;

@Service
public class PatientServiceImpl implements PatientService {


    private final DatabaseConfiguration databaseConfiguration;
    private final Database database;


    public PatientServiceImpl() {
        this.databaseConfiguration = DatabaseConfiguration.getInstance();
        this.database = this.databaseConfiguration.getDatabase();
    }

    @Override
    public Optional<Patient> findbyId(String id) {
        Document doc = database.getDocument(id);
        Patient patient=(Patient) doc.getProperties();
        return Optional.ofNullable(patient);
    }

    @Override
    public Optional<List<Patient>> findAll() {
//        List<Map<String, Object>> patientList = new ArrayList<>();
          List<Patient> patientList = new ArrayList<>();
        try {
            Query query = database.getView("patients").createQuery();

            QueryEnumerator result = query.run();
            System.out.println("~~~~~~~~~~~~~"+result);
            for (Iterator<QueryRow> it = result; it.hasNext(); ) {
                QueryRow row = it.next();

                patientList.add((Patient) row.getDocument().getProperties());
            }
            patientList.stream().forEach(patient -> {
                System.out.println("********************"+patient.getFirstName());
            });
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(patientList);
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
    public boolean deleteById(String id) {
        Boolean isDeleted = Boolean.FALSE;
        try {
            Document task = database.getDocument(id);
           isDeleted=  task.delete();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
}
