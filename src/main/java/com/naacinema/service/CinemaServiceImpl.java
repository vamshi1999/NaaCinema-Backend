package com.naacinema.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naacinema.entity.Cinema;
import com.naacinema.exception.CinemaNotFoundException;
import com.naacinema.exception.InvalidImageFormatException;
import com.naacinema.repository.CinemaRepository;

@Service
public class CinemaServiceImpl implements CinemaService{
	
	@Autowired
	private CinemaRepository cineRepo;
	
	private final List<String> allowedImageFormats = Arrays.asList(".jpg", ".jpeg", ".png", ".gif");
	
	@Value("${spring.servlet.multipart.location}")
	private String fileUploadPath;
	
	@Override
	public String addCinema(String cinemaName, String description, int rating, String genre, String language, MultipartFile image) throws IOException {
		String msg="Unable to add cinema!!";
		
		Cinema cinema = Cinema.builder()
								.cinemaName(cinemaName)
								.description(description)
								.genre(genre)
								.rating(rating)
								.language(language)
								.build();
		
		String originalFilename = image.getOriginalFilename();
		String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

		if (!allowedImageFormats.contains(fileExtension.toLowerCase())) {
			throw new InvalidImageFormatException("Invalid image format");
		} else {
			String imagePath = saveImage(cinema, image);
			cinema.setImagePath(imagePath);
			this.cineRepo.save(cinema);
			
			msg="Cinema added successfully!!";
		}

		return msg;
	}
	
	private String saveImage(Cinema cinema, MultipartFile image) throws IOException {
		String originalFilename = image.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        
        String filename = cinema.getCinemaName()+"-"+cinema.getRating()+fileExtension;
        Path filePath = Paths.get(fileUploadPath + filename);
        Files.write(filePath, image.getBytes());
        return filename;
    }

	@Override
	public String updateCinema(Cinema cinema, Long id) {
		String msg="Cinema Not Found!!";
		Cinema cinemaobj=this.cineRepo.findById(id).orElseThrow(() -> new CinemaNotFoundException("Cinema Not Found!!"));
		if(cinemaobj!=null) {
			cinemaobj.setCinemaName(cinema.getCinemaName());
			cinemaobj.setDescription(cinema.getDescription());
			cinemaobj.setGenre(cinema.getGenre());
			cinemaobj.setLanguage(cinema.getLanguage());
			cinemaobj.setRating(cinema.getRating());
			
			this.cineRepo.save(cinemaobj);
			
			msg="Cinema updated successfully";
		}
		return msg;
	}

	@Override
	public List<Cinema> getAllCinemas() {
		List<Cinema> list=this.cineRepo.findAll();
		
		return list;
	}

	@Override
	public Cinema getCinemaById(Long id) {
		Cinema cinema=this.cineRepo.findById(id).orElseThrow(() -> new CinemaNotFoundException("Cinema not Found"));
		
		return cinema;
	}

	@Override
	public List<Cinema> getCinemaByName(String cinemaName) {
		List<Cinema> list=this.cineRepo.findByCinemaNameContainingIgnoreCase(cinemaName);
		return list;
	}

	@Override
	public List<Cinema> getCinemaByGenre(String genre) {
		List<Cinema> list=this.cineRepo.findByGenre(genre);
		
		return list;
	}

	@Override
	public List<Cinema> sortCinemaByRating() {
		List<Cinema> list=this.cineRepo.findByOrderByRatingDesc();
		return list;
	}

	@Override
	public String deleteCinema(Long id) {
		
		String msg="Cinema not found!!";
		Optional<Cinema> optional=this.cineRepo.findById(id);
		if(optional.isEmpty()) {
			throw new CinemaNotFoundException("Cinema Not Found!!");
		}else {
			this.cineRepo.deleteById(id);
			msg="Cinema deleted successfully";
		}
		
		return msg;
	}

}
