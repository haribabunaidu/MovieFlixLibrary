package com.self.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie_details")
public class Movie {

	@Id
	@Column(name="MOVIE_ID")
	private String movieId;
	@Column(name="MOVIE_NAME")
	private String movieName;
	@Column(name="MOVIE_COLLECTION")
	private long movieCollection;

	public Movie() {

	}
/*
	public Movie(String movieName, long movieCollection) {
		super();
		this.movieName = movieName;
		this.movieCollection = movieCollection;
	}
*/
	public Movie(String movieId, String movieName, long movieCollection) {
		this.movieId = movieId;
		this.movieName = movieName;
		this.movieCollection = movieCollection;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public long getMovieCollection() {
		return movieCollection;
	}

	public void setMovieCollection(long movieCollection) {
		this.movieCollection = movieCollection;
	}

}
