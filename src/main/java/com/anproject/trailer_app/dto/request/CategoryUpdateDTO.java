package com.anproject.trailer_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryUpdateDTO {
	
	@NotNull
	private Long id;

	@NotBlank(message = "Kategori boş olamaz.")
	private String categoryName;

}
