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
import com.anproject.trailer_app.exception.ApiNotFoundException;
import com.anproject.trailer_app.exception.ApiRequestException;
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
        if (likeRequestDto == null || likeRequestDto.getUserId() == null || likeRequestDto.getTrailerId() == null) {
            throw new ApiRequestException("Geçersiz beğeni verisi sağlandı.");
        }

        Like like = likeMapper.likeRequestDTOtoLike(likeRequestDto);
        likeRepository.save(like);
    }

    public void updateLike(LikeUpdateDTO likeUpdateDto) {
        if (likeUpdateDto == null || likeUpdateDto.getId() == null) {
            throw new ApiRequestException("Güncellenmek istenen beğeni verisi eksik.");
        }

        Like existingLike = likeRepository.findById(likeUpdateDto.getId())
                .orElseThrow(() -> new ApiNotFoundException("Güncellenmek istenen beğeni bulunamadı."));

        AppUser appUser = appUserRepository.findById(likeUpdateDto.getUserId())
                .orElseThrow(() -> new ApiNotFoundException("Kullanıcı bulunamadı."));

        Trailer trailer = trailerRepository.findById(likeUpdateDto.getTrailerId())
                .orElseThrow(() -> new ApiNotFoundException("Fragman bulunamadı."));

        Like like = likeMapper.likeUpdateDTOtoLike(likeUpdateDto);
        like.setUser(appUser);
        like.setTrailer(trailer);
        likeRepository.save(like);
    }

    public void deleteLike(Long id) {
        if (id == null || !likeRepository.existsById(id)) {
            throw new ApiNotFoundException("Silinmek istenen beğeni bulunamadı.");
        }

        likeRepository.deleteById(id);
    }

    public LikeResponseDTO getLikebyId(Long id) {
        Like like = likeRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Beğeni bulunamadı."));
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
