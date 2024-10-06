package com.naacinema.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.naacinema.entity.Cinema;
import com.naacinema.service.CinemaService;

@RestController
@RequestMapping("/naacinema")
@CrossOrigin(origins = "*")
public class CinemaController {

	@Autowired
	private CinemaService cinemaService;
	
	@PostMapping(value="/cinema/add-cinema")
	public ResponseEntity<String> addCinema(@RequestParam String cinemaName, @RequestParam String description, @RequestParam int rating, @RequestParam String genre, @RequestParam String language, @RequestParam MultipartFile image) throws IOException{
		String msg=this.cinemaService.addCinema(cinemaName, description, rating, genre, language, image);
		
		return new ResponseEntity<>(msg,HttpStatus.CREATED);
	}
	
	
	@PutMapping(value="/cinema/{id}")
	public ResponseEntity<String> updateCinema(@RequestBody Cinema cinema, @PathVariable Long id){
		String msg=this.cinemaService.updateCinema(cinema, id);
		
		return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/all-cinemas")
	public ResponseEntity<List<Cinema>> getAllCinemas(){
		List<Cinema> list=this.cinemaService.getAllCinemas();
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/cinema/{id}")
	public ResponseEntity<Cinema> getCinemaById(@PathVariable Long id){
		Cinema cinema=this.cinemaService.getCinemaById(id);
		
		return new ResponseEntity<>(cinema,HttpStatus.OK);
	}
	
	@GetMapping("/cinema/name")
	public ResponseEntity<List<Cinema>> getCinemaByName(@RequestParam("name") String cinemaName){
		List<Cinema> list=this.cinemaService.getCinemaByName(cinemaName);
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/cinema/genre")
	public ResponseEntity<List<Cinema>> getCinemaByGenre(@RequestParam("genre") String genre){
		List<Cinema> list=this.cinemaService.getCinemaByGenre(genre);
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/cinema/sort-by-rating-desc")
	public ResponseEntity<List<Cinema>> sortCinemabyRatingDesc(){
		List<Cinema> list=this.cinemaService.sortCinemaByRatingDesc();
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/cinema/sort-by-rating-asc")
	public ResponseEntity<List<Cinema>> sortCinemabyRatingAsc(){
		List<Cinema> list=this.cinemaService.sortCinemaByRatingAsc();
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@DeleteMapping("/cinema/{id}")
	public ResponseEntity<String> deleteCinema(@PathVariable Long id){
		String msg=this.cinemaService.deleteCinema(id);
		
		return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
	}
}
