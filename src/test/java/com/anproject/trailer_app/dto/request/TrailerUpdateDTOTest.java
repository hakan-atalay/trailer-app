package com.anproject.trailer_app.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TrailerUpdateDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void whenValidTrailerUpdateDTO_thenNoConstraintViolations() {
		TrailerUpdateDTO dto = new TrailerUpdateDTO();
		dto.setId(1L);
		dto.setTitle("fsadfsadf");
		dto.setUrl("https://trailer-app/trailer");
		dto.setCategoryId(1L);
		dto.setUserId(1L);

		Set<ConstraintViolation<TrailerUpdateDTO>> violations = validator.validate(dto);

		assertTrue(violations.isEmpty());
	}

	@Test
	public void whenIdIsNull_thenConstraintViolation() {
		TrailerUpdateDTO dto = new TrailerUpdateDTO();
		dto.setId(null);
		dto.setTitle("fsadfsadf");
		dto.setUrl("https://trailer-app/trailer");
		dto.setCategoryId(1L);
		dto.setUserId(1L);

		Set<ConstraintViolation<TrailerUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	public void whenTitleIsBlank_thenConstraintViolation() {
		TrailerUpdateDTO dto = new TrailerUpdateDTO();
		dto.setId(1L);
		dto.setTitle("");
		dto.setUrl("https://trailer-app/trailer");
		dto.setCategoryId(1L);
		dto.setUserId(1L);

		Set<ConstraintViolation<TrailerUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Başlık boş olamaz.", violations.iterator().next().getMessage());
	}

	@Test
	public void whenUrlIsInvalid_thenConstraintViolation() {
		TrailerUpdateDTO dto = new TrailerUpdateDTO();
		dto.setId(1L);
		dto.setTitle("fsadfsadf");
		dto.setUrl("invalid-url");
		dto.setCategoryId(1L);
		dto.setUserId(1L);

		Set<ConstraintViolation<TrailerUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Geçerli bir URL giriniz.", violations.iterator().next().getMessage());
	}

	@Test
	public void whenCategoryIdIsNull_thenConstraintViolation() {
		TrailerUpdateDTO dto = new TrailerUpdateDTO();
		dto.setId(1L);
		dto.setTitle("fsadfsadf");
		dto.setUrl("https://trailer-app/trailer");
		dto.setCategoryId(null);
		dto.setUserId(1L);

		Set<ConstraintViolation<TrailerUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	public void whenUserIdIsNull_thenConstraintViolation() {
		TrailerUpdateDTO dto = new TrailerUpdateDTO();
		dto.setId(1L);
		dto.setTitle("fsadfsadf");
		dto.setUrl("https://trailer-app/trailer");
		dto.setCategoryId(1L);
		dto.setUserId(null);

		Set<ConstraintViolation<TrailerUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

}
