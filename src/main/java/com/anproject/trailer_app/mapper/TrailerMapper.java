package com.anproject.trailer_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.anproject.trailer_app.dto.request.TrailerRequestDTO;
import com.anproject.trailer_app.dto.request.TrailerUpdateDTO;
import com.anproject.trailer_app.dto.response.TrailerResponseDTO;
import com.anproject.trailer_app.entity.Trailer;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, AppUserMapper.class})
public interface TrailerMapper {
	
	TrailerMapper INSTANCE = Mappers.getMapper(TrailerMapper.class);
	
	@Mapping(source = "categoryId", target = "category.id")
	@Mapping(source = "userId", target = "user.id")
	Trailer trailerRequestDTOtoTrailer(TrailerRequestDTO trailerRequestDto);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "categoryId", target = "category.id")
	@Mapping(source = "userId", target = "user.id")
	Trailer trailerUpdateDTOtoTrailer(TrailerUpdateDTO trailerUpdateDto);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "category", target = "categoryResponseDTO")
	@Mapping(source = "user", target = "appUserResponseDTO")
	TrailerResponseDTO trailerToTrailerResponseDTO(Trailer trailer);
	
}
