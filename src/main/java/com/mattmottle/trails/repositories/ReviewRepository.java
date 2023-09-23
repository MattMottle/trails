package com.mattmottle.trails.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mattmottle.trails.models.Review;


@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
	List<Review> findAll();
	
//	List<Review> findAllByReviewedTrail(Trail trail);
}
