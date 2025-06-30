package com.swasthcare.swasthcare.controller;

import com.swasthcare.swasthcare.entity.Booking;
import com.swasthcare.swasthcare.entity.Service;
import com.swasthcare.swasthcare.entity.User;
import com.swasthcare.swasthcare.repository.BookingRepository;
import com.swasthcare.swasthcare.repository.ServiceRepository;
import com.swasthcare.swasthcare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ServiceRepository serviceRepo;

    // ✅ USER books a service
    @PostMapping("/create/{serviceId}")
    public String createBooking(@PathVariable Long serviceId, @RequestBody Booking booking, Authentication auth) {
        String email = auth.getName();
        User user = userRepo.findByEmail(email);
        Service service = serviceRepo.findById(serviceId).orElse(null);

        if (service == null) return "Service not found";

        booking.setUser(user);
        booking.setService(service);
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("PENDING");

        bookingRepo.save(booking);
        return "Booking placed successfully!";
    }

    // ✅ USER views own bookings
    @GetMapping("/my")
    public List<Booking> getUserBookings(Authentication auth) {
        String email = auth.getName();
        User user = userRepo.findByEmail(email);
        return bookingRepo.findByUserId(user.getId());
    }

    // ✅ ADMIN views all bookings
    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }
}
