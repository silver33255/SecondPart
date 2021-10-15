package com.panaskin.secureapp.repository.queries;

public class CustomQueries {
    public static final String OOKING_FILM_SESSION_SEATS = "UPDATE FILM_SESSION SET FREE_SEATS = FREE_SEATS - 1 WHERE id = ? and FREE_SEATS > 0";
}
