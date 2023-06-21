package edu.imdadia.qurbani.service;

import edu.imdadia.qurbani.Entity.DataManagementEntity;
import edu.imdadia.qurbani.repo.DataManagementRepo;
import edu.imdadia.qurbani.util.JavaFXUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DataManagementServiceOKR implements DataManagementService{

    private final DataManagementRepo dataManagementRepo;

    @Override
    public void deleteByFirstName(String item) {
        try {
            dataManagementRepo.deleteByFirstName(item);
        }catch (Exception s){
            JavaFXUtils.showError("THIS NAME IS NOT FOUND");
        }

    }

    @Override
    public void deleteByLastName(String item) {

        try {
            dataManagementRepo.deleteByLastName(item);
        }catch (Exception s){
            JavaFXUtils.showError("THIS NAME IS NOT FOUND");
        }
    }

    @Override
    public void deleteByMob(String item) {
        try {
            dataManagementRepo.deleteByMobileNo(item);
        }catch (Exception s){
            JavaFXUtils.showError("THIS NAME IS NOT FOUND");
        }
    }

    @Override
    public void deleteAll() {
        dataManagementRepo.deleteAll();
    }

    @Override
    public List<DataManagementEntity> getAll(){
        return dataManagementRepo.findAllBy();
    }

    @Override
    public DataManagementEntity save(DataManagementEntity dataManagement) {
        if (dataManagement !=null){
            try {
                dataManagementRepo.save(dataManagement);
            }catch (Exception s){
                JavaFXUtils.showError(s.getMessage());
                System.out.printf(s.getMessage());
            }
        }
        return dataManagement;
    }


    @Override
    public DataManagementEntity findByFirstName(String item) {
        Optional<DataManagementEntity>data = dataManagementRepo.findByFirstNameIgnoreCase(item);
        return data.orElse(null);
    }

    @Override
    public DataManagementEntity findByLastName(String item) {
        Optional<DataManagementEntity> data = dataManagementRepo.findByLastName(item);
        return data.orElse(null);
    }

    @Override
    public DataManagementEntity findByMob(String item) {
        Optional<DataManagementEntity> data = dataManagementRepo.findByLastNameIgnoreCase(item);
        return data.orElse(null);
    }
}
