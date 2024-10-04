package com.anproject.trailer_app.service;

import com.anproject.trailer_app.dto.request.TrailerCommentRequestDTO;
import com.anproject.trailer_app.dto.request.TrailerCommentUpdateDTO;
import com.anproject.trailer_app.dto.response.TrailerCommentResponseDTO;
import com.anproject.trailer_app.entity.AppUser;
import com.anproject.trailer_app.entity.Trailer;
import com.anproject.trailer_app.entity.TrailerComment;
import com.anproject.trailer_app.exception.ApiNotFoundException;
import com.anproject.trailer_app.exception.ApiRequestException;
import com.anproject.trailer_app.mapper.TrailerCommentMapper;
import com.anproject.trailer_app.repository.AppUserRepository;
import com.anproject.trailer_app.repository.TrailerCommentRepository;
import com.anproject.trailer_app.repository.TrailerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrailerCommentServiceTest {

	@Mock
	private TrailerCommentRepository trailerCommentRepository;

	@Mock
	private AppUserRepository appUserRepository;

	@Mock
	private TrailerRepository trailerRepository;

	@Mock
	private TrailerCommentMapper trailerCommentMapper;

	@InjectMocks
	private TrailerCommentService trailerCommentService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void whenSaveTrailerComment_thenTrailerCommentShouldBeSaved() {
		TrailerCommentRequestDTO trailerCommentRequestDTO = new TrailerCommentRequestDTO();
		trailerCommentRequestDTO.setUserId(1L);
		trailerCommentRequestDTO.setTrailerId(1L);
		TrailerComment trailerComment = new TrailerComment();

		when(trailerCommentMapper.trailerCommentRequestDTOtoTrailer(trailerCommentRequestDTO))
				.thenReturn(trailerComment);

		trailerCommentService.saveTrailerComment(trailerCommentRequestDTO);

		verify(trailerCommentRepository, times(1)).save(trailerComment);
		assertNotNull(trailerComment.getCreatedAt());
	}

	@Test
	void whenSaveTrailerComment_withNullRequest_thenThrowException() {
		assertThrows(ApiRequestException.class, () -> trailerCommentService.saveTrailerComment(null));
	}

	@Test
	void whenSaveTrailerComment_withMissingUserIdOrTrailerId_thenThrowException() {
		TrailerCommentRequestDTO trailerCommentRequestDTO = new TrailerCommentRequestDTO();
		assertThrows(ApiRequestException.class,
				() -> trailerCommentService.saveTrailerComment(trailerCommentRequestDTO));
	}

	@Test
	void whenUpdateTrailerComment_thenTrailerCommentShouldBeUpdated() {
		TrailerCommentUpdateDTO trailerCommentUpdateDTO = new TrailerCommentUpdateDTO();
		trailerCommentUpdateDTO.setId(1L);
		trailerCommentUpdateDTO.setUserId(1L);
		trailerCommentUpdateDTO.setTrailerId(1L);
		TrailerComment existingTrailerComment = new TrailerComment();
		existingTrailerComment.setCreatedAt(new Date());
		AppUser appUser = new AppUser();
		Trailer trailer = new Trailer();
		TrailerComment updatedTrailerComment = new TrailerComment();

		when(trailerCommentRepository.findById(trailerCommentUpdateDTO.getId()))
				.thenReturn(Optional.of(existingTrailerComment));
		when(appUserRepository.findById(trailerCommentUpdateDTO.getUserId())).thenReturn(Optional.of(appUser));
		when(trailerRepository.findById(trailerCommentUpdateDTO.getTrailerId())).thenReturn(Optional.of(trailer));
		when(trailerCommentMapper.trailerCommentUpdateDTOtoTrailer(trailerCommentUpdateDTO))
				.thenReturn(updatedTrailerComment);

		trailerCommentService.updateTrailerComment(trailerCommentUpdateDTO);

		verify(trailerCommentRepository, times(1)).save(updatedTrailerComment);
		assertEquals(existingTrailerComment.getCreatedAt(), updatedTrailerComment.getCreatedAt());
		assertEquals(appUser, updatedTrailerComment.getUser());
		assertEquals(trailer, updatedTrailerComment.getTrailer());
	}

	@Test
	void whenUpdateTrailerComment_withNullId_thenThrowException() {
		TrailerCommentUpdateDTO trailerCommentUpdateDTO = new TrailerCommentUpdateDTO();
		assertThrows(ApiRequestException.class,
				() -> trailerCommentService.updateTrailerComment(trailerCommentUpdateDTO));
	}

	@Test
	void whenUpdateTrailerComment_withNonExistingTrailerComment_thenThrowNotFoundException() {
		TrailerCommentUpdateDTO trailerCommentUpdateDTO = new TrailerCommentUpdateDTO();
		trailerCommentUpdateDTO.setId(1L);

		when(trailerCommentRepository.findById(trailerCommentUpdateDTO.getId())).thenReturn(Optional.empty());

		assertThrows(ApiNotFoundException.class,
				() -> trailerCommentService.updateTrailerComment(trailerCommentUpdateDTO));
	}

	@Test
	void whenDeleteTrailerComment_thenTrailerCommentShouldBeDeleted() {
		Long commentId = 1L;

		when(trailerCommentRepository.existsById(commentId)).thenReturn(true);

		trailerCommentService.deleteTrailerComment(commentId);

		verify(trailerCommentRepository, times(1)).deleteById(commentId);
	}

	@Test
	void whenDeleteTrailerComment_withNonExistingComment_thenThrowNotFoundException() {
		Long commentId = 1L;

		when(trailerCommentRepository.existsById(commentId)).thenReturn(false);

		assertThrows(ApiNotFoundException.class, () -> trailerCommentService.deleteTrailerComment(commentId));
	}

	@Test
	void whenGetTrailerCommentById_thenTrailerCommentShouldBeReturned() {
		Long commentId = 1L;
		TrailerComment trailerComment = new TrailerComment();
		TrailerCommentResponseDTO trailerCommentResponseDTO = new TrailerCommentResponseDTO();

		when(trailerCommentRepository.findById(commentId)).thenReturn(Optional.of(trailerComment));
		when(trailerCommentMapper.trailerCommentToTrailerCommentResponseDTO(trailerComment))
				.thenReturn(trailerCommentResponseDTO);

		TrailerCommentResponseDTO result = trailerCommentService.getTrailerCommentbyId(commentId);

		assertNotNull(result);
		verify(trailerCommentRepository, times(1)).findById(commentId);
	}

	@Test
	void whenGetTrailerCommentById_withNonExistingComment_thenThrowNotFoundException() {
		Long commentId = 1L;

		when(trailerCommentRepository.findById(commentId)).thenReturn(Optional.empty());

		assertThrows(ApiNotFoundException.class, () -> trailerCommentService.getTrailerCommentbyId(commentId));
	}

	@Test
	void whenGetAllTrailerComments_thenReturnTrailerCommentList() {
		TrailerComment trailerComment = new TrailerComment();
		TrailerCommentResponseDTO trailerCommentResponseDTO = new TrailerCommentResponseDTO();

		when(trailerCommentRepository.findAll()).thenReturn(Collections.singletonList(trailerComment));
		when(trailerCommentMapper.trailerCommentToTrailerCommentResponseDTO(trailerComment))
				.thenReturn(trailerCommentResponseDTO);

		List<TrailerCommentResponseDTO> result = trailerCommentService.getAllTrailerComments();

		assertFalse(result.isEmpty());
		verify(trailerCommentRepository, times(1)).findAll();
	}
}
