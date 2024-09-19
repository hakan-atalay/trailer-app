package com.anproject.trailer_app.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anproject.trailer_app.dto.request.TrailerCommentRequestDTO;
import com.anproject.trailer_app.dto.request.TrailerCommentUpdateDTO;
import com.anproject.trailer_app.dto.response.TrailerCommentResponseDTO;
import com.anproject.trailer_app.entity.AppUser;
import com.anproject.trailer_app.entity.Trailer;
import com.anproject.trailer_app.entity.TrailerComment;
import com.anproject.trailer_app.mapper.TrailerCommentMapper;
import com.anproject.trailer_app.repository.AppUserRepository;
import com.anproject.trailer_app.repository.TrailerCommentRepository;
import com.anproject.trailer_app.repository.TrailerRepository;

@Service
public class TrailerCommentService {

	private final TrailerCommentRepository trailerCommentRepository;
	private final AppUserRepository appUserRepository;
	private final TrailerRepository trailerRepository;
	private final TrailerCommentMapper trailerCommentMapper;
	
	@Autowired
	public TrailerCommentService(TrailerCommentRepository trailerCommentRepository, AppUserRepository appUserRepository,
			TrailerRepository trailerRepository, TrailerCommentMapper trailerCommentMapper) {
		this.trailerCommentRepository = trailerCommentRepository;
		this.appUserRepository = appUserRepository;
		this.trailerRepository = trailerRepository;
		this.trailerCommentMapper = trailerCommentMapper;
	}

	public void saveTrailerComment(TrailerCommentRequestDTO trailerCommentRequestDto) {
		TrailerComment trailerComment = trailerCommentMapper.trailerCommentRequestDTOtoTrailer(trailerCommentRequestDto);
		trailerComment.setCreatedAt(new Date());
		trailerCommentRepository.save(trailerComment);
	}
	
	public void updateTrailerComment(TrailerCommentUpdateDTO trailercommentUpdateDto) {
		TrailerComment existingTrailerComment  = trailerCommentRepository.findById(trailercommentUpdateDto.getId()).get();
		
		if (existingTrailerComment != null) {
			AppUser appUser = appUserRepository.findById(trailercommentUpdateDto.getUserId()).get();
			Trailer trailer = trailerRepository.findById(trailercommentUpdateDto.getTrailerId()).get();
			TrailerComment trailerComment = trailerCommentMapper.trailerCommentUpdateDTOtoTrailer(trailercommentUpdateDto);
			trailerComment.setCreatedAt(trailerComment.getCreatedAt());
			trailerComment.setUser(appUser);
			trailerComment.setTrailer(trailer);
			trailerCommentRepository.save(trailerComment);
		}
		
	}
	
	public void deleteTrailerComment(Long id) {
		trailerCommentRepository.deleteById(id);
	}
	
	public TrailerCommentResponseDTO getTrailerCommentbyId(Long id) {
		TrailerComment trailerComment = trailerCommentRepository.findById(id).orElseThrow();
		return trailerCommentMapper.trailerCommentToTrailerCommentResponseDTO(trailerComment);
	}
	
	public List<TrailerCommentResponseDTO> getAllTrailerComments(){
		List<TrailerComment> trailerCommentList = trailerCommentRepository.findAll();
		List<TrailerCommentResponseDTO> trailerCommentResponseDtoList = trailerCommentList.stream()
				.map(trailerComment -> trailerCommentMapper.trailerCommentToTrailerCommentResponseDTO(trailerComment))
				.collect(Collectors.toList());
		return trailerCommentResponseDtoList;
	}
	
}
