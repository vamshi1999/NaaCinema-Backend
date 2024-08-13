package com.naacinema.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cinema {
	 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String cinemaName;
	private int rating;
	private String description;
	private String imagePath;
	private String genre;
	private String language;
}
