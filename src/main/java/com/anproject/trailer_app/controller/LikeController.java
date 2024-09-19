package com.anproject.trailer_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anproject.trailer_app.dto.request.LikeRequestDTO;
import com.anproject.trailer_app.dto.request.LikeUpdateDTO;
import com.anproject.trailer_app.dto.response.LikeResponseDTO;
import com.anproject.trailer_app.service.LikeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

	private final LikeService likeService;

	@Autowired
	public LikeController(LikeService likeService) {
		this.likeService = likeService;
	}

	@PostMapping("/save")
	public ResponseEntity<Void> saveLike(@Valid @RequestBody LikeRequestDTO likeRequestDTO) {
		likeService.saveLike(likeRequestDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Void> updateLike(@PathVariable Long id, @Valid @RequestBody LikeUpdateDTO likeUpdateDto) {
		likeUpdateDto.setId(id);
		likeService.updateLike(likeUpdateDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteLike(@PathVariable Long id) {
		likeService.deleteLike(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/get-by-id/{id}")
	public ResponseEntity<LikeResponseDTO> getLikeById(@PathVariable Long id) {
		LikeResponseDTO likeResponseDto = likeService.getLikebyId(id);
		return new ResponseEntity<>(likeResponseDto, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<LikeResponseDTO>> getAllLikes() {
		List<LikeResponseDTO> likeResponseDtoList = likeService.getAllLikes();
		return new ResponseEntity<>(likeResponseDtoList, HttpStatus.OK);
	}

}
