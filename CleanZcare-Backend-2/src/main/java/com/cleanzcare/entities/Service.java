package com.cleanzcare.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long serviceId;

	    @Column(nullable = false)
	    private String serviceName;

	    private String description;

	    @Column(nullable = false)
	    private Double price;

	    @Column(name = "created_at", updatable = false)
	    private LocalDateTime createdAt;

	    @PrePersist
	    public void prePersist() {
	        this.createdAt = LocalDateTime.now();
	    }

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "category_id", nullable = false)
	    private ServiceCategory serviceCategory;

}
