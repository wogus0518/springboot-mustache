package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.hospital.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
