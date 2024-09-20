package com.anproject.trailer_app.dto.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class AppUserUpdateDTOTest {

	private Validator validator;

	@BeforeEach
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void whenValidAppUserUpdateDTO_thenNoConstraintViolations() {
		AppUserUpdateDTO dto = new AppUserUpdateDTO();
		dto.setId(1L);
		dto.setNickname("ValidUser");
		dto.setEmail("user@gmail.com");
		dto.setPassword("Valid123");
		dto.setRoleId(1L);

		Set<ConstraintViolation<AppUserUpdateDTO>> violations = validator.validate(dto);

		assertTrue(violations.isEmpty());
	}

	@Test
	public void whenNullId_thenConstraintViolation() {
		AppUserUpdateDTO dto = new AppUserUpdateDTO();
		dto.setId(null);
		dto.setNickname("ValidUser");
		dto.setEmail("user@gmail.com");
		dto.setPassword("Valid123");

		Set<ConstraintViolation<AppUserUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	public void whenEmptyNickname_thenConstraintViolation() {
		AppUserUpdateDTO dto = new AppUserUpdateDTO();
		dto.setId(1L);
		dto.setNickname("");
		dto.setEmail("user@gmail.com");
		dto.setPassword("Valid123");

		Set<ConstraintViolation<AppUserUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Kullanıcı adı boş olamaz.", violations.iterator().next().getMessage());
	}

	@Test
	public void whenInvalidEmail_thenConstraintViolation() {
		AppUserUpdateDTO dto = new AppUserUpdateDTO();
		dto.setId(1L);
		dto.setNickname("ValidUser");
		dto.setEmail("invalid-email");
		dto.setPassword("Valid123");

		Set<ConstraintViolation<AppUserUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Geçerli bir email adresi giriniz.", violations.iterator().next().getMessage());
	}

	@Test
	public void whenShortPassword_thenConstraintViolation() {
		AppUserUpdateDTO dto = new AppUserUpdateDTO();
		dto.setId(1L);
		dto.setNickname("ValidUser");
		dto.setEmail("user@gmail.com");
		dto.setPassword("short");

		Set<ConstraintViolation<AppUserUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals(
				"Şifre en az bir büyük harf, bir küçük harf, bir rakam ve toplam en az 8 karakter uzunluğunda olmalıdır.",
				violations.iterator().next().getMessage());
	}

	@Test
	public void whenValidPasswordButNoUpperCase_thenConstraintViolation() {
		AppUserUpdateDTO dto = new AppUserUpdateDTO();
		dto.setId(1L);
		dto.setNickname("ValidUser");
		dto.setEmail("user@gmail.com");
		dto.setPassword("valid123");

		Set<ConstraintViolation<AppUserUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals(
				"Şifre en az bir büyük harf, bir küçük harf, bir rakam ve toplam en az 8 karakter uzunluğunda olmalıdır.",
				violations.iterator().next().getMessage());
	}
	
}
