package com.anproject.trailer_app.controller;

import com.anproject.trailer_app.dto.request.AppUserRequestDTO;
import com.anproject.trailer_app.dto.request.AppUserUpdateDTO;
import com.anproject.trailer_app.dto.response.AppUserResponseDTO;
import com.anproject.trailer_app.service.AppUserService;
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

class AppUserControllerTest {

	@Mock
	private AppUserService appUserService;

	@InjectMocks
	private AppUserController appUserController;

	public AppUserControllerTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void saveUser() {
		AppUserRequestDTO requestDto = new AppUserRequestDTO();
		doNothing().when(appUserService).saveUser(requestDto);
		ResponseEntity<String> response = appUserController.saveUser(requestDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(appUserService, times(1)).saveUser(requestDto);
	}

	@Test
	void updateUser() {
		Long userId = 1L;
		AppUserUpdateDTO updateDto = new AppUserUpdateDTO();
		updateDto.setId(userId);
		doNothing().when(appUserService).updateUser(updateDto);
		ResponseEntity<String> response = appUserController.updateUser(userId, updateDto);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(appUserService, times(1)).updateUser(updateDto);
	}

	@Test
	void deleteUser() {
		Long userId = 1L;
		doNothing().when(appUserService).deleteUser(userId);
		ResponseEntity<String> response = appUserController.deleteUser(userId);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(appUserService, times(1)).deleteUser(userId);
	}

	@Test
	void getUserById() {
		Long userId = 1L;
		AppUserResponseDTO responseDto = new AppUserResponseDTO();
		when(appUserService.getUserbyId(userId)).thenReturn(responseDto);
		ResponseEntity<AppUserResponseDTO> response = appUserController.getUserById(userId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(responseDto, response.getBody());
	}

	@Test
	void getAllUsers() {
		AppUserResponseDTO responseDto = new AppUserResponseDTO();
		when(appUserService.getAllUsers()).thenReturn(Collections.singletonList(responseDto));
		ResponseEntity<List<AppUserResponseDTO>> response = appUserController.getAllUsers();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
	}
}
