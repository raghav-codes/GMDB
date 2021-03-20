package com.cognizant.GMDB.controller;

import com.cognizant.GMDB.model.MoviesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    List<MoviesDto> moviesRepository = new ArrayList<>();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MoviesDto> getMovies() {
        return moviesRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovie(@RequestBody MoviesDto movie) {
        moviesRepository.add(movie);
    }

    @GetMapping("/{title}")
    public MoviesDto getMoviesByTitle(@PathVariable String title){

        MoviesDto result = moviesRepository.stream().filter(movie -> movie.getTitle().equals(title)).findFirst().orElse(null);

       if( result == null)
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Movies Not Found");
       return result;
    }

}
