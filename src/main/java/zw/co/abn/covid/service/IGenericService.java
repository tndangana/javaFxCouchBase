package zw.co.abn.covid.service;

import java.util.List;
import java.util.Map;

//interface with all available methods which will be used to interact with couch base.These methods are implemented in the genericService class
public interface IGenericService<Type,T> {

    List<T> findAll(String viewName);//get all objects
    boolean save(Map<Type,T> properties,String viewName); //save properties of an object through the use of a HashMap
    boolean update(Map<Type,T> properties,String id);  //update similar to save handler.Saves updated object
    boolean deleteById(String viewName,String id); //deletes object through the use of an Id
    boolean recordExist(String viewName,String id); //checks if a record exists
     Map<String ,Object> findById(String viewName,String id);  //finds an object through the use of an id



}
