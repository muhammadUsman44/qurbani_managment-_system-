package edu.imdadia.tailor.service;

import edu.imdadia.tailor.Entity.MeausrementEntity;
import edu.imdadia.tailor.repo.MeausrementRepo;
import edu.imdadia.tailor.util.JavaFXUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MeausrementServiceImpl implements MeausrementService {

    private final MeausrementRepo meausrementRepo;

    @Override
    public void deleteAll() {
        meausrementRepo.deleteAll();
    }

    @Override
    public void deleteById(int id) {
        try {
            meausrementRepo.deleteById(id);
        } catch (Exception e) {
            JavaFXUtils.showError("THIS ID IS NOT FOUND");
        }
    }

    @Override
    public void deleteByFirstName(String name) {
        try {
            meausrementRepo.deleteByLastName(name);
        } catch (Exception e) {
            JavaFXUtils.showError("THIS NAME IS NOT FOUND");
        }
    }

    @Override
    public void deleteByLastName(String name) {
        try {
            meausrementRepo.deleteByFirstName(name);
        } catch (Exception e) {
            JavaFXUtils.showError("THIS NAME IS NOT FOUND");
        }
    }

    @Override
    public void deleteByMobNo(String MobNo) {
        try {
            meausrementRepo.deleteByMobileNo(MobNo);
        } catch (Exception e) {
            JavaFXUtils.showError("THIS MOB NO IS NOT FOUND");
        }
    }


    @Override
    public List<MeausrementEntity> findAllByOrderByMeausrementIdAsc() {
            return meausrementRepo.findAllByOrderByMeausrementIdAsc();

    }

    @Override
    public void save(MeausrementEntity meausrement) {
        if (meausrement != null) {
            try {
                meausrementRepo.save(meausrement);
            } catch (Exception e) {
                JavaFXUtils.showError(e.getMessage());
            }
        }
    }

    @Override
    public MeausrementEntity findById(Integer integer) {
        Optional<MeausrementEntity> meausrement = meausrementRepo.findById(integer);
        return meausrement.orElse(null);
    }

    @Override
    public MeausrementEntity findByFirstName(String name) {
        Optional<MeausrementEntity> meausrement = meausrementRepo.findByFirstNameIgnoreCase(name);
        return meausrement.orElse(null);

    }

    @Override
    public MeausrementEntity findByLast(String name) {
        Optional<MeausrementEntity> meausrement = meausrementRepo.findByLastNameIgnoreCase(name);
        return meausrement.orElse(null);
    }

    @Override
    public MeausrementEntity findByMobileNo(String mobNo) {
        Optional<MeausrementEntity> meausrement = meausrementRepo.findByMobileNo(mobNo);
        return meausrement.orElse(null);
    }
}
