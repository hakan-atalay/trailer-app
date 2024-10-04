package com.anproject.trailer_app.service;

import com.anproject.trailer_app.dto.request.TrailerRequestDTO;
import com.anproject.trailer_app.dto.request.TrailerUpdateDTO;
import com.anproject.trailer_app.dto.response.TrailerResponseDTO;
import com.anproject.trailer_app.entity.AppUser;
import com.anproject.trailer_app.entity.Category;
import com.anproject.trailer_app.entity.Trailer;
import com.anproject.trailer_app.exception.ApiNotFoundException;
import com.anproject.trailer_app.exception.ApiRequestException;
import com.anproject.trailer_app.mapper.TrailerMapper;
import com.anproject.trailer_app.repository.AppUserRepository;
import com.anproject.trailer_app.repository.CategoryRepository;
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

class TrailerServiceTest {

	@Mock
	private TrailerRepository trailerRepository;

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private AppUserRepository appUserRepository;

	@Mock
	private TrailerMapper trailerMapper;

	@InjectMocks
	private TrailerService trailerService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void whenSaveTrailer_thenTrailerShouldBeSaved() {
		TrailerRequestDTO trailerRequestDTO = new TrailerRequestDTO();
		trailerRequestDTO.setUserId(1L);
		trailerRequestDTO.setCategoryId(1L);
		Trailer trailer = new Trailer();

		when(trailerMapper.trailerRequestDTOtoTrailer(trailerRequestDTO)).thenReturn(trailer);

		trailerService.saveTrailer(trailerRequestDTO);

		verify(trailerRepository, times(1)).save(trailer);
		assertNotNull(trailer.getCreatedAt());
	}

	@Test
	void whenSaveTrailer_withNullRequest_thenThrowException() {
		assertThrows(ApiRequestException.class, () -> trailerService.saveTrailer(null));
	}

	@Test
	void whenSaveTrailer_withMissingCategoryIdOrUserId_thenThrowException() {
		TrailerRequestDTO trailerRequestDTO = new TrailerRequestDTO();
		assertThrows(ApiRequestException.class, () -> trailerService.saveTrailer(trailerRequestDTO));
	}

	@Test
	void whenUpdateTrailer_thenTrailerShouldBeUpdated() {
		TrailerUpdateDTO trailerUpdateDTO = new TrailerUpdateDTO();
		trailerUpdateDTO.setId(1L);
		trailerUpdateDTO.setCategoryId(1L);
		trailerUpdateDTO.setUserId(1L);
		Trailer existingTrailer = new Trailer();
		existingTrailer.setCreatedAt(new Date());
		Category category = new Category();
		AppUser appUser = new AppUser();
		Trailer updatedTrailer = new Trailer();

		when(trailerRepository.findById(trailerUpdateDTO.getId())).thenReturn(Optional.of(existingTrailer));
		when(categoryRepository.findById(trailerUpdateDTO.getCategoryId())).thenReturn(Optional.of(category));
		when(appUserRepository.findById(trailerUpdateDTO.getUserId())).thenReturn(Optional.of(appUser));
		when(trailerMapper.trailerUpdateDTOtoTrailer(trailerUpdateDTO)).thenReturn(updatedTrailer);

		trailerService.updateTrailer(trailerUpdateDTO);

		verify(trailerRepository, times(1)).save(updatedTrailer);
		assertEquals(existingTrailer.getCreatedAt(), updatedTrailer.getCreatedAt());
		assertEquals(appUser, updatedTrailer.getUser());
		assertEquals(category, updatedTrailer.getCategory());
	}

	@Test
	void whenUpdateTrailer_withNullId_thenThrowException() {
		TrailerUpdateDTO trailerUpdateDTO = new TrailerUpdateDTO();
		assertThrows(ApiRequestException.class, () -> trailerService.updateTrailer(trailerUpdateDTO));
	}

	@Test
	void whenUpdateTrailer_withNonExistingTrailer_thenThrowNotFoundException() {
		TrailerUpdateDTO trailerUpdateDTO = new TrailerUpdateDTO();
		trailerUpdateDTO.setId(1L);

		when(trailerRepository.findById(trailerUpdateDTO.getId())).thenReturn(Optional.empty());

		assertThrows(ApiNotFoundException.class, () -> trailerService.updateTrailer(trailerUpdateDTO));
	}

	@Test
	void whenDeleteTrailer_thenTrailerShouldBeDeleted() {
		Long trailerId = 1L;

		when(trailerRepository.existsById(trailerId)).thenReturn(true);

		trailerService.deleteTrailer(trailerId);

		verify(trailerRepository, times(1)).deleteById(trailerId);
	}

	@Test
	void whenDeleteTrailer_withNonExistingTrailer_thenThrowNotFoundException() {
		Long trailerId = 1L;

		when(trailerRepository.existsById(trailerId)).thenReturn(false);

		assertThrows(ApiNotFoundException.class, () -> trailerService.deleteTrailer(trailerId));
	}

	@Test
	void whenGetTrailerById_thenTrailerShouldBeReturned() {
		Long trailerId = 1L;
		Trailer trailer = new Trailer();
		TrailerResponseDTO trailerResponseDTO = new TrailerResponseDTO();

		when(trailerRepository.findById(trailerId)).thenReturn(Optional.of(trailer));
		when(trailerMapper.trailerToTrailerResponseDTO(trailer)).thenReturn(trailerResponseDTO);

		TrailerResponseDTO result = trailerService.getTrailerbyId(trailerId);

		assertNotNull(result);
		verify(trailerRepository, times(1)).findById(trailerId);
	}

	@Test
	void whenGetTrailerById_withNonExistingTrailer_thenThrowNotFoundException() {
		Long trailerId = 1L;

		when(trailerRepository.findById(trailerId)).thenReturn(Optional.empty());

		assertThrows(ApiNotFoundException.class, () -> trailerService.getTrailerbyId(trailerId));
	}

	@Test
	void whenGetAllTrailers_thenReturnTrailerList() {
		Trailer trailer = new Trailer();
		TrailerResponseDTO trailerResponseDTO = new TrailerResponseDTO();

		when(trailerRepository.findAll()).thenReturn(Collections.singletonList(trailer));
		when(trailerMapper.trailerToTrailerResponseDTO(trailer)).thenReturn(trailerResponseDTO);

		List<TrailerResponseDTO> result = trailerService.getAllTrailers();

		assertFalse(result.isEmpty());
		verify(trailerRepository, times(1)).findAll();
	}
}
