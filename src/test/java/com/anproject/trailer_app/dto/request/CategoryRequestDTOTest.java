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

public class CategoryRequestDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void whenValidCategoryName_thenNoConstraintViolations() {
		CategoryRequestDTO dto = new CategoryRequestDTO();
		dto.setCategoryName("Dizi");

		Set<ConstraintViolation<CategoryRequestDTO>> violations = validator.validate(dto);

		assertTrue(violations.isEmpty());
	}

	@Test
	public void whenBlankCategoryName_thenConstraintViolation() {
		CategoryRequestDTO dto = new CategoryRequestDTO();
		dto.setCategoryName("");

		Set<ConstraintViolation<CategoryRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Kategori boş olamaz.", violations.iterator().next().getMessage());
	}

	@Test
	public void whenNullCategoryName_thenConstraintViolation() {
		CategoryRequestDTO dto = new CategoryRequestDTO();
		dto.setCategoryName(null);

		Set<ConstraintViolation<CategoryRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Kategori boş olamaz.", violations.iterator().next().getMessage());
	}
}
