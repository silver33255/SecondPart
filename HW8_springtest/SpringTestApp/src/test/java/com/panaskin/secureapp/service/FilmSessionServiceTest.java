package com.panaskin.secureapp.service;

import com.panaskin.secureapp.entity.FilmSession;
import com.panaskin.secureapp.exception.FilmSessionNotFoundException;
import com.panaskin.secureapp.repository.FilmSessionRepository;
import com.panaskin.secureapp.service.impl.FilmSessionServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FilmSessionServiceTest {

    private final FilmSessionRepository filmSessionRepository = mock(FilmSessionRepository.class);
    private final FilmSessionService filmSessionService = new FilmSessionServiceImpl(filmSessionRepository);

    @Test
    public void findById_FilmSessionWithEnteredIdNotExistInDb_ShouldThrowException(){
        //GIVEN
        Long notExistId = 3L;
        when(filmSessionRepository.findById(anyLong())).thenReturn(Optional.empty());
        //THEN
        assertThrows(FilmSessionNotFoundException.class, () -> filmSessionService.findById(notExistId));
        verify(filmSessionRepository, times(1)).findById(any());
    }

    @Test
    public void findById_FilmSessionWithEnteredIdExistInDb_ShouldReturnExpectedFilmSession(){
        //GIVEN
        Long filmSessionId = 1l;
        FilmSession expectedFilmSession = FilmSession.builder().id(filmSessionId).film("Fist film").build();
        when(filmSessionRepository.findById(anyLong())).thenReturn(Optional.of(expectedFilmSession));
        //WHEN
        FilmSession resultFilmSession = filmSessionService.findById(filmSessionId);
        //THEN
        verify(filmSessionRepository, times(1)).findById(any());
        assertEquals("Fist film", resultFilmSession.getFilm());
    }

    @Test
    public void findAll_ShouldReceiveListWithTwoSessions(){
        //GIVEN
        FilmSession filmSession1 = FilmSession.builder().id(1l).build();
        FilmSession filmSession2 = FilmSession.builder().id(2l).build();
        List<FilmSession> filmSessionList = List.of(filmSession1, filmSession2);
        when(filmSessionRepository.findAll()).thenReturn(filmSessionList);
        //WHEN
        List<FilmSession> resultList = filmSessionService.findAll();
        //THEN
        verify(filmSessionRepository, times(1)).findAll();
        assertEquals(2, resultList.size());
    }

    @Test
    public void save_FilmSession_ShouldExecuteMethodOnce(){
        //GIVEN
        FilmSession filmSession = FilmSession.builder().id(1l).build();
        //WHEN
        filmSessionService.save(filmSession);
        //THEN
        verify(filmSessionRepository, times(1)).save(filmSession);
    }
}
