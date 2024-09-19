package com.anproject.trailer_app.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrailerResponseDTO {
	private Long id;
	private String title;
	private String url;
	private CategoryResponseDTO categoryResponseDTO;
	private AppUserResponseDTO appUserResponseDTO;
	private Date createdAt;
	
}
