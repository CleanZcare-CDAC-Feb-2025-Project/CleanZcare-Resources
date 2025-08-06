package com.cleanzcare.dto;

import com.cleanzcare.entities.Role;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long userId;
    private String fullName;
    private String email;
    private String mobileNo;
    private Role userRole;
    private String status;
}
