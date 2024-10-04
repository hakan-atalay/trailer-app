package com.anproject.trailer_app.controller;

import com.anproject.trailer_app.dto.request.CategoryRequestDTO;
import com.anproject.trailer_app.dto.request.CategoryUpdateDTO;
import com.anproject.trailer_app.dto.response.CategoryResponseDTO;
import com.anproject.trailer_app.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

	@Mock
	private CategoryService categoryService;

	@InjectMocks
	private CategoryController categoryController;

	public CategoryControllerTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void saveCategory() {
		CategoryRequestDTO requestDto = new CategoryRequestDTO();
		doNothing().when(categoryService).saveCategory(requestDto);
		ResponseEntity<Void> response = categoryController.saveCategory(requestDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		verify(categoryService, times(1)).saveCategory(requestDto);
	}

	@Test
	void updateCategory() {
		Long categoryId = 1L;
		CategoryUpdateDTO updateDto = new CategoryUpdateDTO();
		updateDto.setId(categoryId);
		doNothing().when(categoryService).updateCategory(updateDto);
		ResponseEntity<Void> response = categoryController.updateCategory(categoryId, updateDto);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(categoryService, times(1)).updateCategory(updateDto);
	}

	@Test
	void deleteCategory() {
		Long categoryId = 1L;
		doNothing().when(categoryService).deleteCategory(categoryId);
		ResponseEntity<Void> response = categoryController.deleteRole(categoryId);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(categoryService, times(1)).deleteCategory(categoryId);
	}

	@Test
	void getCategoryById() {
		Long categoryId = 1L;
		CategoryResponseDTO responseDto = new CategoryResponseDTO();
		when(categoryService.getCategorybyId(categoryId)).thenReturn(responseDto);
		ResponseEntity<CategoryResponseDTO> response = categoryController.getCategoryById(categoryId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(responseDto, response.getBody());
	}

	@Test
	void getAllCategories() {
		CategoryResponseDTO responseDto = new CategoryResponseDTO();
		when(categoryService.getAllCategories()).thenReturn(Collections.singletonList(responseDto));
		ResponseEntity<List<CategoryResponseDTO>> response = categoryController.getAllCategories();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
	}
}
