package com.anproject.trailer_app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.anproject.trailer_app.dto.request.AppUserRequestDTO;
import com.anproject.trailer_app.dto.request.AppUserUpdateDTO;
import com.anproject.trailer_app.dto.response.AppUserResponseDTO;
import com.anproject.trailer_app.entity.AppUser;
import com.anproject.trailer_app.entity.Role;
import com.anproject.trailer_app.exception.ApiNotFoundException;
import com.anproject.trailer_app.exception.ApiRequestException;
import com.anproject.trailer_app.mapper.AppUserMapper;
import com.anproject.trailer_app.repository.AppUserRepository;
import com.anproject.trailer_app.repository.RoleRepository;

public class AppUserServiceTest {

	private AppUserService appUserService;
	private AppUserRepository appUserRepository;
	private RoleRepository roleRepository;
	private AppUserMapper appUserMapper;

	@BeforeEach
	void setUp() {
		appUserRepository = Mockito.mock(AppUserRepository.class);
		roleRepository = Mockito.mock(RoleRepository.class);
		appUserMapper = Mockito.mock(AppUserMapper.class);

		appUserService = new AppUserService(appUserRepository, roleRepository, appUserMapper);
	}

	@Test
	void whenSaveUser_thenUserShouldBeSaved() {
		AppUserRequestDTO appUserRequestDTO = new AppUserRequestDTO();
		AppUser appUser = new AppUser();

		when(appUserMapper.appUserRequestDTOtoAppUser(appUserRequestDTO)).thenReturn(appUser);

		appUserService.saveUser(appUserRequestDTO);

		verify(appUserRepository, times(1)).save(appUser);
	}

	@Test
	void whenSaveUserWithNullRequest_thenThrowException() {
		Exception exception = assertThrows(ApiRequestException.class, () -> {
			appUserService.saveUser(null);
		});

		String expectedMessage = "Geçersiz kullanıcı verisi sağlandı.";
		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	void whenUpdateUser_thenUserShouldBeUpdated() {
		AppUserUpdateDTO updateDTO = new AppUserUpdateDTO();
		updateDTO.setId(1L);
		updateDTO.setRoleId(2L);
		AppUser existingAppUser = new AppUser();
		Role role = new Role();

		when(appUserRepository.findById(1L)).thenReturn(Optional.of(existingAppUser));
		when(roleRepository.findById(2L)).thenReturn(Optional.of(role));
		when(appUserMapper.appUserUpdateDTOtoAppUser(updateDTO)).thenReturn(existingAppUser);

		appUserService.updateUser(updateDTO);

		verify(appUserRepository, times(1)).save(existingAppUser);
	}

	@Test
	void whenUpdateUserWithNullRequest_thenThrowException() {
		Exception exception = assertThrows(ApiRequestException.class, () -> {
			appUserService.updateUser(null);
		});

		String expectedMessage = "Güncellenmek istenen kullanıcı verisi eksik.";
		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	void whenDeleteUser_thenUserShouldBeDeleted() {
		when(appUserRepository.existsById(1L)).thenReturn(true);

		appUserService.deleteUser(1L);

		verify(appUserRepository, times(1)).deleteById(1L);
	}

	@Test
	void whenDeleteUserNotFound_thenThrowException() {
		when(appUserRepository.existsById(1L)).thenReturn(false);

		Exception exception = assertThrows(ApiNotFoundException.class, () -> {
			appUserService.deleteUser(1L);
		});

		String expectedMessage = "Silinmek istenen kullanıcı bulunamadı.";
		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	void whenGetUserById_thenReturnUser() {
		AppUser appUser = new AppUser();
		AppUserResponseDTO responseDTO = new AppUserResponseDTO();

		when(appUserRepository.findById(1L)).thenReturn(Optional.of(appUser));
		when(appUserMapper.appUserToAppUserResponseDTO(appUser)).thenReturn(responseDTO);

		AppUserResponseDTO result = appUserService.getUserbyId(1L);

		assertNotNull(result);
		assertEquals(responseDTO, result);
	}

	@Test
	void whenGetUserByIdNotFound_thenThrowException() {
		when(appUserRepository.findById(1L)).thenReturn(Optional.empty());

		Exception exception = assertThrows(ApiNotFoundException.class, () -> {
			appUserService.getUserbyId(1L);
		});

		String expectedMessage = "Kullanıcı bulunamadı.";
		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	void whenGetAllUsers_thenReturnUserList() {
		List<AppUser> users = List.of(new AppUser(), new AppUser());
		AppUserResponseDTO responseDTO1 = new AppUserResponseDTO();
		AppUserResponseDTO responseDTO2 = new AppUserResponseDTO();

		when(appUserRepository.findAll()).thenReturn(users);
		when(appUserMapper.appUserToAppUserResponseDTO(any(AppUser.class))).thenReturn(responseDTO1, responseDTO2);

		List<AppUserResponseDTO> result = appUserService.getAllUsers();

		assertEquals(2, result.size());
		verify(appUserMapper, times(2)).appUserToAppUserResponseDTO(any(AppUser.class));
	}

}
