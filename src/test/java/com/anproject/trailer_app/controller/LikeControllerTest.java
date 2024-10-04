package com.anproject.trailer_app.controller;

import com.anproject.trailer_app.dto.request.LikeRequestDTO;
import com.anproject.trailer_app.dto.request.LikeUpdateDTO;
import com.anproject.trailer_app.dto.response.LikeResponseDTO;
import com.anproject.trailer_app.service.LikeService;
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

class LikeControllerTest {

	@Mock
	private LikeService likeService;

	@InjectMocks
	private LikeController likeController;

	public LikeControllerTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void saveLike() {
		LikeRequestDTO requestDto = new LikeRequestDTO();
		doNothing().when(likeService).saveLike(requestDto);
		ResponseEntity<Void> response = likeController.saveLike(requestDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(likeService, times(1)).saveLike(requestDto);
	}

	@Test
	void updateLike() {
		Long likeId = 1L;
		LikeUpdateDTO updateDto = new LikeUpdateDTO();
		updateDto.setId(likeId);
		doNothing().when(likeService).updateLike(updateDto);
		ResponseEntity<Void> response = likeController.updateLike(likeId, updateDto);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(likeService, times(1)).updateLike(updateDto);
	}

	@Test
	void deleteLike() {
		Long likeId = 1L;
		doNothing().when(likeService).deleteLike(likeId);
		ResponseEntity<Void> response = likeController.deleteLike(likeId);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(likeService, times(1)).deleteLike(likeId);
	}

	@Test
	void getLikeById() {
		Long likeId = 1L;
		LikeResponseDTO responseDto = new LikeResponseDTO();
		when(likeService.getLikebyId(likeId)).thenReturn(responseDto);
		ResponseEntity<LikeResponseDTO> response = likeController.getLikeById(likeId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(responseDto, response.getBody());
	}

	@Test
	void getAllLikes() {
		LikeResponseDTO responseDto = new LikeResponseDTO();
		when(likeService.getAllLikes()).thenReturn(Collections.singletonList(responseDto));
		ResponseEntity<List<LikeResponseDTO>> response = likeController.getAllLikes();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
	}
}
