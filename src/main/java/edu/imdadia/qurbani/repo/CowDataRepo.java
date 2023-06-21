package edu.imdadia.qurbani.repo;

import edu.imdadia.qurbani.Entity.CowDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CowDataRepo extends JpaRepository<CowDataEntity, Integer> {

     List<CowDataEntity> findAllByOrderByOrderIdAsc();

     Optional<CowDataEntity> cowNumberField(String name);
     List<CowDataEntity> findByCowNumberField(String name);

}
