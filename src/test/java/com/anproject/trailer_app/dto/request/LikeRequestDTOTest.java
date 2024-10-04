package com.anproject.trailer_app.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LikeRequestDTOTest {

	private Validator validator;

	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void whenValidLikeRequestDTO_thenNoConstraintViolations() {
		LikeRequestDTO dto = new LikeRequestDTO();
		dto.setTrailerLike(true);
		dto.setUserId(1L);
		dto.setTrailerId(2L);

		Set<ConstraintViolation<LikeRequestDTO>> violations = validator.validate(dto);

		assertTrue(violations.isEmpty());
	}

	@Test
	void whenNullUserId_thenConstraintViolation() {
		LikeRequestDTO dto = new LikeRequestDTO();
		dto.setTrailerLike(true);
		dto.setUserId(null);
		dto.setTrailerId(2L);

		Set<ConstraintViolation<LikeRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	void whenNullTrailerId_thenConstraintViolation() {
		LikeRequestDTO dto = new LikeRequestDTO();
		dto.setTrailerLike(true);
		dto.setUserId(1L);
		dto.setTrailerId(null); 

		Set<ConstraintViolation<LikeRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	void whenAllFieldsAreNull_thenMultipleConstraintViolations() {
		LikeRequestDTO dto = new LikeRequestDTO();
		dto.setTrailerLike(null);
		dto.setUserId(null);
		dto.setTrailerId(null);

		Set<ConstraintViolation<LikeRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals(2, violations.size());
	}

}