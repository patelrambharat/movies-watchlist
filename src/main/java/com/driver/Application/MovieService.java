package com.driver.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String addMovie(Movie movie) {
        return movieRepository.addMovie(movie);
    }

    public String addDirector(Director director) {
        return movieRepository.addDirector(director);
    }

    public String addMovieDirectorPair(String movieName, String directorName) {
        return movieRepository.addMovieDirectorPair(movieName,directorName);
    }

    public Movie getMovieByName(String movieName) {
        return movieRepository.getMovieByName(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return movieRepository.getDirectorByName(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        return movieRepository.getMoviesByDirectorName(directorName);

    }

    public List<String> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public String deleteDirectorByName(String directorName) {
        List<String> movies=movieRepository.getMoviesByDirectorName(directorName);
        for(String movie:movies){
            Movie movie1=movieRepository.getMovieByName(movie);
            if(movie1!=null){
                movieRepository.deleteMovies(movie);
            }
        }
        return movieRepository.deleteDirectorByName(directorName);
    }

    public String deleteAllDirectors() {
        List<String> directors=movieRepository.findAllDirectors();
        for(String director:directors){
            movieRepository.deleteDirectorByName(director);
        }
        List<Movie> unassignedMovies=movieRepository.unassignedMovies();
        movieRepository.deleteAllMovies();
        for(Movie movie:unassignedMovies){
            movieRepository.addMovieInMovieDb(movie);
        }
        return "success";
    }
}
