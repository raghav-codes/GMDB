package com.cognizant.GMDB.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoviesDto {
    String title;
    String director;
    String actor;
    String releaseYear;
    String description;
    List<Integer> allStarRatings;
    Integer starRating;
}
