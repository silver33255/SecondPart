package com.panaskin.secureapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panaskin.secureapp.config.AbstractIntegrationTest;
import com.panaskin.secureapp.entity.FilmSession;
import com.panaskin.secureapp.repository.FilmSessionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class FilmSessionControllerIT extends AbstractIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private FilmSessionRepository filmSessionRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private static FilmSession filmSession;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .alwaysDo(print())
                .build();
    }

    @Test
    public void filmSessions_shouldReceiveTwoFilmSessions() throws Exception{
        FilmSession fistSession = FilmSession.builder().id(5L).film("FirstTestFilm").build();
        filmSessionRepository.save(fistSession);
        FilmSession secondSession = FilmSession.builder().id(11L).film("SecondTestFilm").build();
        filmSessionRepository.save(secondSession);
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void filmSessionById_shouldReceiveFilmSessionWithTestFilmName() throws Exception{
        FilmSession filmSession = FilmSession.builder().id(12L).film("TestFilmName").build();
        Long savedFilmId = filmSessionRepository.save(filmSession).getId();
        mockMvc.perform(get("/" + savedFilmId.intValue()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.film", is(filmSession.getFilm())));
    }

    @Test
    public void addFilmSession() throws Exception{
        FilmSession filmSession = FilmSession.builder().id(3L).film("TestFilm").build();
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filmSession)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", aMapWithSize(5)));
    }

    @AfterEach
    void cleanRepository(){
        filmSessionRepository.deleteAll();
    }
}
