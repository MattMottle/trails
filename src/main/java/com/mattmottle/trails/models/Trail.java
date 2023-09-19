package com.mattmottle.trails.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="trails")
public class Trail {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@PrePersist
	 protected void onCreate(){
		 this.createdAt = new Date();
	    }
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	    }
	@NotEmpty(message="Please enter a name of the trail.")
	@Size(min=4, max=255, message="Name of trail must be 4-255 charcters.")
	private String name;
	@NotEmpty(message="Please enter a location of the trail.")
	@Size(min=4, max=255, message="Location of trail must be 3-255 characters.")
	private String location;
	@NotNull(message="Must choose whether trail was hiked.")
	private Boolean isHikedEntirely;
	@NotEmpty(message="Please describe the trail.")
	private String description;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User trailCreator;
	
	@OneToMany(mappedBy="reviewedTrail", fetch=FetchType.LAZY)
	private List<Review> allReviews;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Boolean getIsHikedEntirely() {
		return isHikedEntirely;
	}
	public void setIsHikedEntirely(Boolean isHikedEntirely) {
		this.isHikedEntirely = isHikedEntirely;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getTrailCreator() {
		return trailCreator;
	}
	public void setTrailCreator(User trailCreator) {
		this.trailCreator = trailCreator;
	}
	public List<Review> getAllReviews() {
		return allReviews;
	}
	public void setAllReviews(List<Review> allReviews) {
		this.allReviews = allReviews;
	}
	
}

