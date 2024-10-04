package com.anproject.trailer_app.controller;

import com.anproject.trailer_app.dto.request.TrailerRequestDTO;
import com.anproject.trailer_app.dto.request.TrailerUpdateDTO;
import com.anproject.trailer_app.dto.response.TrailerResponseDTO;
import com.anproject.trailer_app.service.TrailerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TrailerControllerTest {

	@Mock
	private TrailerService trailerService;

	@InjectMocks
	private TrailerController trailerController;

	public TrailerControllerTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void saveTrailer() {
		TrailerRequestDTO requestDto = new TrailerRequestDTO();
		doNothing().when(trailerService).saveTrailer(requestDto);
		ResponseEntity<Void> response = trailerController.saveTrailer(requestDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(trailerService, times(1)).saveTrailer(requestDto);
	}

	@Test
	void updateTrailer() {
		Long trailerId = 1L;
		TrailerUpdateDTO updateDto = new TrailerUpdateDTO();
		updateDto.setId(trailerId);
		doNothing().when(trailerService).updateTrailer(updateDto);
		ResponseEntity<Void> response = trailerController.updateTrailer(trailerId, updateDto);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(trailerService, times(1)).updateTrailer(updateDto);
	}

	@Test
	void deleteTrailer() {
		Long trailerId = 1L;
		doNothing().when(trailerService).deleteTrailer(trailerId);
		ResponseEntity<Void> response = trailerController.deleteTrailer(trailerId);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(trailerService, times(1)).deleteTrailer(trailerId);
	}

	@Test
	void getTrailerById() {
		Long trailerId = 1L;
		TrailerResponseDTO responseDto = new TrailerResponseDTO();
		when(trailerService.getTrailerbyId(trailerId)).thenReturn(responseDto);
		ResponseEntity<TrailerResponseDTO> response = trailerController.getTrailerById(trailerId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(responseDto, response.getBody());
	}

	@Test
	void getAllTrailers() {
		TrailerResponseDTO responseDto = new TrailerResponseDTO();
		when(trailerService.getAllTrailers()).thenReturn(Collections.singletonList(responseDto));
		ResponseEntity<List<TrailerResponseDTO>> response = trailerController.getAllTrailer();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
	}
}
