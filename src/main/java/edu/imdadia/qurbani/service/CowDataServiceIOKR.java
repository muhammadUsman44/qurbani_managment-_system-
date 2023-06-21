package edu.imdadia.qurbani.service;

import edu.imdadia.qurbani.Entity.CowDataEntity;
import edu.imdadia.qurbani.repo.CowDataRepo;
import edu.imdadia.qurbani.util.JavaFXUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CowDataServiceIOKR implements CowDataService {

    private final CowDataRepo cowDataRepo;

    @Override
    public void save(CowDataEntity cowDataEntity) {
        if (cowDataEntity != null) {
            cowDataRepo.save(cowDataEntity);
        } else {
            JavaFXUtils.showError("..............");
        }
    }

    @Override
    public void delete(CowDataEntity cowDataEntity) {
        try {
            cowDataRepo.delete(cowDataEntity);
        } catch (Exception e) {
            JavaFXUtils.showError("WRONG ENTRY");
        }

    }

    @Override
    public void deleteById(Integer id) {
        try {
            cowDataRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            JavaFXUtils.showWarningMessage("............");
        } catch (Exception e) {
            JavaFXUtils.showWarningMessage("............");
        }
    }

    @Override
    public Optional<CowDataEntity> findById(Integer integer) {

        return cowDataRepo.findById(integer);

    }
//    @Override
//    public OrderEntity searchByUserName(String str) {
//        Optional<OrderEntity> orderEntity = orderRepo.cowNumberField();
//        return orderEntity.orElse(null);
//    }

        @Override
    public Optional<CowDataEntity> findCowNumber(String name) {
        return cowDataRepo.cowNumberField(name);
    }

    @Override
    public void saveAll(List<CowDataEntity> orderEntities) {
        cowDataRepo.saveAll(orderEntities);
    }

    @Override
    public void deleteAll() {
        try {
            cowDataRepo.deleteAll();
        }catch (Exception e){
            JavaFXUtils.showError(e.getMessage());
        }
    }

    @Override
    public List<CowDataEntity> getAll() {
        return cowDataRepo.findAllByOrderByOrderIdAsc();
    }

    @Override
    public CowDataEntity searchByRegNo(Integer integer) {
        Optional<CowDataEntity> o = cowDataRepo.findById(integer);
        CowDataEntity cowDataEntity = o.orElse(null);
        return cowDataEntity;
    }
    @Override
    public List<CowDataEntity> findByCowNumberField(String str) {

        return cowDataRepo.findByCowNumberField(str);
    }
}
