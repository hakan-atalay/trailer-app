package com.anproject.trailer_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.anproject.trailer_app.dto.request.CategoryRequestDTO;
import com.anproject.trailer_app.dto.response.CategoryResponseDTO;
import com.anproject.trailer_app.entity.Category;

@Mapper
public interface CategoryMapper {
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
	Category categoryRequestDTOtoCategory(CategoryRequestDTO categoryRequestDto);
	Category categoryUpdateDTOtoCategory(CategoryRequestDTO categoryRequestDto);
	
	CategoryResponseDTO categoryToCategoryResponseDTO(Category category);
	
}
