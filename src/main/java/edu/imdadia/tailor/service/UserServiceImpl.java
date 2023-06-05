package edu.imdadia.tailor.service;

import edu.imdadia.tailor.Entity.UserEntity;
import edu.imdadia.tailor.repo.UserRepo;
import edu.imdadia.tailor.util.JavaFXUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{


    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserEntity searchByUserName(String str) {
        Optional<UserEntity> userEntities = userRepo.findByUserName(str);
        return userEntities.orElse(null);
    }

    @Override
    public void save(UserEntity user) {
        if (user == null) {
            JavaFXUtils.showError("..............");
        } else {
            userRepo.save(user);
        }
    }

    @Override
    public void delete(UserEntity user) {
        try {
            userRepo.delete(user);
        } catch (Exception e) {
            JavaFXUtils.showError(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        userRepo.deleteAll();
    }
}
