package edu.imdadia.qurbani.service;

import edu.imdadia.qurbani.Entity.DataManagementEntity;

import java.util.List;

public interface DataManagementService {

    void deleteByFirstName(String item);

    void deleteByLastName(String item);

    void deleteByMob(String item);

    void deleteAll();
//    List<DataManagementEntity> findAllByOrderByDataManagementIdAsc();

    List<DataManagementEntity> getAll();

    DataManagementEntity save(DataManagementEntity dataManagement);


    DataManagementEntity findByFirstName(String item);

    DataManagementEntity findByLastName(String item);

    DataManagementEntity findByMob(String item);

}
