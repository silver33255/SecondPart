package com.panaskin.secureapp.repository;

import com.panaskin.secureapp.entity.FilmSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmSessionRepository extends JpaRepository<FilmSession, Long> {
}
