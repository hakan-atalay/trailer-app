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
import com.anproject.trailer_app.exception.ApiNotFoundException;
import com.anproject.trailer_app.exception.ApiRequestException;
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
        if (trailerCommentRequestDto == null || trailerCommentRequestDto.getUserId() == null || trailerCommentRequestDto.getTrailerId() == null) {
            throw new ApiRequestException("Geçersiz yorum verisi sağlandı.");
        }

        TrailerComment trailerComment = trailerCommentMapper.trailerCommentRequestDTOtoTrailer(trailerCommentRequestDto);
        trailerComment.setCreatedAt(new Date());
        trailerCommentRepository.save(trailerComment);
    }

    public void updateTrailerComment(TrailerCommentUpdateDTO trailerCommentUpdateDto) {
        if (trailerCommentUpdateDto == null || trailerCommentUpdateDto.getId() == null) {
            throw new ApiRequestException("Güncellenmek istenen yorum verisi eksik.");
        }

        TrailerComment existingTrailerComment = trailerCommentRepository.findById(trailerCommentUpdateDto.getId())
                .orElseThrow(() -> new ApiNotFoundException("Güncellenmek istenen yorum bulunamadı."));

        AppUser appUser = appUserRepository.findById(trailerCommentUpdateDto.getUserId())
                .orElseThrow(() -> new ApiNotFoundException("Kullanıcı bulunamadı."));

        Trailer trailer = trailerRepository.findById(trailerCommentUpdateDto.getTrailerId())
                .orElseThrow(() -> new ApiNotFoundException("Fragman bulunamadı."));

        TrailerComment trailerComment = trailerCommentMapper.trailerCommentUpdateDTOtoTrailer(trailerCommentUpdateDto);
        trailerComment.setCreatedAt(existingTrailerComment.getCreatedAt());
        trailerComment.setUser(appUser);
        trailerComment.setTrailer(trailer);
        trailerCommentRepository.save(trailerComment);
    }

    public void deleteTrailerComment(Long id) {
        if (id == null || !trailerCommentRepository.existsById(id)) {
            throw new ApiNotFoundException("Silinmek istenen yorum bulunamadı.");
        }

        trailerCommentRepository.deleteById(id);
    }

    public TrailerCommentResponseDTO getTrailerCommentbyId(Long id) {
        TrailerComment trailerComment = trailerCommentRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Yorum bulunamadı."));
        return trailerCommentMapper.trailerCommentToTrailerCommentResponseDTO(trailerComment);
    }

    public List<TrailerCommentResponseDTO> getAllTrailerComments() {
        List<TrailerComment> trailerCommentList = trailerCommentRepository.findAll();
        return trailerCommentList.stream()
                .map(trailerComment -> trailerCommentMapper.trailerCommentToTrailerCommentResponseDTO(trailerComment))
                .collect(Collectors.toList());
    }
    
}
