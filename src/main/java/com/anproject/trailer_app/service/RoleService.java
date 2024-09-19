package com.anproject.trailer_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anproject.trailer_app.dto.request.RoleRequestDTO;
import com.anproject.trailer_app.dto.request.RoleUpdateDTO;
import com.anproject.trailer_app.dto.response.RoleResponseDTO;
import com.anproject.trailer_app.entity.Role;
import com.anproject.trailer_app.exception.ApiNotFoundException;
import com.anproject.trailer_app.exception.ApiRequestException;
import com.anproject.trailer_app.mapper.RoleMapper;
import com.anproject.trailer_app.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public void saveRole(RoleRequestDTO roleRequestDto) {
        if (roleRequestDto == null) {
            throw new ApiRequestException("Geçersiz rol verisi sağlandı.");
        }

        Role role = roleMapper.roleRequestDTOtoRole(roleRequestDto);
        roleRepository.save(role);
    }

    public void updateRole(RoleUpdateDTO roleUpdateDto) {
        if (roleUpdateDto == null || roleUpdateDto.getId() == null) {
            throw new ApiRequestException("Güncellenmek istenen rol verisi eksik.");
        }

        Role existingRole = roleRepository.findById(roleUpdateDto.getId())
                .orElseThrow(() -> new ApiNotFoundException("Güncellenmek istenen rol bulunamadı."));

        Role role = roleMapper.roleUpdateDTOtoRole(roleUpdateDto);
        roleRepository.save(role);
    }

    public void deleteRole(Long id) {
        if (id == null || !roleRepository.existsById(id)) {
            throw new ApiNotFoundException("Silinmek istenen rol bulunamadı.");
        }

        roleRepository.deleteById(id);
    }

    public RoleResponseDTO getRolebyId(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Rol bulunamadı."));
        return roleMapper.roleToRoleResponseDTO(role);
    }

    public List<RoleResponseDTO> getAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        return roleList.stream()
                .map(role -> roleMapper.roleToRoleResponseDTO(role))
                .collect(Collectors.toList());
    }
    
}
