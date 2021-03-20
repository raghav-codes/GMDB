package com.cognizant.GMDB.controller;

import com.cognizant.GMDB.model.MoviesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
}
