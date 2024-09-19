package com.anproject.trailer_app.dto.request;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrailerRequestDTO {
	
	@NotNull
	private String title;
	
	@URL(message = "Geçerli bir URL giriniz.")
	private String url;
	
	@NotNull
	private Long categoryId;
	
	@NotNull
	private Long userId;

}
