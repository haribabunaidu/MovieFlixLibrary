package com.self.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.self.practice.model.Movie;
import com.self.practice.repository.MovieRepository;

@Service
@EnableAutoConfiguration
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;

	public Movie createMovieDetails(Movie movieInfo) {
		System.out.println("new movieInfo details inside addMovieDetails service is:" + movieInfo.getMovieName() + " "
				+ movieInfo.getMovieCollection());
		System.out.println("repo object:" + movieRepository);
		return movieRepository.save(movieInfo);
	}

	@Override
	public List<Movie> getMovies() {
		return movieRepository.findAll(); // return all movie objects
	}

	@Override
	public Movie searchMovieById(String movieId) {
		return movieRepository.findByMovieId(movieId);
	}

	@Override
	public List<Movie> searchMovieByName(String movieName) {
		return movieRepository.findByNameFree(movieName);
	}

	@Override
	public List<Movie> searchMovieByCollection(String fromValue, String toValue) {
		return movieRepository.findByMovieCollection(Long.valueOf(fromValue), Long.valueOf(toValue));
	}

}
