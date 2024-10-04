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

public class AppUserRequestDTOTest {

	private Validator validator;

	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void whenValidAppUserRequestDTO_thenNoConstraintViolations() {
		AppUserRequestDTO dto = new AppUserRequestDTO();
		dto.setNickname("valid user");
		dto.setEmail("user@gmail.com");
		dto.setPassword("Valid123");
		dto.setRoleId(2L);

		Set<ConstraintViolation<AppUserRequestDTO>> violations = validator.validate(dto);

		assertTrue(violations.isEmpty());
	}

	@Test
	void whenEmptyNickname_thenConstraintViolation() {
		AppUserRequestDTO dto = new AppUserRequestDTO();
		dto.setNickname("");
		dto.setEmail("user@gmail.com");
		dto.setPassword("Valid123");

		Set<ConstraintViolation<AppUserRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Kullanıcı adı boş olamaz.", violations.iterator().next().getMessage());
	}

	@Test
	void whenInvalidEmail_thenConstraintViolation() {
		AppUserRequestDTO dto = new AppUserRequestDTO();
		dto.setNickname("ValidUser");
		dto.setEmail("invalid-email");
		dto.setPassword("Valid123");

		Set<ConstraintViolation<AppUserRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Geçerli bir email adresi giriniz.", violations.iterator().next().getMessage());
	}

	@Test
	void whenShortPassword_thenConstraintViolation() {
		AppUserRequestDTO dto = new AppUserRequestDTO();
		dto.setNickname("ValidUser");
		dto.setEmail("user@gmail.com");
		dto.setPassword("short");

		Set<ConstraintViolation<AppUserRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals(
				"Şifre en az bir büyük harf, bir küçük harf, bir rakam ve toplam en az 8 karakter uzunluğunda olmalıdır.",
				violations.iterator().next().getMessage());
	}

	@Test
	void whenValidPasswordButNoUpperCase_thenConstraintViolation() {
		AppUserRequestDTO dto = new AppUserRequestDTO();
		dto.setNickname("ValidUser");
		dto.setEmail("user@gmail.com");
		dto.setPassword("valid123");

		Set<ConstraintViolation<AppUserRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals(
				"Şifre en az bir büyük harf, bir küçük harf, bir rakam ve toplam en az 8 karakter uzunluğunda olmalıdır.",
				violations.iterator().next().getMessage());
	}

}
