package edu.imdadia.tailor.repo;

import edu.imdadia.tailor.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserName(String UserName);


}