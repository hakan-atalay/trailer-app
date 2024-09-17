package com.anproject.trailer_app.mapper;

import org.mapstruct.factory.Mappers;

import com.anproject.trailer_app.dto.request.TrailerCommentRequestDTO;
import com.anproject.trailer_app.dto.request.TrailerCommentUpdateDTO;
import com.anproject.trailer_app.dto.response.TrailerCommentResponseDTO;
import com.anproject.trailer_app.entity.Trailer;
import com.anproject.trailer_app.entity.TrailerComment;

public interface TrailerCommentMapper {
	TrailerCommentMapper INSTANCE = Mappers.getMapper(TrailerCommentMapper.class);
	
	TrailerComment trailerCommentRequestDTOtoTrailer(TrailerCommentRequestDTO trailerCommentRequestDto);
	TrailerComment trailerCommentUpdateDTOtoTrailer(TrailerCommentUpdateDTO trailerCommentUpdateDto);
	
	TrailerCommentResponseDTO trailerCommentToTrailerCommentResponseDTO(Trailer trailer);
	
}
