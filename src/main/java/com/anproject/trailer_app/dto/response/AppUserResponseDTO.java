package com.anproject.trailer_app.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserResponseDTO {
	private Long id;
	private String nickname;
	private String email;
	private String password;
	private RoleResponseDTO roleResponseDTO;
	
}
