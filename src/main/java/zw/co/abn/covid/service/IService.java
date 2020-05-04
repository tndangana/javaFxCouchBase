package zw.co.abn.covid.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

    Optional<T> findbyId(String id);
    Optional<List<T>> findAll();
    T save(T t);
    void deleteById(String id);

}
