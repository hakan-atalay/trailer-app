package com.anproject.trailer_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.anproject.trailer_app.dto.request.AppUserRequestDTO;
import com.anproject.trailer_app.dto.request.AppUserUpdateDTO;
import com.anproject.trailer_app.dto.response.AppUserResponseDTO;
import com.anproject.trailer_app.entity.AppUser;

@Mapper
public interface AppUserMapper {
	AppUserMapper INSTANCE = Mappers.getMapper(AppUserMapper.class);
	
	AppUser appUserRequestDTOtoAppUser(AppUserRequestDTO appUserRequestDto);
	AppUser appUserUpdateDTOtoAppUser(AppUserUpdateDTO appUserUpdateDto);
	
	AppUserResponseDTO appUserToAppUserResponseDTO(AppUser appUser);
	
}
