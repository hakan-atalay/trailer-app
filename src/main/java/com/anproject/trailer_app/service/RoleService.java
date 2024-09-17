package com.anproject.trailer_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anproject.trailer_app.dto.request.RoleRequestDTO;
import com.anproject.trailer_app.dto.request.RoleUpdateDTO;
import com.anproject.trailer_app.dto.response.RoleResponseDTO;
import com.anproject.trailer_app.entity.Role;
import com.anproject.trailer_app.mapper.RoleMapper;
import com.anproject.trailer_app.repository.RoleRepository;

@Service
public class RoleService {
	
	private final RoleRepository roleRepository;
	private final RoleMapper roleMapper;

	@Autowired
	public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
		this.roleRepository = roleRepository;
		this.roleMapper = roleMapper;
	}
	
	public void saveRole(RoleRequestDTO roleRequestDto) {
		Role role = roleMapper.roleRequestDTOtoRole(roleRequestDto);
		roleRepository.save(role);
	}
	
	public void updateRole(RoleUpdateDTO roleUpdateDto) {
		Role existingRole  = roleRepository.findById(roleUpdateDto.getId()).get();
		
		if (existingRole != null) {
			Role role = roleMapper.roleUpdateDTOtoRole(roleUpdateDto);
			roleRepository.save(role);
		}
		
	}
	
	public void deleteRole(Long id) {
		roleRepository.deleteById(id);
	}
	
	public RoleResponseDTO getRolebyId(Long id) {
		Role role = roleRepository.findById(id).orElseThrow();
		return roleMapper.roleToRoleResponseDTO(role);
	}
	
	public List<RoleResponseDTO> getAllRoles(){
		List<Role> roleList = roleRepository.findAll();
		List<RoleResponseDTO> roleResponseDtoList = roleList.stream()
				.map(role -> roleMapper.roleToRoleResponseDTO(role))
				.collect(Collectors.toList());
		return roleResponseDtoList;
	}
	
}
