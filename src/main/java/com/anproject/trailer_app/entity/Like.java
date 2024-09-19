package com.anproject.trailer_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "likes")
public class Like {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "trailer_like")
	private Boolean trailerLike;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private AppUser user;

	@ManyToOne
	@JoinColumn(name = "trailer_id")
	private Trailer trailer;

}
