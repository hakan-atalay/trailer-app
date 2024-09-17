package com.anproject.trailer_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.anproject.trailer_app.dto.request.AppUserRequestDTO;
import com.anproject.trailer_app.dto.request.AppUserUpdateDTO;
import com.anproject.trailer_app.dto.response.AppUserResponseDTO;
import com.anproject.trailer_app.entity.AppUser;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
	AppUserMapper INSTANCE = Mappers.getMapper(AppUserMapper.class);
	
	@Mapping(source = "roleId", target = "role.id")
	AppUser appUserRequestDTOtoAppUser(AppUserRequestDTO appUserRequestDto);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "roleId", target = "role.id")
	AppUser appUserUpdateDTOtoAppUser(AppUserUpdateDTO appUserUpdateDto);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "role", target = "roleResponseDTO")
	AppUserResponseDTO appUserToAppUserResponseDTO(AppUser appUser);
	
}
