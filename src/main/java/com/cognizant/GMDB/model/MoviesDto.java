package com.cognizant.GMDB.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    Integer starRating;
}
