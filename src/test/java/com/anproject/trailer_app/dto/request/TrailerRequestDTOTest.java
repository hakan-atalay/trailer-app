package com.anproject.trailer_app.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TrailerRequestDTOTest {

	private Validator validator;

	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void whenValidTrailerRequestDTO_thenNoConstraintViolations() {
		TrailerRequestDTO dto = new TrailerRequestDTO();
		dto.setTitle("fsadfsadf");
		dto.setUrl("https://trailer-app/trailer");
		dto.setCategoryId(1L);
		dto.setUserId(1L);

		Set<ConstraintViolation<TrailerRequestDTO>> violations = validator.validate(dto);

		assertTrue(violations.isEmpty());
	}

	@Test
	void whenTitleIsNull_thenConstraintViolation() {
		TrailerRequestDTO dto = new TrailerRequestDTO();
		dto.setTitle(null);
		dto.setUrl("https://trailer-app/trailer");
		dto.setCategoryId(1L);
		dto.setUserId(1L);

		Set<ConstraintViolation<TrailerRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	void whenUrlIsInvalid_thenConstraintViolation() {
		TrailerRequestDTO dto = new TrailerRequestDTO();
		dto.setTitle("fsadfsadf");
		dto.setUrl("invalid-url");
		dto.setCategoryId(1L);
		dto.setUserId(1L);

		Set<ConstraintViolation<TrailerRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Geçerli bir URL giriniz.", violations.iterator().next().getMessage());
	}

	@Test
	void whenUrlIsNull_thenNoConstraintViolation() {
		TrailerRequestDTO dto = new TrailerRequestDTO();
		dto.setTitle("fsadfsadf");
		dto.setUrl(null);
		dto.setCategoryId(1L);
		dto.setUserId(1L);

		Set<ConstraintViolation<TrailerRequestDTO>> violations = validator.validate(dto);

		assertTrue(violations.isEmpty());
	}

	@Test
	void whenCategoryIdIsNull_thenConstraintViolation() {
		TrailerRequestDTO dto = new TrailerRequestDTO();
		dto.setTitle("fsadfsadf");
		dto.setUrl("https://trailer-app/trailer");
		dto.setCategoryId(null);
		dto.setUserId(1L);

		Set<ConstraintViolation<TrailerRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	void whenUserIdIsNull_thenConstraintViolation() {
		TrailerRequestDTO dto = new TrailerRequestDTO();
		dto.setTitle("fsadfsadf");
		dto.setUrl("https://trailer-app/trailer");
		dto.setCategoryId(1L);
		dto.setUserId(null);

		Set<ConstraintViolation<TrailerRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

}
