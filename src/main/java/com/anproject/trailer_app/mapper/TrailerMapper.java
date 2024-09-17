package com.anproject.trailer_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.anproject.trailer_app.dto.request.TrailerRequestDTO;
import com.anproject.trailer_app.dto.request.TrailerUpdateDTO;
import com.anproject.trailer_app.dto.response.TrailerResponseDTO;
import com.anproject.trailer_app.entity.Trailer;

@Mapper
public interface TrailerMapper {
	TrailerMapper INSTANCE = Mappers.getMapper(TrailerMapper.class);
	
	Trailer trailerRequestDTOtoTrailer(TrailerRequestDTO trailerRequestDto);
	Trailer trailerUpdateDTOtoTrailer(TrailerUpdateDTO trailerUpdateDto);
	
	TrailerResponseDTO trailerToTrailerResponseDTO(Trailer trailer);
	
}
