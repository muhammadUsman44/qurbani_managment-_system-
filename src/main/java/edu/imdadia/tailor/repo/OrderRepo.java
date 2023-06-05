package edu.imdadia.tailor.repo;

import edu.imdadia.tailor.Entity.MeausrementEntity;
import edu.imdadia.tailor.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {

     List<OrderEntity> findAllByOrderByOrderIdAsc();

}
