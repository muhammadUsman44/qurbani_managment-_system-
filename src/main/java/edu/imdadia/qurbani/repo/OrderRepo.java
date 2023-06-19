package edu.imdadia.qurbani.repo;

import edu.imdadia.qurbani.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {

     List<OrderEntity> findAllByOrderByOrderIdAsc();

     Optional<OrderEntity> cowNumberField(String name);
     List<OrderEntity> findByCowNumberField(String name);

}
