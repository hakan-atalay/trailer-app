package com.anproject.trailer_app.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrailerCommentUpdateDTO {
	private Long id;
	private Long userId;
	private Long trailerId;
	private String trailerComment;
	
}
