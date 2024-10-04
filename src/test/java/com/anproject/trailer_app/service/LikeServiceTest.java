package com.anproject.trailer_app.service;

import com.anproject.trailer_app.dto.request.LikeRequestDTO;
import com.anproject.trailer_app.dto.request.LikeUpdateDTO;
import com.anproject.trailer_app.dto.response.LikeResponseDTO;
import com.anproject.trailer_app.entity.AppUser;
import com.anproject.trailer_app.entity.Like;
import com.anproject.trailer_app.entity.Trailer;
import com.anproject.trailer_app.exception.ApiNotFoundException;
import com.anproject.trailer_app.exception.ApiRequestException;
import com.anproject.trailer_app.mapper.LikeMapper;
import com.anproject.trailer_app.repository.AppUserRepository;
import com.anproject.trailer_app.repository.LikeRepository;
import com.anproject.trailer_app.repository.TrailerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LikeServiceTest {

	@Mock
	private LikeRepository likeRepository;

	@Mock
	private AppUserRepository appUserRepository;

	@Mock
	private TrailerRepository trailerRepository;

	@Mock
	private LikeMapper likeMapper;

	@InjectMocks
	private LikeService likeService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void whenSaveLike_thenLikeShouldBeSaved() {
		LikeRequestDTO likeRequestDTO = new LikeRequestDTO();
		likeRequestDTO.setUserId(1L);
		likeRequestDTO.setTrailerId(1L);
		Like like = new Like();

		when(likeMapper.likeRequestDTOtoLike(likeRequestDTO)).thenReturn(like);

		likeService.saveLike(likeRequestDTO);

		verify(likeRepository, times(1)).save(like);
	}

	@Test
	void whenSaveLike_withNullRequest_thenThrowException() {
		assertThrows(ApiRequestException.class, () -> likeService.saveLike(null));
	}

	@Test
	void whenSaveLike_withMissingUserIdOrTrailerId_thenThrowException() {
		LikeRequestDTO likeRequestDTO = new LikeRequestDTO();
		assertThrows(ApiRequestException.class, () -> likeService.saveLike(likeRequestDTO));
	}

	@Test
	void whenUpdateLike_thenLikeShouldBeUpdated() {
		LikeUpdateDTO likeUpdateDTO = new LikeUpdateDTO();
		likeUpdateDTO.setId(1L);
		likeUpdateDTO.setUserId(1L);
		likeUpdateDTO.setTrailerId(1L);
		Like existingLike = new Like();
		AppUser appUser = new AppUser();
		Trailer trailer = new Trailer();
		Like updatedLike = new Like();

		when(likeRepository.findById(likeUpdateDTO.getId())).thenReturn(Optional.of(existingLike));
		when(appUserRepository.findById(likeUpdateDTO.getUserId())).thenReturn(Optional.of(appUser));
		when(trailerRepository.findById(likeUpdateDTO.getTrailerId())).thenReturn(Optional.of(trailer));
		when(likeMapper.likeUpdateDTOtoLike(likeUpdateDTO)).thenReturn(updatedLike);

		likeService.updateLike(likeUpdateDTO);

		verify(likeRepository, times(1)).save(updatedLike);
		assertEquals(appUser, updatedLike.getUser());
		assertEquals(trailer, updatedLike.getTrailer());
	}

	@Test
	void whenUpdateLike_withNullId_thenThrowException() {
		LikeUpdateDTO likeUpdateDTO = new LikeUpdateDTO();
		assertThrows(ApiRequestException.class, () -> likeService.updateLike(likeUpdateDTO));
	}

	@Test
	void whenUpdateLike_withNonExistingLike_thenThrowNotFoundException() {
		LikeUpdateDTO likeUpdateDTO = new LikeUpdateDTO();
		likeUpdateDTO.setId(1L);

		when(likeRepository.findById(likeUpdateDTO.getId())).thenReturn(Optional.empty());

		assertThrows(ApiNotFoundException.class, () -> likeService.updateLike(likeUpdateDTO));
	}

	@Test
	void whenDeleteLike_thenLikeShouldBeDeleted() {
		Long likeId = 1L;

		when(likeRepository.existsById(likeId)).thenReturn(true);

		likeService.deleteLike(likeId);

		verify(likeRepository, times(1)).deleteById(likeId);
	}

	@Test
	void whenDeleteLike_withNonExistingLike_thenThrowNotFoundException() {
		Long likeId = 1L;

		when(likeRepository.existsById(likeId)).thenReturn(false);

		assertThrows(ApiNotFoundException.class, () -> likeService.deleteLike(likeId));
	}

	@Test
	void whenGetLikeById_thenLikeShouldBeReturned() {
		Long likeId = 1L;
		Like like = new Like();
		LikeResponseDTO likeResponseDTO = new LikeResponseDTO();

		when(likeRepository.findById(likeId)).thenReturn(Optional.of(like));
		when(likeMapper.likeToLikeReponseDTO(like)).thenReturn(likeResponseDTO);

		LikeResponseDTO result = likeService.getLikebyId(likeId);

		assertNotNull(result);
		verify(likeRepository, times(1)).findById(likeId);
	}

	@Test
	void whenGetLikeById_withNonExistingLike_thenThrowNotFoundException() {
		Long likeId = 1L;

		when(likeRepository.findById(likeId)).thenReturn(Optional.empty());

		assertThrows(ApiNotFoundException.class, () -> likeService.getLikebyId(likeId));
	}

	@Test
	void whenGetAllLikes_thenReturnLikeList() {
		Like like = new Like();
		LikeResponseDTO likeResponseDTO = new LikeResponseDTO();

		when(likeRepository.findAll()).thenReturn(Collections.singletonList(like));
		when(likeMapper.likeToLikeReponseDTO(like)).thenReturn(likeResponseDTO);

		List<LikeResponseDTO> result = likeService.getAllLikes();

		assertFalse(result.isEmpty());
		verify(likeRepository, times(1)).findAll();
	}

}
