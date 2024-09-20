package com.anproject.trailer_app.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TrailerCommentUpdateDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void whenValidTrailerCommentUpdateDTO_thenNoConstraintViolations() {
		TrailerCommentUpdateDTO dto = new TrailerCommentUpdateDTO();
		dto.setId(1L);
		dto.setUserId(1L);
		dto.setTrailerId(1L);
		dto.setTrailerComment("dsafasdfsda");

		Set<ConstraintViolation<TrailerCommentUpdateDTO>> violations = validator.validate(dto);

		assertTrue(violations.isEmpty());
	}

	@Test
	public void whenIdIsNull_thenConstraintViolation() {
		TrailerCommentUpdateDTO dto = new TrailerCommentUpdateDTO();
		dto.setId(null);
		dto.setUserId(1L);
		dto.setTrailerId(1L);
		dto.setTrailerComment("dsafasdfsda");

		Set<ConstraintViolation<TrailerCommentUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	public void whenUserIdIsNull_thenConstraintViolation() {
		TrailerCommentUpdateDTO dto = new TrailerCommentUpdateDTO();
		dto.setId(1L);
		dto.setUserId(null); 
		dto.setTrailerId(1L);
		dto.setTrailerComment("dsafasdfsda");

		Set<ConstraintViolation<TrailerCommentUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	public void whenTrailerIdIsNull_thenConstraintViolation() {
		TrailerCommentUpdateDTO dto = new TrailerCommentUpdateDTO();
		dto.setId(1L);
		dto.setUserId(1L);
		dto.setTrailerId(null);
		dto.setTrailerComment("dsafasdfsda");

		Set<ConstraintViolation<TrailerCommentUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	public void whenTrailerCommentIsBlank_thenConstraintViolation() {
		TrailerCommentUpdateDTO dto = new TrailerCommentUpdateDTO();
		dto.setId(1L);
		dto.setUserId(1L);
		dto.setTrailerId(1L);
		dto.setTrailerComment("");

		Set<ConstraintViolation<TrailerCommentUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Yorum boş olamaz.", violations.iterator().next().getMessage());
	}

	@Test
	public void whenTrailerCommentIsNull_thenConstraintViolation() {
		TrailerCommentUpdateDTO dto = new TrailerCommentUpdateDTO();
		dto.setId(1L);
		dto.setUserId(1L);
		dto.setTrailerId(1L);
		dto.setTrailerComment(null);

		Set<ConstraintViolation<TrailerCommentUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Yorum boş olamaz.", violations.iterator().next().getMessage());
	}

}
