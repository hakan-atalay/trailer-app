package com.anproject.trailer_app.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrailerUpdateDTO {
	
	private Long id;
	private String title;
	private String url;
	private Long categoryId;
	private Long userId;
	
}
