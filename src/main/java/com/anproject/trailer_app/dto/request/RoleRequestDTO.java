package com.anproject.trailer_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestDTO {
	
	@NotBlank(message = "Rol boş olamaz.")
	private String roleName;

}
