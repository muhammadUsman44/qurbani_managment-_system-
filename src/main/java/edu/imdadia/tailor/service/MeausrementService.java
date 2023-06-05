package edu.imdadia.tailor.service;

import edu.imdadia.tailor.Entity.MeausrementEntity;

import java.util.List;

public interface MeausrementService {

    void deleteAll();


    void deleteById(int id);

    void deleteByFirstName(String name);

    void deleteByLastName(String name);

    void deleteByMobNo(String MobNo);



    List<MeausrementEntity> findAllByOrderByMeausrementIdAsc();

    void save(MeausrementEntity meausrement);

    MeausrementEntity findById(Integer integer);


    MeausrementEntity findByFirstName(String name);

    MeausrementEntity findByLast(String name);

    MeausrementEntity findByMobileNo(String mobNo);


}
