package com.cleanzcare.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "service_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceCategory {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long categoryId;

	    @NotBlank(message = "Category name is required")
	    @Column(nullable = false)
	    private String categoryName;

	    private String description;

	    @Column(name = "created_at")
	    private LocalDateTime createdAt;

	    @PrePersist
	    public void prePersist() {
	        this.createdAt = LocalDateTime.now();
	    }
	    
	    @OneToMany(mappedBy = "serviceCategory", cascade = CascadeType.ALL)
	    private List<ServiceEntity> services;

}
