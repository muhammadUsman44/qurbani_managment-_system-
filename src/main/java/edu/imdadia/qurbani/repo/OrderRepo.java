package edu.imdadia.qurbani.repo;

import edu.imdadia.qurbani.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {

     List<OrderEntity> findAllByOrderByOrderIdAsc();

}
