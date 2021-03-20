package com.cognizant.GMDB;

import com.cognizant.GMDB.model.MoviesDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GMDBTestIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getMoviesEmptyTest() throws Exception {
        mockMvc.perform(get("/movies")).andExpect(status().isOk())
                .andExpect(content().json("[ ]"));
    }

    @Test
    public void addMovieTest() throws Exception {
        MoviesDto movie = MoviesDto.builder().title("Citizen Kane").build();
        MoviesDto movie2 = MoviesDto.builder().title("The Big Lebowski").build();

        mockMvc.perform(post("/movies")
                .content(objectMapper.writeValueAsString(movie))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("[0].title").value("Citizen Kane"));

        mockMvc.perform(post("/movies")
                .content(objectMapper.writeValueAsString(movie2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("[0].title").value("Citizen Kane"))
                .andExpect(jsonPath("[1].title").value("The Big Lebowski"));
    }

    @Test
    public void getMoviesByTitleTest() throws Exception {

        MoviesDto movie1 = MoviesDto.builder().title("Mystic River").build();
        MoviesDto movie2 = MoviesDto.builder().title("The BodyGuard").build();

        mockMvc.perform(post("/movies")
                .content(objectMapper.writeValueAsString(movie1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/movies")
                .content(objectMapper.writeValueAsString(movie2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(get(String.format("/movies/%s",movie1.getTitle())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Mystic River"));


        mockMvc.perform(get(String.format("/movies/%s","The big short")))
                .andExpect(status().isNotFound());
    }

    @Test
    void newMovieDefaultStarRatingIsZero() throws Exception {
        MoviesDto movie1 = MoviesDto.builder().title("Mystic River").build();

        mockMvc.perform(post("/movies")
                .content(objectMapper.writeValueAsString(movie1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(get(String.format("/movies/%s",movie1.getTitle())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Mystic River"))
                .andExpect(jsonPath("$.starRating").value(0));
    }

    @Test
    public void updateStarRatingOfMovieReturnsUpdatedRating() throws Exception {
        MoviesDto movie1 = MoviesDto.builder().title("Mystic River").build();

        mockMvc.perform(post("/movies")
                .content(objectMapper.writeValueAsString(movie1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(get(String.format("/movies/%s",movie1.getTitle())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Mystic River"))
                .andExpect(jsonPath("$.starRating").value(0));

        movie1.setStarRating(5);

        mockMvc.perform(put(String.format("/movies/%s",movie1.getTitle()))
                .content(objectMapper.writeValueAsString(movie1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        mockMvc.perform(get(String.format("/movies/%s",movie1.getTitle())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Mystic River"))
                .andExpect(jsonPath("$.starRating").value(5))
                .andExpect(jsonPath("$.allStarRatings.length()").value(1));
    }
}
