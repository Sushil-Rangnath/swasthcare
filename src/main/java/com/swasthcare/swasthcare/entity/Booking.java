package com.swasthcare.swasthcare.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Service service;

    private LocalDateTime bookingTime;
    private String status; // PENDING, CONFIRMED, CANCELLED
    private String notes;
}