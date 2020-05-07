package com.hongwei.model.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User u where u.user_name=:user_name")
    User findUser(@Param("user_name") String user_name);

    @Query("from User u where u.user_name=:user_name and u.password_hash=:password_hash")
    User validate(@Param("user_name") String user_name, @Param("password_hash") String password_hash);

}