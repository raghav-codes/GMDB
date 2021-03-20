package com.cognizant.GMDB.service;

import com.cognizant.GMDB.model.MovieEntity;
import com.cognizant.GMDB.model.MoviesDto;
import com.cognizant.GMDB.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void addMovie(MoviesDto movie) {
        MovieEntity movieEntity = MovieEntity.builder()
                .title(movie.getTitle())
                .actor(movie.getActor())
                .director(movie.getDirector())
                .releaseYear(movie.getReleaseYear())
                .description(movie.getDescription())
                .build();

        movieRepository.save(movieEntity);
    }
}
