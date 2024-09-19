package com.anproject.trailer_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anproject.trailer_app.dto.request.TrailerRequestDTO;
import com.anproject.trailer_app.dto.request.TrailerUpdateDTO;
import com.anproject.trailer_app.dto.response.TrailerResponseDTO;
import com.anproject.trailer_app.service.TrailerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/trailer")
public class TrailerController {

	private final TrailerService trailerService;

	@Autowired
	public TrailerController(TrailerService trailerService) {
		this.trailerService = trailerService;
	}

	@PostMapping("/save")
	public ResponseEntity<Void> saveTrailer(@Valid @RequestBody TrailerRequestDTO trailerRequestDto) {
		trailerService.saveTrailer(trailerRequestDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Void> updateTrailer(@PathVariable Long id, @Valid @RequestBody TrailerUpdateDTO trailerUpdateDto) {
		trailerUpdateDto.setId(id);
		trailerService.updateTrailer(trailerUpdateDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteTrailer(@PathVariable Long id) {
		trailerService.deleteTrailer(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/get-by-id/{id}")
	public ResponseEntity<TrailerResponseDTO> getTrailerById(@PathVariable Long id) {
		TrailerResponseDTO trailerResponseDto = trailerService.getTrailerbyId(id);
		return new ResponseEntity<>(trailerResponseDto, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<TrailerResponseDTO>> getAllTrailer() {
		List<TrailerResponseDTO> trailerResponseDtoList = trailerService.getAllTrailers();
		return new ResponseEntity<>(trailerResponseDtoList, HttpStatus.OK);
	}

}
