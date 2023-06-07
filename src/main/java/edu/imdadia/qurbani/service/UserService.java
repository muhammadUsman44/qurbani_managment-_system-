package edu.imdadia.qurbani.service;

import edu.imdadia.qurbani.Entity.UserEntity;

public interface UserService {


    UserEntity searchByUserName(String str);

    void save(UserEntity user);

    void delete(UserEntity user);

    void deleteAll();
}

