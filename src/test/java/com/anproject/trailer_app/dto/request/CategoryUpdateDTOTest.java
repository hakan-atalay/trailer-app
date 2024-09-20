package com.anproject.trailer_app.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryUpdateDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void whenValidCategoryUpdateDTO_thenNoConstraintViolations() {
		CategoryUpdateDTO dto = new CategoryUpdateDTO();
		dto.setId(1L);
		dto.setCategoryName("Oyun");

		Set<ConstraintViolation<CategoryUpdateDTO>> violations = validator.validate(dto);

		assertTrue(violations.isEmpty());
	}

	@Test
	public void whenNullId_thenConstraintViolation() {
		CategoryUpdateDTO dto = new CategoryUpdateDTO();
		dto.setId(null);
		dto.setCategoryName("Oyun");

		Set<ConstraintViolation<CategoryUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	public void whenBlankCategoryName_thenConstraintViolation() {
		CategoryUpdateDTO dto = new CategoryUpdateDTO();
		dto.setId(1L);
		dto.setCategoryName("");

		Set<ConstraintViolation<CategoryUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Kategori boş olamaz.", violations.iterator().next().getMessage());
	}

	@Test
	public void whenNullCategoryName_thenConstraintViolation() {
		CategoryUpdateDTO dto = new CategoryUpdateDTO();
		dto.setId(1L);
		dto.setCategoryName(null);

		Set<ConstraintViolation<CategoryUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Kategori boş olamaz.", violations.iterator().next().getMessage());
	}

}