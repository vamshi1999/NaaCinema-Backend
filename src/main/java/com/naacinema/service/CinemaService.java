package com.naacinema.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.naacinema.entity.Cinema;

public interface CinemaService {

	String addCinema(String cinemaName, String description, int rating, String genre, String language, MultipartFile image) throws IOException;
	String updateCinema(Cinema cinema, Long id);
	List<Cinema> getAllCinemas();
	Cinema getCinemaById(Long id);
	List<Cinema> getCinemaByName(String cinemaName);
	List<Cinema> getCinemaByGenre(String genre);
	List<Cinema> sortCinemaByRating();
	String deleteCinema(Long id);
}
