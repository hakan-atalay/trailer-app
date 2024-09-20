package com.anproject.trailer_app.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LikeUpdateDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void whenValidLikeUpdateDTO_thenNoConstraintViolations() {
		LikeUpdateDTO dto = new LikeUpdateDTO();
		dto.setId(1L);
		dto.setTrailerLike(true);
		dto.setUserId(2L);
		dto.setTrailerId(3L);

		Set<ConstraintViolation<LikeUpdateDTO>> violations = validator.validate(dto);

		assertTrue(violations.isEmpty());
	}

	@Test
	public void whenNullId_thenConstraintViolation() {
		LikeUpdateDTO dto = new LikeUpdateDTO();
		dto.setId(null);
		dto.setTrailerLike(true);
		dto.setUserId(2L);
		dto.setTrailerId(3L);

		Set<ConstraintViolation<LikeUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	public void whenNullUserId_thenConstraintViolation() {
		LikeUpdateDTO dto = new LikeUpdateDTO();
		dto.setId(1L);
		dto.setTrailerLike(true);
		dto.setUserId(null);
		dto.setTrailerId(3L);

		Set<ConstraintViolation<LikeUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	public void whenNullTrailerId_thenConstraintViolation() {
		LikeUpdateDTO dto = new LikeUpdateDTO();
		dto.setId(1L);
		dto.setTrailerLike(true);
		dto.setUserId(2L);
		dto.setTrailerId(null);

		Set<ConstraintViolation<LikeUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	public void whenAllFieldsAreNull_thenMultipleConstraintViolations() {
		LikeUpdateDTO dto = new LikeUpdateDTO();
		dto.setId(null);
		dto.setTrailerLike(null);
		dto.setUserId(null);
		dto.setTrailerId(null);

		Set<ConstraintViolation<LikeUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals(3, violations.size());
	}
	
}
