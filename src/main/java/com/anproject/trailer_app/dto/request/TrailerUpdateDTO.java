package com.anproject.trailer_app.dto.request;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrailerUpdateDTO {
	
	@NotNull
	private Long id;
	
	@NotBlank(message = "Başlık boş olamaz.")
	private String title;
	
	@URL(message = "Geçerli bir URL giriniz.")
	private String url;
	
	@NotNull
	private Long categoryId;
	
	@NotNull
	private Long userId;
	
}
