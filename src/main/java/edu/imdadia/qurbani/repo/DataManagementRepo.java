package edu.imdadia.qurbani.repo;

import edu.imdadia.qurbani.Entity.DataManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataManagementRepo extends JpaRepository<DataManagementEntity,Integer> {


    Optional<DataManagementEntity> findByLastNameIgnoreCase(String str);

    Optional<DataManagementEntity> findByFirstNameIgnoreCase(String str);

    Optional<DataManagementEntity> findByLastName(String str);
    List<DataManagementEntity> findAllBy();

    Optional<DataManagementEntity> deleteByMobileNo(String str);

    Optional<DataManagementEntity> deleteByFirstName(String str);

    Optional<DataManagementEntity> deleteByLastName(String str);

}