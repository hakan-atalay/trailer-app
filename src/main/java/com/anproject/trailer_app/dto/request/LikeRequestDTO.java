package com.anproject.trailer_app.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeRequestDTO {
	
	private Boolean trailerLike;
	private Long userId;
	private Long trailerId;
	
}
