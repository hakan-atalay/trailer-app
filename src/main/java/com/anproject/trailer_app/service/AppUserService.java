package com.anproject.trailer_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anproject.trailer_app.dto.request.AppUserRequestDTO;
import com.anproject.trailer_app.dto.request.AppUserUpdateDTO;
import com.anproject.trailer_app.dto.response.AppUserResponseDTO;
import com.anproject.trailer_app.entity.AppUser;
import com.anproject.trailer_app.entity.Role;
import com.anproject.trailer_app.mapper.AppUserMapper;
import com.anproject.trailer_app.repository.AppUserRepository;
import com.anproject.trailer_app.repository.RoleRepository;

@Service
public class AppUserService {

	private final AppUserRepository appUserRepository;
	private final RoleRepository roleRepository;
	private final AppUserMapper appUserMapper;
	
	@Autowired
	public AppUserService(AppUserRepository appUserRepository, RoleRepository roleRepository,
			AppUserMapper appUserMapper) {
		this.appUserRepository = appUserRepository;
		this.roleRepository = roleRepository;
		this.appUserMapper = appUserMapper;
	}
		
	public void saveUser(AppUserRequestDTO appUserRequestDto) {
		AppUser appUser = appUserMapper.appUserRequestDTOtoAppUser(appUserRequestDto);
		appUserRepository.save(appUser);
	}
	
	public void updateUser(AppUserUpdateDTO appUserUpdateDto) {
		AppUser existingAppUser  = appUserRepository.findById(appUserUpdateDto.getId()).get();
		if (existingAppUser != null) {
			Role role = roleRepository.findById(appUserUpdateDto.getRoleId()).get();
			AppUser appUser = appUserMapper.appUserUpdateDTOtoAppUser(appUserUpdateDto);
			appUser.setRole(role);
			appUserRepository.save(appUser);
		}
	}
	
	public void deleteUser(Long id) {
		appUserRepository.deleteById(id);
	}
	
	public AppUserResponseDTO getUserbyId(Long id) {
		AppUser appUser = appUserRepository.findById(id).orElseThrow();
		return appUserMapper.appUserToAppUserResponseDTO(appUser);
	}
	
	public List<AppUserResponseDTO> getAllUsers(){
		List<AppUser> userList = appUserRepository.findAll();
		List<AppUserResponseDTO> appUserResponseDtoList = userList.stream()
				.map(user -> appUserMapper.appUserToAppUserResponseDTO(user))
				.collect(Collectors.toList());
		return appUserResponseDtoList;
	}
	
}
