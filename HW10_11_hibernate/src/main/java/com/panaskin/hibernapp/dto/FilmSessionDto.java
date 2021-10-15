package com.panaskin.hibernapp.dto;

import com.panaskin.hibernapp.entity.Film;
import com.panaskin.hibernapp.entity.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class FilmSessionDto {
    private UUID id;
    private Film film;
    private List<Ticket> tickets;
}
