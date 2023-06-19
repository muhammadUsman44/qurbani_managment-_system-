package edu.imdadia.qurbani.service;


import edu.imdadia.qurbani.Entity.OrderEntity;
import edu.imdadia.qurbani.Entity.UserEntity;


import java.util.List;
import java.util.Optional;

public interface OrderService {


    void save(OrderEntity orderEntity);

    void delete(OrderEntity orderEntity);

    void deleteById(Integer id);


    Optional<OrderEntity> findById(Integer integer);

    Optional<OrderEntity> findCowNumber(String str);


    void saveAll(List<OrderEntity> orderEntities);

    void deleteAll();

    List<OrderEntity> getAll();

    OrderEntity searchByRegNo(Integer integer);

    List<OrderEntity>findByCowNumberField(String str);

}
