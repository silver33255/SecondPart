package com.panaskin.secureapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panaskin.secureapp.entity.FilmSession;
import com.panaskin.secureapp.service.FilmSessionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class FilmSessionControllerTest {
    private final FilmSessionService filmSessionService = Mockito.mock(FilmSessionService.class);
    private final FilmSessionController fsc = new FilmSessionController(filmSessionService);
    private final MockMvc mockMvc = standaloneSetup(fsc).build();

    @Test
    public void filmSessions_ShouldReceiveTwoFilmSessionsWithExpectedIdAndFilm() throws Exception {
        FilmSession first = FilmSession.builder().id(1L).film("Swan").build();
        FilmSession second = FilmSession.builder().id(2L).film("Bird").build();
        List<FilmSession> filmSessions = Arrays.asList(first, second);
        when(filmSessionService.findAll()).thenReturn(filmSessions);

        mockMvc.perform(get("/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(first.getId().intValue())))
                .andExpect(jsonPath("$[0].film", is(first.getFilm())))
                .andExpect(jsonPath("$[1].id", is(second.getId().intValue())))
                .andExpect(jsonPath("$[1].film", is(second.getFilm())));
    }

    @Test
    public void addSession_ShouldReturnOkStatusAndContentTypeJson() throws Exception {
        FilmSession filmSession = FilmSession.builder().id(1L).build();
        when(filmSessionService.save(any())).thenReturn(filmSession);
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(filmSession)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void filmSessionsById_ShouldReceiveFilmSessionWithExpectedIdAndFilmName() throws Exception {
        Long filmSessionId = 1L;
        FilmSession filmSession = FilmSession.builder().id(filmSessionId).film("Alone").build();
        when(filmSessionService.findById(any())).thenReturn(filmSession);

        mockMvc.perform(get("/" + filmSessionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(filmSession.getId().intValue())))
                .andExpect(jsonPath("$.film", is(filmSession.getFilm())));
    }
}
