package com.anproject.trailer_app.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeRequestDTO {
	private Boolean like;
	private Long userId;
	private Long trailerId;
	
}
