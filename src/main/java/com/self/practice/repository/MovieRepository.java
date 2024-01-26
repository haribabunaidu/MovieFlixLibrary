package com.self.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.self.practice.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	Movie findByMovieId(String movieId);

	@Query("SELECT m FROM Movie m WHERE m.movieCollection > :fromValue and m.movieCollection < :toValue")
	List<Movie> findByMovieCollection(long fromValue, long toValue);

	@Query("select m from Movie m where lower(m.movieName) like lower(concat('%', :movieName,'%'))")
	public List<Movie> findByNameFree(@Param("movieName") String movieName);
}
