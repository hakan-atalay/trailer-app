package com.anproject.trailer_app.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.anproject.trailer_app.entity.AppUser;
import com.anproject.trailer_app.entity.Role;

public class CustomUserDetails implements UserDetails{
	
	private AppUser appUser;
	
	public CustomUserDetails(AppUser appUser) {
		this.appUser = appUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Role role = appUser.getRole();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return appUser.getPassword();
	}

	@Override
	public String getUsername() {
		return appUser.getNickname();
	}
	
	public AppUser getAppUser() {
		return appUser;
	}
}
