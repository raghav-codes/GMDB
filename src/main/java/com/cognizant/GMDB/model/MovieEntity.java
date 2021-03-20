package com.cognizant.GMDB.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String title;
    String director;
    String actor;
    String releaseYear;
    String description;
}
