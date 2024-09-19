package com.anproject.trailer_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anproject.trailer_app.dto.request.AppUserRequestDTO;
import com.anproject.trailer_app.dto.request.AppUserUpdateDTO;
import com.anproject.trailer_app.dto.response.AppUserResponseDTO;
import com.anproject.trailer_app.service.AppUserService;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

	private final AppUserService appUserService;

	@Autowired
	public AppUserController(AppUserService appUserService) {
		this.appUserService = appUserService;
	}

	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody AppUserRequestDTO appUserRequestDto) {
		appUserService.saveUser(appUserRequestDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody AppUserUpdateDTO appUserUpdateDto) {
		appUserUpdateDto.setId(id);
		appUserService.updateUser(appUserUpdateDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		appUserService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/get-by-id/{id}")
	public ResponseEntity<AppUserResponseDTO> getUserById(@PathVariable Long id) {
		AppUserResponseDTO appUserResponseDto = appUserService.getUserbyId(id);
		return new ResponseEntity<>(appUserResponseDto, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<AppUserResponseDTO>> getAllUsers() {
		List<AppUserResponseDTO> appUserResponseDtoList = appUserService.getAllUsers();
		return new ResponseEntity<>(appUserResponseDtoList, HttpStatus.OK);
	}
	
}
