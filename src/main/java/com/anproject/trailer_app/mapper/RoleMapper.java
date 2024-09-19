package com.anproject.trailer_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.anproject.trailer_app.dto.request.RoleRequestDTO;
import com.anproject.trailer_app.dto.request.RoleUpdateDTO;
import com.anproject.trailer_app.dto.response.RoleResponseDTO;
import com.anproject.trailer_app.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

	Role roleRequestDTOtoRole(RoleRequestDTO roleRequestDto);
	Role roleUpdateDTOtoRole(RoleUpdateDTO roleUpdateDto);

	RoleResponseDTO roleToRoleResponseDTO(Role role);

}