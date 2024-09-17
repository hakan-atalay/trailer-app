package com.anproject.trailer_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.anproject.trailer_app.dto.request.LikeRequestDTO;
import com.anproject.trailer_app.dto.request.LikeUpdateDTO;
import com.anproject.trailer_app.dto.response.LikeResponseDTO;
import com.anproject.trailer_app.entity.Like;

@Mapper(componentModel = "spring" ,uses = {AppUserMapper.class, TrailerMapper.class})
public interface LikeMapper {
	LikeMapper INSTANCE = Mappers.getMapper(LikeMapper.class);
	
	@Mapping(source = "userId", target = "user.id")
	@Mapping(source = "trailerId", target = "trailer.id")
	Like likeRequestDTOtoLike(LikeRequestDTO likeRequestDto);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "userId", target = "user.id")
	@Mapping(source = "trailerId", target = "trailer.id")
	Like likeUpdateDTOtoLike(LikeUpdateDTO likeUpdateDto);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "user", target = "appUserResponseDTO")
	@Mapping(source = "trailer", target = "trailerResponseDTO")
	LikeResponseDTO likeToLikeReponseDTO(Like like);
	
}
