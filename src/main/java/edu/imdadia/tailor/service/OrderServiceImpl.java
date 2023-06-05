package edu.imdadia.tailor.service;

import edu.imdadia.tailor.Entity.OrderEntity;
import edu.imdadia.tailor.repo.OrderRepo;
import edu.imdadia.tailor.util.JavaFXUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    @Override
    public void save(OrderEntity orderEntity) {
        if (orderEntity == null) {
            JavaFXUtils.showError("..............");
        } else {
            orderRepo.save(orderEntity);
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
}
