package com.swasthcare.swasthcare.repository;


import com.swasthcare.swasthcare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
