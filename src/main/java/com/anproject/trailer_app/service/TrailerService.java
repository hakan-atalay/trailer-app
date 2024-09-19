package com.anproject.trailer_app.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anproject.trailer_app.dto.request.TrailerRequestDTO;
import com.anproject.trailer_app.dto.request.TrailerUpdateDTO;
import com.anproject.trailer_app.dto.response.TrailerResponseDTO;
import com.anproject.trailer_app.entity.AppUser;
import com.anproject.trailer_app.entity.Category;
import com.anproject.trailer_app.entity.Trailer;
import com.anproject.trailer_app.exception.ApiNotFoundException;
import com.anproject.trailer_app.exception.ApiRequestException;
import com.anproject.trailer_app.mapper.TrailerMapper;
import com.anproject.trailer_app.repository.AppUserRepository;
import com.anproject.trailer_app.repository.CategoryRepository;
import com.anproject.trailer_app.repository.TrailerRepository;

@Service
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
        if (trailerRequestDto == null || trailerRequestDto.getCategoryId() == null || trailerRequestDto.getUserId() == null) {
            throw new ApiRequestException("Geçersiz fragman verisi sağlandı.");
        }

        Trailer trailer = trailerMapper.trailerRequestDTOtoTrailer(trailerRequestDto);
        trailer.setCreatedAt(new Date());
        trailerRepository.save(trailer);
    }

    public void updateTrailer(TrailerUpdateDTO trailerUpdateDto) {
        if (trailerUpdateDto == null || trailerUpdateDto.getId() == null) {
            throw new ApiRequestException("Güncellenmek istenen fragman verisi eksik.");
        }

        Trailer existingTrailer = trailerRepository.findById(trailerUpdateDto.getId())
                .orElseThrow(() -> new ApiNotFoundException("Güncellenmek istenen fragman bulunamadı."));

        Category category = categoryRepository.findById(trailerUpdateDto.getCategoryId())
                .orElseThrow(() -> new ApiNotFoundException("Kategori bulunamadı."));

        AppUser appUser = appUserRepository.findById(trailerUpdateDto.getUserId())
                .orElseThrow(() -> new ApiNotFoundException("Kullanıcı bulunamadı."));

        Trailer trailer = trailerMapper.trailerUpdateDTOtoTrailer(trailerUpdateDto);
        trailer.setCreatedAt(existingTrailer.getCreatedAt());
        trailer.setUser(appUser);
        trailer.setCategory(category);
        trailerRepository.save(trailer);
    }

    public void deleteTrailer(Long id) {
        if (id == null || !trailerRepository.existsById(id)) {
            throw new ApiNotFoundException("Silinmek istenen fragman bulunamadı.");
        }

        trailerRepository.deleteById(id);
    }

    public TrailerResponseDTO getTrailerbyId(Long id) {
        Trailer trailer = trailerRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Fragman bulunamadı."));
        return trailerMapper.trailerToTrailerResponseDTO(trailer);
    }

    public List<TrailerResponseDTO> getAllTrailers() {
        List<Trailer> trailerList = trailerRepository.findAll();
        return trailerList.stream()
                .map(trailer -> trailerMapper.trailerToTrailerResponseDTO(trailer))
                .collect(Collectors.toList());
    }
    
}
