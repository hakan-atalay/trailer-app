package com.anproject.trailer_app.controller;

import com.anproject.trailer_app.dto.request.RoleRequestDTO;
import com.anproject.trailer_app.dto.request.RoleUpdateDTO;
import com.anproject.trailer_app.dto.response.RoleResponseDTO;
import com.anproject.trailer_app.service.RoleService;
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

class RoleControllerTest {

	@Mock
	private RoleService roleService;

	@InjectMocks
	private RoleController roleController;

	public RoleControllerTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void saveRole() {
		RoleRequestDTO requestDto = new RoleRequestDTO();
		doNothing().when(roleService).saveRole(requestDto);
		ResponseEntity<Void> response = roleController.saveRole(requestDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(roleService, times(1)).saveRole(requestDto);
	}

	@Test
	void updateRole() {
		Long roleId = 1L;
		RoleUpdateDTO updateDto = new RoleUpdateDTO();
		updateDto.setId(roleId);
		doNothing().when(roleService).updateRole(updateDto);
		ResponseEntity<Void> response = roleController.updateRole(roleId, updateDto);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(roleService, times(1)).updateRole(updateDto);
	}

	@Test
	void deleteRole() {
		Long roleId = 1L;
		doNothing().when(roleService).deleteRole(roleId);
		ResponseEntity<Void> response = roleController.deleteRole(roleId);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(roleService, times(1)).deleteRole(roleId);
	}

	@Test
	void getRoleById() {
		Long roleId = 1L;
		RoleResponseDTO responseDto = new RoleResponseDTO();
		when(roleService.getRolebyId(roleId)).thenReturn(responseDto);
		ResponseEntity<RoleResponseDTO> response = roleController.getRoleById(roleId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(responseDto, response.getBody());
	}

	@Test
	void getAllRoles() {
		RoleResponseDTO responseDto = new RoleResponseDTO();
		when(roleService.getAllRoles()).thenReturn(Collections.singletonList(responseDto));
		ResponseEntity<List<RoleResponseDTO>> response = roleController.getAllRoles();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
	}
}
