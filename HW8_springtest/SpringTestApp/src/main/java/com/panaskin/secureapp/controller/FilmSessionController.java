package com.panaskin.secureapp.controller;

import com.panaskin.secureapp.entity.FilmSession;
import com.panaskin.secureapp.service.FilmSessionService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@AllArgsConstructor
@RestController
public class FilmSessionController {

    @Autowired
    private FilmSessionService sessionService;

    @GetMapping("/")
    public List<FilmSession> filmSessions() {
        return sessionService.findAll();
    }

    @PostMapping("/")
    public FilmSession addSession(@RequestBody FilmSession filmSession) {
        return sessionService.save(filmSession);
    }

    @GetMapping("/{id}")
    public FilmSession filmSessionById(@PathVariable("id") long id) {
        return sessionService.findById(id);
    }

    @PutMapping("/booking/{id}")
    public FilmSession sessionBooking(@PathVariable("id") long bookedSessionId) {
        FilmSession filmSession = sessionService.findById(bookedSessionId);
        filmSession.setFree_seats(filmSession.getFree_seats()-1);
        return sessionService.save(filmSession);
    }
}