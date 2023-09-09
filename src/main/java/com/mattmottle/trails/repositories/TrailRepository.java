package com.mattmottle.trails.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mattmottle.trails.models.Trail;

public interface TrailRepository extends CrudRepository<Trail, Long> {
	List <Trail> findAll();
}
