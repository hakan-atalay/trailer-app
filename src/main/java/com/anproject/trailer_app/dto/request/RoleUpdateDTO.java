package com.anproject.trailer_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleUpdateDTO {
	
	@NotNull
	private Long id;
	
	@NotBlank(message = "Rol boş olamaz.")
	private String roleName;
	
}
