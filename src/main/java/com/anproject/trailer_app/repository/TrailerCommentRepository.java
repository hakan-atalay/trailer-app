package com.anproject.trailer_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anproject.trailer_app.entity.TrailerComment;

@Repository
public interface TrailerCommentRepository extends JpaRepository<TrailerComment, Long> {

}
