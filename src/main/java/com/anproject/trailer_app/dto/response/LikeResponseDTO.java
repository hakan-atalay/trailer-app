package com.anproject.trailer_app.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeResponseDTO {
	private Long id;
	private Boolean like;
	private AppUserResponseDTO appUserResponseDTO;
	private TrailerResponseDTO trailerResponseDTO;
	
}
