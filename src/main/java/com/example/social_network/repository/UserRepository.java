package com.example.social_network.repository;

import com.example.social_network.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);

    public User findUserByEmail(String email);

    @Query("select u from User u where u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
    public List<User> searchUser(@Param("query") String query);
}
