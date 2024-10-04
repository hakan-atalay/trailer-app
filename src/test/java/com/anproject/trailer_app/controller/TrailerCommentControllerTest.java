package com.anproject.trailer_app.controller;

import com.anproject.trailer_app.dto.request.TrailerCommentRequestDTO;
import com.anproject.trailer_app.dto.request.TrailerCommentUpdateDTO;
import com.anproject.trailer_app.dto.response.TrailerCommentResponseDTO;
import com.anproject.trailer_app.service.TrailerCommentService;
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

class TrailerCommentControllerTest {

	@Mock
	private TrailerCommentService trailerCommentService;

	@InjectMocks
	private TrailerCommentController trailerCommentController;

	public TrailerCommentControllerTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void saveTrailerComment() {
		TrailerCommentRequestDTO requestDto = new TrailerCommentRequestDTO();
		doNothing().when(trailerCommentService).saveTrailerComment(requestDto);
		ResponseEntity<Void> response = trailerCommentController.saveTrailerComment(requestDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(trailerCommentService, times(1)).saveTrailerComment(requestDto);
	}

	@Test
	void updateTrailerComment() {
		Long commentId = 1L;
		TrailerCommentUpdateDTO updateDto = new TrailerCommentUpdateDTO();
		updateDto.setId(commentId);
		doNothing().when(trailerCommentService).updateTrailerComment(updateDto);
		ResponseEntity<Void> response = trailerCommentController.updateTrailerComment(commentId, updateDto);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(trailerCommentService, times(1)).updateTrailerComment(updateDto);
	}

	@Test
	void deleteTrailerComment() {
		Long commentId = 1L;
		doNothing().when(trailerCommentService).deleteTrailerComment(commentId);
		ResponseEntity<Void> response = trailerCommentController.deleteTrailerComment(commentId);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(trailerCommentService, times(1)).deleteTrailerComment(commentId);
	}

	@Test
	void getTrailerCommentById() {
		Long commentId = 1L;
		TrailerCommentResponseDTO responseDto = new TrailerCommentResponseDTO();
		when(trailerCommentService.getTrailerCommentbyId(commentId)).thenReturn(responseDto);
		ResponseEntity<TrailerCommentResponseDTO> response = trailerCommentController.getTrailerCommentById(commentId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(responseDto, response.getBody());
	}

	@Test
	void getAllTrailerComments() {
		TrailerCommentResponseDTO responseDto = new TrailerCommentResponseDTO();
		when(trailerCommentService.getAllTrailerComments()).thenReturn(Collections.singletonList(responseDto));
		ResponseEntity<List<TrailerCommentResponseDTO>> response = trailerCommentController.getAllTrailerComments();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
	}
}
