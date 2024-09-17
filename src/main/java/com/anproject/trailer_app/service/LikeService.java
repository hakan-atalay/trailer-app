package com.anproject.trailer_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anproject.trailer_app.dto.request.LikeRequestDTO;
import com.anproject.trailer_app.dto.request.LikeUpdateDTO;
import com.anproject.trailer_app.dto.response.LikeResponseDTO;
import com.anproject.trailer_app.entity.AppUser;
import com.anproject.trailer_app.entity.Like;
import com.anproject.trailer_app.entity.Trailer;
import com.anproject.trailer_app.mapper.LikeMapper;
import com.anproject.trailer_app.repository.AppUserRepository;
import com.anproject.trailer_app.repository.LikeRepository;
import com.anproject.trailer_app.repository.TrailerRepository;

@Service
public class LikeService {

	private final LikeRepository likeRepository;
	private final AppUserRepository appUserRepository;
	private final TrailerRepository trailerRepository;
	private final LikeMapper likeMapper;

	@Autowired
	public LikeService(LikeRepository likeRepository, AppUserRepository appUserRepository,
			TrailerRepository trailerRepository, LikeMapper likeMapper) {
		this.likeRepository = likeRepository;
		this.appUserRepository = appUserRepository;
		this.trailerRepository = trailerRepository;
		this.likeMapper = likeMapper;
	}

	public void saveLike(LikeRequestDTO likeRequestDto) {
		Like like = likeMapper.likeRequestDTOtoLike(likeRequestDto);
		likeRepository.save(like);
	}
	
	public void updateLike(LikeUpdateDTO likeUpdateDto) {		
		Like existingLike  = likeRepository.findById(likeUpdateDto.getId()).get();
		
		if (existingLike != null) {
			AppUser appUser = appUserRepository.findById(likeUpdateDto.getUserId()).get();
			Trailer trailer = trailerRepository.findById(likeUpdateDto.getTrailerId()).get();
			Like like = likeMapper.likeUpdateDTOtoLike(likeUpdateDto);
			like.setUser(appUser);
			like.setTrailer(trailer);
			likeRepository.save(like);
		}
		
	}
	
	public void deleteLike(Long id) {
		likeRepository.deleteById(id);
	}
	
	public LikeResponseDTO getLikebyId(Long id) {
		Like like = likeRepository.findById(id).orElseThrow();
		return likeMapper.likeToLikeReponseDTO(like);
	}
	
	public List<LikeResponseDTO> getAllLikes(){
		List<Like> likeList = likeRepository.findAll();
		List<LikeResponseDTO> likeResponseDtoList = likeList.stream()
				.map(like -> likeMapper.likeToLikeReponseDTO(like))
				.collect(Collectors.toList());
		return likeResponseDtoList;
	}
	
}
