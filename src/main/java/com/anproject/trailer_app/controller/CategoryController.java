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

import com.anproject.trailer_app.dto.request.CategoryRequestDTO;
import com.anproject.trailer_app.dto.request.CategoryUpdateDTO;
import com.anproject.trailer_app.dto.response.CategoryResponseDTO;
import com.anproject.trailer_app.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping("/save")
	public ResponseEntity<Void> saveCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
		categoryService.saveCategory(categoryRequestDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Void> updateCategory(@PathVariable Long id,
			@Valid @RequestBody CategoryUpdateDTO categoryUpdateDto) {
		categoryUpdateDto.setId(id);
		categoryService.updateCategory(categoryUpdateDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/get-by-id/{id}")
	public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
		CategoryResponseDTO categoryResponseDto = categoryService.getCategorybyId(id);
		return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
		List<CategoryResponseDTO> categoryResponseDtoList = categoryService.getAllCategories();
		return new ResponseEntity<>(categoryResponseDtoList, HttpStatus.OK);
	}

}
