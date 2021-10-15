package com.panaskin.secureapp.service.impl;

import com.panaskin.secureapp.entity.FilmSession;
import com.panaskin.secureapp.repository.FilmSessionRepository;
import com.panaskin.secureapp.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilmSessionServiceImpl implements EntityService<FilmSession> {

    @Autowired
    private final FilmSessionRepository filmSessionRepository;


    @Override
    public List<FilmSession> receiveAll() {
        return filmSessionRepository.findAll();
    }

    @Override
    public Optional<FilmSession> receiveById(Long id) {
        return filmSessionRepository.findById(id);
    }

    @Override
    public void add(FilmSession session) {
        filmSessionRepository.save(session);
    }
}
