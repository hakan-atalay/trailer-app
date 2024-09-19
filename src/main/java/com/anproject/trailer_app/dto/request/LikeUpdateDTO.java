package com.anproject.trailer_app.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeUpdateDTO {
	
	@NotNull
	private Long id;
	
	private Boolean trailerLike;
	
	@NotNull
	private Long userId;
	
	@NotNull
	private Long trailerId;
	
}
