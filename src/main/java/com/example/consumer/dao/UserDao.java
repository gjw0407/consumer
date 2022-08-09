package com.example.consumer.dao;

import com.example.consumer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer>, QueryByExampleExecutor<User> {

    @Query("select password from user where email = :email")
    Optional<String> findPwd(@Param("email") String email);

    boolean existsByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);
}
