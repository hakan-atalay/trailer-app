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
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void whenValidTrailerUpdateDTO_thenNoConstraintViolations() {
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
	void whenIdIsNull_thenConstraintViolation() {
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
	void whenTitleIsBlank_thenConstraintViolation() {
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
	void whenUrlIsInvalid_thenConstraintViolation() {
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
	void whenCategoryIdIsNull_thenConstraintViolation() {
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
	void whenUserIdIsNull_thenConstraintViolation() {
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
