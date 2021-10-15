package com.panaskin.secureapp.repository;

import com.panaskin.secureapp.entity.FilmSession;
import com.panaskin.secureapp.repository.queries.CustomQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FilmSessionRepository extends JpaRepository<FilmSession, Long> {
}
