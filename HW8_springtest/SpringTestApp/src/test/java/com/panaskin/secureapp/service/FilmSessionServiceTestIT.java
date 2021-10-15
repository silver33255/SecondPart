package com.panaskin.secureapp.service;

import com.panaskin.secureapp.config.AbstractIntegrationTest;
import com.panaskin.secureapp.entity.FilmSession;
import com.panaskin.secureapp.exception.FilmSessionNotFoundException;
import com.panaskin.secureapp.repository.FilmSessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilmSessionServiceTestIT extends AbstractIntegrationTest {
    @Autowired
    FilmSessionService filmSessionService;

    @Autowired
    FilmSessionRepository filmSessionRepository;

    @Test
    public void findAll_ShouldReceiveListWithTreeSessions() throws SQLException {
        //GIVEN
        filmSessionRepository.save(FilmSession.builder().id(1l).film("First").build());
        filmSessionRepository.save(FilmSession.builder().id(2l).film("Second").build());
        filmSessionRepository.save(FilmSession.builder().id(3l).film("Third").build());
        //WHEN
        List<FilmSession> filmSessionList = filmSessionService.findAll();
        //THEN
        assertEquals(3, filmSessionList.size());
    }

    @Test
    public void findById_ShouldReceiveUserWithGivenId(){
        //GIVEN
        Long givenId = 1L;
        FilmSession givenFilmSession = FilmSession.builder().id(givenId).film("Spider-man").build();
        filmSessionRepository.save(givenFilmSession);
        //WHEN
        FilmSession resultFilmSession = filmSessionService.findById(givenId);
        //THEN
        assertNotNull(resultFilmSession);
        assertEquals(givenId, resultFilmSession.getId());
    }

    @Test
    public void findById_ShouldThrowExceptionIfUseNotExistFilmSessionId(){
        //GIVEN
        Long givenId = 1L;
        Long notExistId = 2L;
        FilmSession givenFilmSession = FilmSession.builder().id(givenId).film("Spider-man").build();
        filmSessionRepository.save(givenFilmSession);
        //THEN
        assertThrows(FilmSessionNotFoundException.class, () -> filmSessionService.findById(notExistId));
    }

    @Test
    public void save_ShouldReturnUserListWithSizeTwoAfterSaveFilmSession(){
        //GIVEN
        FilmSession filmSession = FilmSession.builder().id(5l).film("Spider-man").build();
        //WHEN
        filmSessionService.save(filmSession);
        //THEN
        assertEquals(1, filmSessionRepository.findAll().size());
    }
}