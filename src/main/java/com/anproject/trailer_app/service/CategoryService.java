package com.anproject.trailer_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anproject.trailer_app.dto.request.CategoryRequestDTO;
import com.anproject.trailer_app.dto.request.CategoryUpdateDTO;
import com.anproject.trailer_app.dto.response.CategoryResponseDTO;
import com.anproject.trailer_app.entity.Category;
import com.anproject.trailer_app.exception.ApiNotFoundException;
import com.anproject.trailer_app.exception.ApiRequestException;
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
        if (categoryRequestDto == null) {
            throw new ApiRequestException("Geçersiz kategori verisi sağlandı.");
        }

        Category category = categoryMapper.categoryRequestDTOtoCategory(categoryRequestDto);
        categoryRepository.save(category);
    }
    
    public void updateCategory(CategoryUpdateDTO categoryUpdateDto) {
        if (categoryUpdateDto == null || categoryUpdateDto.getId() == null) {
            throw new ApiRequestException("Güncellenmek istenen kategori verisi eksik.");
        }

        Category existingCategory = categoryRepository.findById(categoryUpdateDto.getId())
                .orElseThrow(() -> new ApiNotFoundException("Güncellenmek istenen kategori bulunamadı."));

        Category category = categoryMapper.categoryUpdateDTOtoCategory(categoryUpdateDto);
        categoryRepository.save(category);
    }
    
    public void deleteCategory(Long id) {
        if (id == null || !categoryRepository.existsById(id)) {
            throw new ApiNotFoundException("Silinmek istenen kategori bulunamadı.");
        }

        categoryRepository.deleteById(id);
    }
    
    public CategoryResponseDTO getCategorybyId(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Kategori bulunamadı."));
        return categoryMapper.categoryToCategoryResponseDTO(category);
    }
    
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(category -> categoryMapper.categoryToCategoryResponseDTO(category))
                .collect(Collectors.toList());
    }
    
}
