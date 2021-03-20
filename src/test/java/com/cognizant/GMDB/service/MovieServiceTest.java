package com.cognizant.GMDB.service;

import com.cognizant.GMDB.model.MovieEntity;
import com.cognizant.GMDB.model.MoviesDto;
import com.cognizant.GMDB.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieService movieService;

    @Test
    public void addMovieTest() {
        MoviesDto movie = MoviesDto.builder()
                .title("HI")
                .build();
        movieService.addMovie(movie);
        verify(movieRepository).save(
                MovieEntity.builder().title("HI").build()
        );
    }
}
