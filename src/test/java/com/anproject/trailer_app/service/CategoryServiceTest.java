package com.anproject.trailer_app.service;

import com.anproject.trailer_app.dto.request.CategoryRequestDTO;
import com.anproject.trailer_app.dto.request.CategoryUpdateDTO;
import com.anproject.trailer_app.dto.response.CategoryResponseDTO;
import com.anproject.trailer_app.entity.Category;
import com.anproject.trailer_app.exception.ApiNotFoundException;
import com.anproject.trailer_app.exception.ApiRequestException;
import com.anproject.trailer_app.mapper.CategoryMapper;
import com.anproject.trailer_app.repository.CategoryRepository;
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

class CategoryServiceTest {

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private CategoryMapper categoryMapper;

	@InjectMocks
	private CategoryService categoryService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void whenSaveCategory_thenCategoryShouldBeSaved() {
		CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO();
		Category category = new Category();

		when(categoryMapper.categoryRequestDTOtoCategory(categoryRequestDTO)).thenReturn(category);

		categoryService.saveCategory(categoryRequestDTO);

		verify(categoryRepository, times(1)).save(category);
	}

	@Test
	void whenSaveCategory_withNullRequest_thenThrowException() {
		assertThrows(ApiRequestException.class, () -> categoryService.saveCategory(null));
	}

	@Test
	void whenUpdateCategory_thenCategoryShouldBeUpdated() {
		CategoryUpdateDTO categoryUpdateDTO = new CategoryUpdateDTO();
		categoryUpdateDTO.setId(1L);
		Category existingCategory = new Category();
		Category updatedCategory = new Category();

		when(categoryRepository.findById(categoryUpdateDTO.getId())).thenReturn(Optional.of(existingCategory));
		when(categoryMapper.categoryUpdateDTOtoCategory(categoryUpdateDTO)).thenReturn(updatedCategory);

		categoryService.updateCategory(categoryUpdateDTO);

		verify(categoryRepository, times(1)).save(updatedCategory);
	}

	@Test
	void whenUpdateCategory_withNullId_thenThrowException() {
		CategoryUpdateDTO categoryUpdateDTO = new CategoryUpdateDTO();
		assertThrows(ApiRequestException.class, () -> categoryService.updateCategory(categoryUpdateDTO));
	}

	@Test
	void whenUpdateCategory_withNonExistingCategory_thenThrowNotFoundException() {
		CategoryUpdateDTO categoryUpdateDTO = new CategoryUpdateDTO();
		categoryUpdateDTO.setId(1L);

		when(categoryRepository.findById(categoryUpdateDTO.getId())).thenReturn(Optional.empty());

		assertThrows(ApiNotFoundException.class, () -> categoryService.updateCategory(categoryUpdateDTO));
	}

	@Test
	void whenDeleteCategory_thenCategoryShouldBeDeleted() {
		Long categoryId = 1L;

		when(categoryRepository.existsById(categoryId)).thenReturn(true);

		categoryService.deleteCategory(categoryId);

		verify(categoryRepository, times(1)).deleteById(categoryId);
	}

	@Test
	void whenDeleteCategory_withNonExistingCategory_thenThrowNotFoundException() {
		Long categoryId = 1L;

		when(categoryRepository.existsById(categoryId)).thenReturn(false);

		assertThrows(ApiNotFoundException.class, () -> categoryService.deleteCategory(categoryId));
	}

	@Test
	void whenGetCategoryById_thenCategoryShouldBeReturned() {
		Long categoryId = 1L;
		Category category = new Category();
		CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();

		when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
		when(categoryMapper.categoryToCategoryResponseDTO(category)).thenReturn(categoryResponseDTO);

		CategoryResponseDTO result = categoryService.getCategorybyId(categoryId);

		assertNotNull(result);
		verify(categoryRepository, times(1)).findById(categoryId);
	}

	@Test
	void whenGetCategoryById_withNonExistingCategory_thenThrowNotFoundException() {
		Long categoryId = 1L;

		when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

		assertThrows(ApiNotFoundException.class, () -> categoryService.getCategorybyId(categoryId));
	}

	@Test
	void whenGetAllCategories_thenReturnCategoryList() {
		Category category = new Category();
		CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();

		when(categoryRepository.findAll()).thenReturn(Collections.singletonList(category));
		when(categoryMapper.categoryToCategoryResponseDTO(category)).thenReturn(categoryResponseDTO);

		List<CategoryResponseDTO> result = categoryService.getAllCategories();

		assertFalse(result.isEmpty());
		verify(categoryRepository, times(1)).findAll();
	}
}
