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

import com.anproject.trailer_app.dto.request.RoleRequestDTO;
import com.anproject.trailer_app.dto.request.RoleUpdateDTO;
import com.anproject.trailer_app.dto.response.RoleResponseDTO;
import com.anproject.trailer_app.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	private final RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@PostMapping("/save")
	public ResponseEntity<Void> saveRole(@RequestBody RoleRequestDTO roleRequestDto) {
		roleService.saveRole(roleRequestDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Void> updateRole(@PathVariable Long id, @RequestBody RoleUpdateDTO roleUpdateDto) {
		roleUpdateDto.setId(id);
		roleService.updateRole(roleUpdateDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
		roleService.deleteRole(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/get-by-id/{id}")
	public ResponseEntity<RoleResponseDTO> getRoleById(@PathVariable Long id) {
		RoleResponseDTO roleResponseDto = roleService.getRolebyId(id);
		return new ResponseEntity<>(roleResponseDto, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {
		List<RoleResponseDTO> roleResponseDtoList = roleService.getAllRoles();
		return new ResponseEntity<>(roleResponseDtoList, HttpStatus.OK);
	}

}
