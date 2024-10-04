package com.anproject.trailer_app.service;

import com.anproject.trailer_app.dto.request.RoleRequestDTO;
import com.anproject.trailer_app.dto.request.RoleUpdateDTO;
import com.anproject.trailer_app.dto.response.RoleResponseDTO;
import com.anproject.trailer_app.entity.Role;
import com.anproject.trailer_app.exception.ApiNotFoundException;
import com.anproject.trailer_app.exception.ApiRequestException;
import com.anproject.trailer_app.mapper.RoleMapper;
import com.anproject.trailer_app.repository.RoleRepository;
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

class RoleServiceTest {

	@Mock
	private RoleRepository roleRepository;

	@Mock
	private RoleMapper roleMapper;

	@InjectMocks
	private RoleService roleService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void whenSaveRole_thenRoleShouldBeSaved() {
		RoleRequestDTO roleRequestDTO = new RoleRequestDTO();
		Role role = new Role();

		when(roleMapper.roleRequestDTOtoRole(roleRequestDTO)).thenReturn(role);

		roleService.saveRole(roleRequestDTO);

		verify(roleRepository, times(1)).save(role);
	}

	@Test
	void whenSaveRole_withNullRequest_thenThrowException() {
		assertThrows(ApiRequestException.class, () -> roleService.saveRole(null));
	}

	@Test
	void whenUpdateRole_thenRoleShouldBeUpdated() {
		RoleUpdateDTO roleUpdateDTO = new RoleUpdateDTO();
		roleUpdateDTO.setId(1L);
		Role existingRole = new Role();
		Role updatedRole = new Role();

		when(roleRepository.findById(roleUpdateDTO.getId())).thenReturn(Optional.of(existingRole));
		when(roleMapper.roleUpdateDTOtoRole(roleUpdateDTO)).thenReturn(updatedRole);

		roleService.updateRole(roleUpdateDTO);

		verify(roleRepository, times(1)).save(updatedRole);
	}

	@Test
	void whenUpdateRole_withNullId_thenThrowException() {
		RoleUpdateDTO roleUpdateDTO = new RoleUpdateDTO();
		assertThrows(ApiRequestException.class, () -> roleService.updateRole(roleUpdateDTO));
	}

	@Test
	void whenUpdateRole_withNonExistingRole_thenThrowNotFoundException() {
		RoleUpdateDTO roleUpdateDTO = new RoleUpdateDTO();
		roleUpdateDTO.setId(1L);

		when(roleRepository.findById(roleUpdateDTO.getId())).thenReturn(Optional.empty());

		assertThrows(ApiNotFoundException.class, () -> roleService.updateRole(roleUpdateDTO));
	}

	@Test
	void whenDeleteRole_thenRoleShouldBeDeleted() {
		Long roleId = 1L;

		when(roleRepository.existsById(roleId)).thenReturn(true);

		roleService.deleteRole(roleId);

		verify(roleRepository, times(1)).deleteById(roleId);
	}

	@Test
	void whenDeleteRole_withNonExistingRole_thenThrowNotFoundException() {
		Long roleId = 1L;

		when(roleRepository.existsById(roleId)).thenReturn(false);

		assertThrows(ApiNotFoundException.class, () -> roleService.deleteRole(roleId));
	}

	@Test
	void whenGetRoleById_thenRoleShouldBeReturned() {
		Long roleId = 1L;
		Role role = new Role();
		RoleResponseDTO roleResponseDTO = new RoleResponseDTO();

		when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));
		when(roleMapper.roleToRoleResponseDTO(role)).thenReturn(roleResponseDTO);

		RoleResponseDTO result = roleService.getRolebyId(roleId);

		assertNotNull(result);
		verify(roleRepository, times(1)).findById(roleId);
	}

	@Test
	void whenGetRoleById_withNonExistingRole_thenThrowNotFoundException() {
		Long roleId = 1L;

		when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

		assertThrows(ApiNotFoundException.class, () -> roleService.getRolebyId(roleId));
	}

	@Test
	void whenGetAllRoles_thenReturnRoleList() {
		Role role = new Role();
		RoleResponseDTO roleResponseDTO = new RoleResponseDTO();

		when(roleRepository.findAll()).thenReturn(Collections.singletonList(role));
		when(roleMapper.roleToRoleResponseDTO(role)).thenReturn(roleResponseDTO);

		List<RoleResponseDTO> result = roleService.getAllRoles();

		assertFalse(result.isEmpty());
		verify(roleRepository, times(1)).findAll();
	}
}
