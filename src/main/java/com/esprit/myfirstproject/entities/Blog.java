package com.esprit.myfirstproject.entities;

import java.time.LocalDate;

import com.esprit.myfirstproject.entities.enums.BlogStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private String titre;
	 private String description;
	 @Enumerated(EnumType.STRING) 
	 private BlogStatus status;
	 private LocalDate datecreation;
	 @OneToOne
	 private Image image;
}
