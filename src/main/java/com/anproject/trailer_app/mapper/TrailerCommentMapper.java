package com.anproject.trailer_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.anproject.trailer_app.dto.request.TrailerCommentRequestDTO;
import com.anproject.trailer_app.dto.request.TrailerCommentUpdateDTO;
import com.anproject.trailer_app.dto.response.TrailerCommentResponseDTO;
import com.anproject.trailer_app.entity.TrailerComment;

@Mapper(componentModel = "spring", uses = {AppUserMapper.class, TrailerMapper.class})
public interface TrailerCommentMapper {
	TrailerCommentMapper INSTANCE = Mappers.getMapper(TrailerCommentMapper.class);
	
	@Mapping(source = "userId", target = "user.id")
	@Mapping(source = "trailerId", target = "trailer.id")
	TrailerComment trailerCommentRequestDTOtoTrailer(TrailerCommentRequestDTO trailerCommentRequestDto);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "userId", target = "user.id")
	@Mapping(source = "trailerId", target = "trailer.id")
	TrailerComment trailerCommentUpdateDTOtoTrailer(TrailerCommentUpdateDTO trailerCommentUpdateDto);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "user", target = "appUserResponseDTO")
	@Mapping(source = "trailer", target = "trailerResponseDTO")
	TrailerCommentResponseDTO trailerCommentToTrailerCommentResponseDTO(TrailerComment trailerComment);
	
}
