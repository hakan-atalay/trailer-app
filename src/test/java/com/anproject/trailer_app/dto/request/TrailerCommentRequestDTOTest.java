package com.anproject.trailer_app.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TrailerCommentRequestDTOTest {

	private Validator validator;

	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void whenValidTrailerCommentRequestDTO_thenNoConstraintViolations() {
		TrailerCommentRequestDTO dto = new TrailerCommentRequestDTO();
		dto.setUserId(1L);
		dto.setTrailerId(1L);
		dto.setTrailerComment("Great trailer!");

		Set<ConstraintViolation<TrailerCommentRequestDTO>> violations = validator.validate(dto);

		assertTrue(violations.isEmpty());
	}

	@Test
	void whenUserIdIsNull_thenConstraintViolation() {
		TrailerCommentRequestDTO dto = new TrailerCommentRequestDTO();
		dto.setUserId(null);
		dto.setTrailerId(1L);
		dto.setTrailerComment("Great trailer!");

		Set<ConstraintViolation<TrailerCommentRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	void whenTrailerIdIsNull_thenConstraintViolation() {
		TrailerCommentRequestDTO dto = new TrailerCommentRequestDTO();
		dto.setUserId(1L);
		dto.setTrailerId(null);
		dto.setTrailerComment("Great trailer!");

		Set<ConstraintViolation<TrailerCommentRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	void whenTrailerCommentIsBlank_thenConstraintViolation() {
		TrailerCommentRequestDTO dto = new TrailerCommentRequestDTO();
		dto.setUserId(1L);
		dto.setTrailerId(1L);
		dto.setTrailerComment("");

		Set<ConstraintViolation<TrailerCommentRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Yorum boş olamaz.", violations.iterator().next().getMessage());
	}

	@Test
	void whenTrailerCommentIsNull_thenConstraintViolation() {
		TrailerCommentRequestDTO dto = new TrailerCommentRequestDTO();
		dto.setUserId(1L);
		dto.setTrailerId(1L);
		dto.setTrailerComment(null);

		Set<ConstraintViolation<TrailerCommentRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Yorum boş olamaz.", violations.iterator().next().getMessage());
	}

}
