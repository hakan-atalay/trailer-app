package com.anproject.trailer_app.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrailerCommentResponseDTO {
	private Long id;
	private AppUserResponseDTO appUserResponseDTO;
	private TrailerResponseDTO trailerResponseDTO;
	private String TrailerComment;
	private Date createdAt;
	
}
