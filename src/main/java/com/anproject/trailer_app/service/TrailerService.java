package com.anproject.trailer_app.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.anproject.trailer_app.dto.request.TrailerRequestDTO;
import com.anproject.trailer_app.dto.request.TrailerUpdateDTO;
import com.anproject.trailer_app.dto.response.TrailerResponseDTO;
import com.anproject.trailer_app.entity.AppUser;
import com.anproject.trailer_app.entity.Category;
import com.anproject.trailer_app.entity.Trailer;
import com.anproject.trailer_app.entity.TrailerComment;
import com.anproject.trailer_app.mapper.TrailerMapper;
import com.anproject.trailer_app.repository.AppUserRepository;
import com.anproject.trailer_app.repository.CategoryRepository;
import com.anproject.trailer_app.repository.TrailerRepository;

public class TrailerService {
	
	private final TrailerRepository trailerRepository;
	private final CategoryRepository categoryRepository;
	private final AppUserRepository appUserRepository;
	private final TrailerMapper trailerMapper;
	
	@Autowired
	public TrailerService(TrailerRepository trailerRepository, CategoryRepository categoryRepository,
			AppUserRepository appUserRepository, TrailerMapper trailerMapper) {
		this.trailerRepository = trailerRepository;
		this.categoryRepository = categoryRepository;
		this.appUserRepository = appUserRepository;
		this.trailerMapper = trailerMapper;
	}

	public void saveTrailer(TrailerRequestDTO trailerRequestDto) {
		Trailer trailer = trailerMapper.trailerRequestDTOtoTrailer(trailerRequestDto);
		trailer.setCreatedAt(new Date());
		trailerRepository.save(trailer);
	}
	
	public void updateTrailer(TrailerUpdateDTO trailerUpdateDto) {	
		Trailer existingTrailer  = trailerRepository.findById(trailerUpdateDto.getId()).get();
		
		if (existingTrailer != null) {
			Category category = categoryRepository.findById(trailerUpdateDto.getCategoryId()).get();
			AppUser appUser = appUserRepository.findById(trailerUpdateDto.getUserId()).get();
			Trailer trailer = trailerMapper.trailerUpdateDTOtoTrailer(trailerUpdateDto);
			trailer.setUser(appUser);
			trailer.setCategory(category);
			trailerRepository.save(trailer);
		}
		
	}
	
	public void deleteTrailer(Long id) {
		trailerRepository.deleteById(id);
	}
	
	public TrailerResponseDTO getTrailerbyId(Long id) {
		Trailer trailer = trailerRepository.findById(id).orElseThrow();
		return trailerMapper.trailerToTrailerResponseDTO(trailer);
	}
	
	public List<TrailerResponseDTO> getAllTrailer(){
		List<Trailer> trailerList = trailerRepository.findAll();
		List<TrailerResponseDTO> trailerResponseDtoList = trailerList.stream()
				.map(trailer -> trailerMapper.trailerToTrailerResponseDTO(trailer))
				.collect(Collectors.toList());
		return trailerResponseDtoList;
	}
	
}
