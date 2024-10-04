package com.anproject.trailer_app.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RoleRequestDTOTest {

	private Validator validator;

	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void whenValidRoleRequestDTO_thenNoConstraintViolations() {
		RoleRequestDTO dto = new RoleRequestDTO();
		dto.setRoleName("admin");

		Set<ConstraintViolation<RoleRequestDTO>> violations = validator.validate(dto);

		assertTrue(violations.isEmpty());
	}

	@Test
	void whenRoleNameIsBlank_thenConstraintViolation() {
		RoleRequestDTO dto = new RoleRequestDTO();
		dto.setRoleName("");

		Set<ConstraintViolation<RoleRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Rol boş olamaz.", violations.iterator().next().getMessage());
	}

	@Test
	void whenRoleNameIsNull_thenConstraintViolation() {
		RoleRequestDTO dto = new RoleRequestDTO();
		dto.setRoleName(null);

		Set<ConstraintViolation<RoleRequestDTO>> violations = validator.validate(dto);

		assertFalse(violations.isEmpty());
		assertEquals("Rol boş olamaz.", violations.iterator().next().getMessage());
	}

}
