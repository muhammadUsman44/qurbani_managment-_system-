package edu.imdadia.tailor.service;

import edu.imdadia.tailor.Entity.UserEntity;

public interface UserService {


    UserEntity searchByUserName(String str);

    void save(UserEntity user);

    void delete(UserEntity user);

    void deleteAll();
}

