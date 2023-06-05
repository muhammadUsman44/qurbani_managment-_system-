package edu.imdadia.tailor.repo;

import edu.imdadia.tailor.Entity.MeausrementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeausrementRepo extends JpaRepository<MeausrementEntity, Integer> {


    Optional<MeausrementEntity> deleteByMobileNo(String str);

    MeausrementEntity deleteByLastName(String str);

    MeausrementEntity deleteByFirstName(String str);

    List<MeausrementEntity> findAllByOrderByMeausrementIdAsc();



    Optional<MeausrementEntity> findByFirstNameIgnoreCase(String name);
    Optional<MeausrementEntity> findByLastNameIgnoreCase(String name);
    Optional<MeausrementEntity> findByMobileNo(String  mobNo);


}
