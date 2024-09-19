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

import com.anproject.trailer_app.dto.request.TrailerCommentRequestDTO;
import com.anproject.trailer_app.dto.request.TrailerCommentUpdateDTO;
import com.anproject.trailer_app.dto.response.TrailerCommentResponseDTO;
import com.anproject.trailer_app.service.TrailerCommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/trailer-comments")
public class TrailerCommentController {

	private final TrailerCommentService trailerCommentService;

	@Autowired
	public TrailerCommentController(TrailerCommentService trailerCommentService) {
		this.trailerCommentService = trailerCommentService;
	}

	@PostMapping("/save")
	public ResponseEntity<Void> saveTrailerComment(@Valid @RequestBody TrailerCommentRequestDTO trailerCommentRequestDto) {
		trailerCommentService.saveTrailerComment(trailerCommentRequestDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Void> updateTrailerComment(@PathVariable Long id,
			@Valid @RequestBody TrailerCommentUpdateDTO trailerCommentUpdateDto) {
		trailerCommentUpdateDto.setId(id);
		trailerCommentService.updateTrailerComment(trailerCommentUpdateDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteTrailerComment(@PathVariable Long id) {
		trailerCommentService.deleteTrailerComment(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/get-by-id/{id}")
	public ResponseEntity<TrailerCommentResponseDTO> getTrailerCommentById(@PathVariable Long id) {
		TrailerCommentResponseDTO trailerCommentResponseDto = trailerCommentService.getTrailerCommentbyId(id);
		return new ResponseEntity<>(trailerCommentResponseDto, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<TrailerCommentResponseDTO>> getAllTrailerComments() {
		List<TrailerCommentResponseDTO> trailerCommentResponseDtoList = trailerCommentService.getAllTrailerComments();
		return new ResponseEntity<>(trailerCommentResponseDtoList, HttpStatus.OK);
	}

}
