package com.cognizant.GMDB.service;

import com.cognizant.GMDB.model.MovieEntity;
import com.cognizant.GMDB.model.MoviesDto;
import com.cognizant.GMDB.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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

    @Test
    public void findAllMovies(){
            MoviesDto movie = MoviesDto.builder()
                    .title("HI")
                    .build();
            MovieEntity moviesList = MovieEntity.builder().title("HI").build();


            when(movieRepository.findAll()).thenReturn(List.of(moviesList));

            List<MoviesDto> resultSet = movieService.getMovies();

            assertThat(resultSet).isEqualTo(List.of(movie));

            verify(movieRepository).findAll();
            verifyNoMoreInteractions(movieRepository);

    }
/*    @Test
        public void findMoviesByTitle(){
        MoviesDto movie = MoviesDto.builder().title("HI").build();
        MovieEntity moviesEntity = MovieEntity.builder().title("HI").build();

        when(movieRepository.findOne(anyString())).thenReturn(moviesEntity);

        MoviesDto resultSet = movieService.findByTitle("HI");

        assertThat(resultSet).isEqualTo(movie);

        verify(movieRepository).findOne();
        verifyNoMoreInteractions(movieRepository);



    }*/

}
