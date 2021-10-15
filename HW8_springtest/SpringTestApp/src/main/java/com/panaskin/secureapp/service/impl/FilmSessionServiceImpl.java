package com.panaskin.secureapp.service.impl;

import com.panaskin.secureapp.entity.FilmSession;
import com.panaskin.secureapp.exception.FilmSessionNotFoundException;
import com.panaskin.secureapp.repository.FilmSessionRepository;
import com.panaskin.secureapp.service.FilmSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmSessionServiceImpl implements FilmSessionService {

    @Autowired
    private final FilmSessionRepository filmSessionRepository;

    @Override
    public List<FilmSession> findAll() {
        return filmSessionRepository.findAll();
    }

    @Override
    public FilmSession findById(Long id) {
        return filmSessionRepository.findById(id).orElseThrow(() ->
                new FilmSessionNotFoundException(id)
        );
    }

    @Override
    public FilmSession save(FilmSession session) {
        filmSessionRepository.save(session);
        return session;
    }
}