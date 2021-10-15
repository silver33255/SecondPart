package com.panaskin.secureapp.exception;

public class FilmSessionNotFoundException extends RuntimeException {
    public FilmSessionNotFoundException(long id){
        super("Couldn't found film session by id: " + id);
    }
}
