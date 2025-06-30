package com.swasthcare.swasthcare.repository;

import com.swasthcare.swasthcare.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}