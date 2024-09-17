package com.anproject.trailer_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.anproject.trailer_app.dto.request.LikeRequestDTO;
import com.anproject.trailer_app.dto.request.LikeUpdateDTO;
import com.anproject.trailer_app.dto.response.LikeResponseDTO;
import com.anproject.trailer_app.entity.Like;

@Mapper
public interface LikeMapper {
	LikeMapper INSTANCE = Mappers.getMapper(LikeMapper.class);
	
	Like likeRequestDTOtoLike(LikeRequestDTO likeRequestDto);
	Like likeUpdateDTOtoLike(LikeUpdateDTO likeUpdateDto);
	
	LikeResponseDTO likeToLikeReponseDTO(Like like);
	
}
