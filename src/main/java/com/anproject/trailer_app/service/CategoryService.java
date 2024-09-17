package com.anproject.trailer_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anproject.trailer_app.dto.request.CategoryRequestDTO;
import com.anproject.trailer_app.dto.request.CategoryUpdateDTO;
import com.anproject.trailer_app.dto.response.CategoryResponseDTO;
import com.anproject.trailer_app.entity.Category;
import com.anproject.trailer_app.mapper.CategoryMapper;
import com.anproject.trailer_app.repository.CategoryRepository;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;
	
	@Autowired
	public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
	}
	
	public void saveCategory(CategoryRequestDTO categoryRequestDto) {
		Category category = categoryMapper.categoryRequestDTOtoCategory(categoryRequestDto);
		categoryRepository.save(category);
	}
	
	public void updateCategory(CategoryUpdateDTO categoryUpdateDto) {
		Category existingCategory  = categoryRepository.findById(categoryUpdateDto.getId()).get();
		if (existingCategory != null) {
			Category category = categoryMapper.categoryUpdateDTOtoCategory(categoryUpdateDto);
			categoryRepository.save(category);
		}
	}
	
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}
	
	public CategoryResponseDTO getRolebyId(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow();
		return categoryMapper.categoryToCategoryResponseDTO(category);
	}
	
	public List<CategoryResponseDTO> getAllCategories(){
		List<Category> categoryList = categoryRepository.findAll();
		List<CategoryResponseDTO> CategoryResponseDtoList = categoryList.stream()
				.map(category -> categoryMapper.categoryToCategoryResponseDTO(category))
				.collect(Collectors.toList());
		return CategoryResponseDtoList;
	}
	
}
