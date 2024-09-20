package com.anproject.trailer_app.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserRequestDTO {
	
	@NotBlank(message = "Kullanıcı adı boş olamaz.")
	@Size(min=4, max=20, message = "Kullanıcı adı 4 ile 20 karakter arasında olmalı.")
	private String nickname;
	
	@NotBlank(message = "Email boş olamaz.")
	@Email(message = "Geçerli bir email adresi giriniz.")
	private String email;
	
	@NotBlank(message = "Şifre boş olamaz.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$", 
    message = "Şifre en az bir büyük harf, bir küçük harf, bir rakam ve toplam en az 8 karakter uzunluğunda olmalıdır.")
	private String password;
	
	private Long roleId;
	
}
