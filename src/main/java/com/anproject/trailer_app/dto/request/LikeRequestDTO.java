package com.anproject.trailer_app.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeRequestDTO {
	
	private Boolean trailerLike;
	
	@NotNull
	private Long userId;
	
	@NotNull
	private Long trailerId;
	
}
