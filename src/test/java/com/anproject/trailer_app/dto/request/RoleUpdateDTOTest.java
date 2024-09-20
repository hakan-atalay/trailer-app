package com.anproject.trailer_app.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RoleUpdateDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void whenValidRoleUpdateDTO_thenNoConstraintViolations() {
		RoleUpdateDTO dto = new RoleUpdateDTO();
		dto.setId(1L);
		dto.setRoleName("USER");

		Set<ConstraintViolation<RoleUpdateDTO>> violations = validator.validate(dto);

		assertTrue(violations.isEmpty());
	}

	@Test
	public void whenIdIsNull_thenConstraintViolation() {
		RoleUpdateDTO dto = new RoleUpdateDTO();
		dto.setId(null);
		dto.setRoleName("user");

		Set<ConstraintViolation<RoleUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("must not be null", violations.iterator().next().getMessage());
	}

	@Test
	public void whenRoleNameIsBlank_thenConstraintViolation() {
		RoleUpdateDTO dto = new RoleUpdateDTO();
		dto.setId(1L);
		dto.setRoleName("");

		Set<ConstraintViolation<RoleUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Rol boş olamaz.", violations.iterator().next().getMessage());
	}

	@Test
	public void whenRoleNameIsNull_thenConstraintViolation() {
		RoleUpdateDTO dto = new RoleUpdateDTO();
		dto.setId(1L);
		dto.setRoleName(null);

		Set<ConstraintViolation<RoleUpdateDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Rol boş olamaz.", violations.iterator().next().getMessage());
	}
}
