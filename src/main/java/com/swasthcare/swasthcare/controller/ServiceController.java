package com.swasthcare.swasthcare.controller;

import com.swasthcare.swasthcare.entity.Service;
import com.swasthcare.swasthcare.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    // ✅ Accessible to all (USER + ADMIN)
    @GetMapping
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    // ✅ ADMIN only (secured in SecurityConfig)
    @PostMapping("/add")
    public Service addService(@RequestBody Service service) {
        return serviceRepository.save(service);
    }
}
