package edu.imdadia.qurbani.repo;

import edu.imdadia.qurbani.Entity.DataManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataManagementRepo extends JpaRepository<DataManagementEntity,String> {


    Optional<DataManagementEntity> findByMobileNo(String str);

    Optional<DataManagementEntity> findByFirstName(String str);

    Optional<DataManagementEntity> findByLastName(String str);
//
//    List<DataManagementEntity> findAllByOrderByOrderIdAsc();

    Optional<DataManagementEntity> deleteByMobileNo(String str);

    Optional<DataManagementEntity> deleteByFirstName(String str);

    Optional<DataManagementEntity> deleteByLastName(String str);

}