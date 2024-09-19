package com.anproject.trailer_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrailerCommentUpdateDTO {
	
	@NotNull
	private Long id;
	
	@NotNull
	private Long userId;
	
	@NotNull
	private Long trailerId;
	
	@NotBlank(message = "Yorum boş olamaz.")
	private String trailerComment;
	
}
