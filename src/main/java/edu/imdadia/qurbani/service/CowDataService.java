package edu.imdadia.qurbani.service;


import edu.imdadia.qurbani.Entity.CowDataEntity;


import java.util.List;
import java.util.Optional;

public interface CowDataService {


    void save(CowDataEntity cowDataEntity);

    void delete(CowDataEntity cowDataEntity);

    void deleteById(Integer id);


    Optional<CowDataEntity> findById(Integer integer);

    Optional<CowDataEntity> findCowNumber(String str);


    void saveAll(List<CowDataEntity> orderEntities);

    void deleteAll();

    List<CowDataEntity> getAll();

    CowDataEntity searchByRegNo(Integer integer);

    List<CowDataEntity>findByCowNumberField(String str);

}
