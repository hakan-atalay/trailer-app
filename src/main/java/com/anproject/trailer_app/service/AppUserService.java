package com.anproject.trailer_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anproject.trailer_app.dto.request.AppUserRequestDTO;
import com.anproject.trailer_app.dto.request.AppUserUpdateDTO;
import com.anproject.trailer_app.dto.response.AppUserResponseDTO;
import com.anproject.trailer_app.entity.AppUser;
import com.anproject.trailer_app.entity.Role;
import com.anproject.trailer_app.exception.ApiNotFoundException;
import com.anproject.trailer_app.exception.ApiRequestException;
import com.anproject.trailer_app.mapper.AppUserMapper;
import com.anproject.trailer_app.repository.AppUserRepository;
import com.anproject.trailer_app.repository.RoleRepository;

@Service
public class AppUserService {
	
    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final AppUserMapper appUserMapper;
    
    @Autowired
    public AppUserService(AppUserRepository appUserRepository, RoleRepository roleRepository,
                          AppUserMapper appUserMapper) {
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
        this.appUserMapper = appUserMapper;
    }
    
    public void saveUser(AppUserRequestDTO appUserRequestDto) {
        if (appUserRequestDto == null) {
            throw new ApiRequestException("Geçersiz kullanıcı verisi sağlandı.");
        }

        AppUser appUser = appUserMapper.appUserRequestDTOtoAppUser(appUserRequestDto);
        appUserRepository.save(appUser);
    }
    
    public void updateUser(AppUserUpdateDTO appUserUpdateDto) {
        if (appUserUpdateDto == null || appUserUpdateDto.getId() == null) {
            throw new ApiRequestException("Güncellenmek istenen kullanıcı verisi eksik.");
        }

        AppUser existingAppUser = appUserRepository.findById(appUserUpdateDto.getId())
                .orElseThrow(() -> new ApiNotFoundException("Güncellenmek istenen kullanıcı bulunamadı."));

        Role role = roleRepository.findById(appUserUpdateDto.getRoleId())
                .orElseThrow(() -> new ApiNotFoundException("Rol bulunamadı."));

        AppUser appUser = appUserMapper.appUserUpdateDTOtoAppUser(appUserUpdateDto);
        appUser.setRole(role);
        appUserRepository.save(appUser);
    }
    
    public void deleteUser(Long id) {
        if (id == null || !appUserRepository.existsById(id)) {
            throw new ApiNotFoundException("Silinmek istenen kullanıcı bulunamadı.");
        }

        appUserRepository.deleteById(id);
    }
    
    public AppUserResponseDTO getUserbyId(Long id) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Kullanıcı bulunamadı."));
        return appUserMapper.appUserToAppUserResponseDTO(appUser);
    }
    
    public List<AppUserResponseDTO> getAllUsers() {
        List<AppUser> userList = appUserRepository.findAll();
        return userList.stream()
                .map(user -> appUserMapper.appUserToAppUserResponseDTO(user))
                .collect(Collectors.toList());
    }
    
}
