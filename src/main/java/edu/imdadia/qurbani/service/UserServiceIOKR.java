package edu.imdadia.qurbani.service;

import edu.imdadia.qurbani.Entity.UserEntity;
import edu.imdadia.qurbani.repo.UserRepo;
import edu.imdadia.qurbani.util.JavaFXUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceIOKR implements UserService{


    private final UserRepo userRepo;

    public UserServiceIOKR(UserRepo userRepo) {
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
