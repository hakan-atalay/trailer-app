package com.anproject.trailer_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.anproject.trailer_app.dto.request.CategoryRequestDTO;
import com.anproject.trailer_app.dto.request.CategoryUpdateDTO;
import com.anproject.trailer_app.dto.response.CategoryResponseDTO;
import com.anproject.trailer_app.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	Category categoryRequestDTOtoCategory(CategoryRequestDTO categoryRequestDto);
	Category categoryUpdateDTOtoCategory(CategoryUpdateDTO categoryUpdateDto);

	CategoryResponseDTO categoryToCategoryResponseDTO(Category category);

}
