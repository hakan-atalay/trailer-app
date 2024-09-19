package com.anproject.trailer_app.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserRequestDTO {
	private String nickname;
	private String email;
	private String password;
	private Long roleId;
	
}
