package edu.imdadia.qurbani.service;

import edu.imdadia.qurbani.Entity.OrderEntity;
import edu.imdadia.qurbani.repo.OrderRepo;
import edu.imdadia.qurbani.util.JavaFXUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class OrderServiceIOKR implements OrderService {

    private final OrderRepo orderRepo;

    @Override
    public void save(OrderEntity orderEntity) {
        if (orderEntity != null) {
            orderRepo.save(orderEntity);
        } else {
            JavaFXUtils.showError("..............");
        }
    }

    @Override
    public void delete(OrderEntity orderEntity) {
        try {
            orderRepo.delete(orderEntity);
        } catch (Exception e) {
            JavaFXUtils.showError("WRONG ENTRY");
        }

    }

    @Override
    public void deleteById(Integer id) {
        try {
            orderRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            JavaFXUtils.showWarningMessage("............");
        } catch (Exception e) {
            JavaFXUtils.showWarningMessage("............");
        }
    }

    @Override
    public Optional<OrderEntity> findById(Integer integer) {

        return orderRepo.findById(integer);

    }
//    @Override
//    public OrderEntity searchByUserName(String str) {
//        Optional<OrderEntity> orderEntity = orderRepo.cowNumberField();
//        return orderEntity.orElse(null);
//    }

        @Override
    public Optional<OrderEntity> findCowNumber(String name) {
        return orderRepo.cowNumberField(name);
    }

    @Override
    public void saveAll(List<OrderEntity> orderEntities) {
        orderRepo.saveAll(orderEntities);
    }

    @Override
    public void deleteAll() {
        try {
            orderRepo.deleteAll();
        }catch (Exception e){
            JavaFXUtils.showError(e.getMessage());
        }
    }

    @Override
    public List<OrderEntity> getAll() {
        return orderRepo.findAllByOrderByOrderIdAsc();
    }

    @Override
    public OrderEntity searchByRegNo(Integer integer) {
        Optional<OrderEntity> o = orderRepo.findById(integer);
        OrderEntity orderEntity = o.orElse(null);
        return orderEntity;
    }
    @Override
    public List<OrderEntity> findByCowNumberField(String str) {

        return orderRepo.findByCowNumberField(str);
    }
}
