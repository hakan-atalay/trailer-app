package com.anproject.trailer_app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.anproject.trailer_app.entity.AppUser;
import com.anproject.trailer_app.repository.AppUserRepository;
import com.anproject.trailer_app.security.CustomUserDetails;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private AppUserRepository appUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
		Optional<AppUser> userDetail = appUserRepository.findByNickname(nickname);
		
		return userDetail.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı." + nickname));
	}

}
