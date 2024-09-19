package com.anproject.trailer_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {

	@NotBlank(message = "Kategori boş olamaz.")
	private String categoryName;

}
