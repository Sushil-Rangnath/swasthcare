package com.swasthcare.swasthcare.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;            // e.g. Doctor Visit
    private String type;            // e.g. HomeCare, Ambulance
    private String description;
    private double price;
    private String imageUrl;
}