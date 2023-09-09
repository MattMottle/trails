package com.mattmottle.trails.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mattmottle.trails.models.Trail;
import com.mattmottle.trails.repositories.TrailRepository;

@Service
public class TrailService {
	@Autowired
	private TrailRepository trailRepository;
	
	public List <Trail> findAll() {
		return trailRepository.findAll();
	}
	
	public Trail findById(Long trailId) {
		Optional <Trail> optionalTrail = trailRepository.findById(trailId);
		if(optionalTrail.isPresent()) {
			return optionalTrail.get();
		}
		return null;
	}
	
	public Trail create(Trail trail) {
		return trailRepository.save(trail);
	}
	
	public Trail updateTrail(Trail updatedTrail) {
		return trailRepository.save(updatedTrail);
	}
	public void deleteTrail(Long trailId) {
		trailRepository.deleteById(trailId);	
	}
}
