package com.cleanzcare.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProviderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String skills;
    private int experience;

    private boolean approved; // for admin to approve the provider

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}