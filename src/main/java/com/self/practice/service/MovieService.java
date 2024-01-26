package com.self.practice.service;

import java.util.List;

import com.self.practice.model.Movie;

public interface MovieService {

	public Movie createMovieDetails(Movie movieInfo);

	public List<Movie> getMovies();

	public Movie searchMovieById(String movieId);

	public List<Movie> searchMovieByName(String movieName);

	public List<Movie> searchMovieByCollection(String from, String to);
}
