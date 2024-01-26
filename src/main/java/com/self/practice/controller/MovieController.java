package com.self.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.self.practice.model.Movie;
import com.self.practice.service.MovieService;

@RestController
public class MovieController {

	@Autowired
	MovieService movieService;

	@GetMapping("/index")
	public String index() {
		return "index"; // logical name "index" gets mapped to physical view -> /templates/index.html
	}

	@GetMapping("/admin")
	public ModelAndView admin() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin");
		return mav; // logical name "admin" gets mapped to physical view -> /templates/admin.html
	}

	@GetMapping("/adminPage")
	public ModelAndView formAdmin(@RequestParam("uname") String username, @RequestParam("psw") String password) {
		ModelAndView mav = new ModelAndView();
		if (username.equalsIgnoreCase("hari") && password.equals("hari@123")) {
			mav.setViewName("adminLogin");
		} else {
			mav.setViewName("loginIssue");
		}
		return mav;
	}

	@GetMapping("/showMoviesByMovieId")
	public ModelAndView searchByMovieId(Model model) {
		// create model attribute to bind form data
		ModelAndView mav = new ModelAndView();
		List<Movie> movies = movieService.getMovies();
		mav.addObject("movie", movies);
		mav.setViewName("movieId");
		return mav;
	}

	@GetMapping("/showMoviesByMovieName")
	public ModelAndView searchByMovieName(Model model) {
		// create model attribute to bind form data
		ModelAndView mav = new ModelAndView();
		List<Movie> movies = movieService.getMovies();
		mav.addObject("movie", movies);
		mav.setViewName("movieName");
		return mav;
	}

	@GetMapping("/showMoviesByMovieCollection")
	public ModelAndView searchByMovieCollection(Model model) {
		// create model attribute to bind form data
		ModelAndView mav = new ModelAndView();
		List<Movie> movies = movieService.getMovies();
		mav.addObject("movie", movies);
		mav.setViewName("movieCollection");
		return mav;
	}

	@GetMapping("/movie/movieId")
	public ModelAndView showMoviesById(@RequestParam("movieId") String movieId) {
		ModelAndView mav = new ModelAndView();
		Movie movies = movieService.searchMovieById(movieId);
		if (movies == null) {
			mav.setViewName("movieEmptyList");
		} else {
			mav.addObject("movie", movies);
			mav.setViewName("movieIdList");
		}
		return mav;
	}

	@GetMapping("/movie/movieName")
	public ModelAndView showMoviesByName(@RequestParam("movieName") String movieName) {
		ModelAndView mav = new ModelAndView();
		List<Movie> movies = movieService.searchMovieByName(movieName);
		if (movies.isEmpty()) {
			mav.setViewName("movieEmptyList");
		} else {
			mav.addObject("movie", movies);
			mav.setViewName("movieNameList");
		}
		return mav;
	}

	@GetMapping("/movie/movieCollection")
	public ModelAndView showMoviesByCollection(@RequestParam("from") String from, @RequestParam("to") String to) {
		ModelAndView mav = new ModelAndView();
		List<Movie> movies = movieService.searchMovieByCollection(from, to);
		if (movies.isEmpty()) {
			mav.setViewName("movieEmptyList");
		} else {
			mav.addObject("movie", movies);
			mav.setViewName("movieCollectionList");
		}
		return mav;
	}

	@GetMapping("/addMovieDetailsPage")
	public ModelAndView getMovieDetails() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("addMovieDeails");
		return mav;
	}

	@PostMapping("/addMovieDetails")
	public ModelAndView createMovieDetails(Movie movie) {
		System.out.println("Inside the controller createMovieDetails post method");
		ModelAndView mav = new ModelAndView();
		Movie movie2 = movieService.searchMovieById(movie.getMovieId());
		if (movie2 != null) {
			mav.setViewName("recordExist");
		} else {
			Movie moviesInfo = new Movie();
			moviesInfo.setMovieId(movie.getMovieId());
			moviesInfo.setMovieName(movie.getMovieName());
			moviesInfo.setMovieCollection(movie.getMovieCollection());
			System.out.println("Details of movie is: " + moviesInfo.getMovieId() + " " + moviesInfo.getMovieName() + " "
					+ moviesInfo.getMovieCollection());
			moviesInfo = movieService.createMovieDetails(moviesInfo);
			mav.setViewName("success");
			mav.addObject("movie", moviesInfo);
		}
		return mav;
	}

	@GetMapping("/getMovies")
	public ModelAndView getMovies() {
		List<Movie> movies = movieService.getMovies();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("displayAllMovieDetails");
		mav.addObject("movie", movies);
		return mav;
	}

	@GetMapping("/modifyMovie")
	public ModelAndView modifyMovie() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("fail");
		return mav;
	}

/*	
	@GetMapping("/movie/{movieId}")
	public Movie showMovieById(@PathVariable String movieId) {
		return movieService.getMovieById(movieId);
	}

	@GetMapping("/getMovieById/{movieId}")
	public Movie searchMovieById(Model model, @PathVariable(value = "movieId") String movieId) {
		ModelAndView mav = new ModelAndView();
		Movie movies = movieService.getMovieById(movieId);
		mav.addObject("movie", movies);
		return movies;
	}
*/

}
