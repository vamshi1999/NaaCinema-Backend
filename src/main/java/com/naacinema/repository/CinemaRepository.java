package com.naacinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naacinema.entity.Cinema;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long>{

	List<Cinema> findByCinemaNameContainingIgnoreCase(String cinemaName); 

	List<Cinema> findByOrderByRatingDesc();
	
	List<Cinema> findByGenre(String genre);
}
